/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.kernel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.kayura.bpm.engine.IWorkflowContext;
import org.kayura.bpm.engine.IWorkflowContextAware;
import org.kayura.bpm.engine.executor.CreateActivityInstanceExecutor;
import org.kayura.bpm.engine.executor.CreateWorkItemExecutor;
import org.kayura.bpm.kernel.ActivityInstance.ExecuteTypes;
import org.kayura.bpm.kernel.WorkItem.Prioritys;
import org.kayura.bpm.kernel.WorkItem.TaskStatus;
import org.kayura.bpm.kernel.WorkItem.TaskTypes;
import org.kayura.bpm.models.Activity;
import org.kayura.bpm.models.Activity.HandleTypes;
import org.kayura.bpm.models.Node;
import org.kayura.bpm.models.Node.NodeTypes;
import org.kayura.bpm.models.WorkflowProcess;
import org.kayura.bpm.organize.IOrganizeService;
import org.kayura.bpm.storage.IStorageService;
import org.kayura.bpm.types.Actor;
import org.kayura.bpm.types.TaskArgs;
import org.kayura.bpm.types.TaskResult;
import org.kayura.utils.DateUtils;

/**
 * @author liangxia@live.com
 */
public class TaskManager implements IWorkflowContextAware {

	private IWorkflowContext context;
	private WorkItem workItem;
	private ActivityInstance activityInstance;
	private ProcessInstance processInstance;
	private IStorageService storageService;
	private IOrganizeService organizeService;

	public TaskManager(WorkItem workItem) {
		this.workItem = workItem;
		this.activityInstance = workItem.getActivityInstance();
		this.processInstance = activityInstance.getProcessInstance();
	}

	@Override
	public void setContext(IWorkflowContext context) {
		this.context = context;
		
		this.storageService = context.getStorageService();
		this.organizeService = context.getOrganizeService();
		
		this.context.bind(this.activityInstance);
		this.context.bind(this.processInstance);
	}

	public TaskResult completed(TaskArgs args) {

		TaskResult result = new TaskResult();
		try {

			// 更新参与人数据.
			Actor handler = organizeService.findActorByActor(args.getHandler());
			args.setHandler(handler);

			List<Actor> copyTos = organizeService.findActorsByActors(args.getCopyTo());
			args.setCopyTo(copyTos);

			Map<String, List<Actor>> targetActivities = args.getTargetActivities();
			for (String key : targetActivities.keySet()) {
				List<Actor> value = targetActivities.get(key);
				List<Actor> actors = organizeService.findActorsByActors(value);
				targetActivities.replace(key, actors);
			}

			// 更新任务的完成状态.
			completedWorkItem(args);

			// 创建抄送任务.
			createCopyTo(args);

			// 创建后续任务.
			createNextActivityInstance(args);

		} catch (Exception e) {
			result.setCode(-1);
			result.setMessage(e.getMessage());
		}

		return result;
	}

	private void createNextActivityInstance(TaskArgs args) {

		if (this.activityInstance.getStatus() == InstanceStatus.Complete
				|| this.activityInstance.getStatus() == InstanceStatus.End) {

			Map<String, List<Actor>> targetActivities = args.getTargetActivities();
			List<String> activityIds = new ArrayList<String>(targetActivities.keySet());

			// 获取下一环节及处理人.
			Map<Node, List<Actor>> nextNodeActors = this.activityInstance
					.findNextNodesActors(args.getVariables());
			for (Node nextNode : nextNodeActors.keySet()) {

				if (activityIds.contains(nextNode.getId())) {

					if (nextNode.getNodeType() == NodeTypes.EndNode) {

						Integer[] status = { InstanceStatus.Running, InstanceStatus.Hangup };
						Integer i = storageService
								.activityInstanceCount(this.processInstance.getId(), status);
						if (i == 0) {
							processInstance.completed();
							return;
						}

					} else if (nextNode.getNodeType() == NodeTypes.Activity) {
						
						Activity nextAct = (Activity) nextNode;

						CreateActivityInstanceExecutor ae = new CreateActivityInstanceExecutor(
								this.processInstance, nextAct);
						ae.setCreator(args.getHandler());
						ActivityInstance ai = ae.execute(context);

						List<Actor> actors1 = targetActivities.get(nextNode.getId());
						List<Actor> actors2 = nextNodeActors.get(nextNode);
						for (Actor actor : actors2) {

							if (actors1.stream().anyMatch(s -> s.getId() == actor.getId())) {

								CreateWorkItemExecutor we = new CreateWorkItemExecutor(ai,
										args.getHandler(), actor);
								we.setPriority(Prioritys.Medium);
								we.setTaskType(TaskTypes.Task);
								we.setSn(0);

								we.execute(context);
							}
						}
					}
				}
			}
		}
	}

	private void createCopyTo(TaskArgs args) {

		List<Actor> copyToActors = organizeService.findActorsByActors(args.getCopyTo());
		for (Actor actor : copyToActors) {

			CreateWorkItemExecutor executor = new CreateWorkItemExecutor(this.activityInstance,
					args.getHandler(), actor);
			executor.setTaskType(TaskTypes.Read);
			executor.execute(this.context);
		}
	}

	private void completedWorkItem(TaskArgs args) {

		// 完成当前工作项.
		workItem.setHandler(args.getHandler());
		workItem.setComment(args.getComment());
		workItem.setCompletedTime(DateUtils.now());
		workItem.setStatus(TaskStatus.Completed);
		storageService.completedWorkItem(workItem);

		// 根据任务类型做不同的处理.
		WorkItem parentWorkItem = workItem.getParent();
		if (parentWorkItem != null) {

			// 1. 检查子任务是否已经全部处理完成.
			Integer[] status = { TaskStatus.Todo, TaskStatus.Suspend };
			Integer count = storageService.childTaskCount(parentWorkItem.getId(), status);

			// 2. 若全部完成将恢复上级流程状态.
			if (count == 0 && parentWorkItem.getStatus() == TaskStatus.Suspend) {

				parentWorkItem.setStatus(TaskStatus.Todo);
				storageService.updateWorkItemStatus(parentWorkItem);
			}

		} else {

			Activity activity = this.activityInstance.getActivity();
			Integer handleType = activity.getHandleType();

			// 按顺序处理的任务.
			if (handleType == HandleTypes.Sequence) {

				Integer[] status = { TaskStatus.Todo, TaskStatus.Suspend };

				// 1. 存在相序号的其它未处理任务,将只完成本任务后不做其它动作.
				Integer count = storageService.taskCountByActivity(activityInstance.getId(),
						workItem.getSn(), status);
				if (count > 0) {
					return;
				}

				// 2. 找下一个序号的任务,并将该任务置为待办状态(可能有多个相同序号的任务).
				Integer nextSn = storageService.findNextTaskSequence(this.activityInstance.getId(),
						this.workItem.getSn());
				if (nextSn != null) {
					List<WorkItem> nextWorkitems = storageService
							.findWorkItemsBySn(this.activityInstance.getId(), nextSn);
					for (WorkItem wi : nextWorkitems) {
						wi.setStatus(TaskStatus.Todo);
						storageService.updateWorkItemStatus(wi);
					}
				}

				// 3. 如果没有下个序号任务，将可以完成当前环节实例了.

			} else {
				// 任意处理人、所有处理人、按比例处理；
				Integer[] status = { TaskStatus.Todo, TaskStatus.Suspend, TaskStatus.Suspend };
				Integer todoCount = storageService
						.taskCountByActivity(this.activityInstance.getId(), null, status);
				if (todoCount == 0) {
					this.activityInstance.completed();
				}
			}
		}
	}

}
