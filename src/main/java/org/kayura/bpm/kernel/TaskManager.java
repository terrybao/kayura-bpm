/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.kernel;

import org.kayura.bpm.engine.IWorkflowContext;
import org.kayura.bpm.engine.IWorkflowContextAware;
import org.kayura.bpm.kernel.WorkItem.TaskStatus;
import org.kayura.bpm.models.Activity;
import org.kayura.bpm.models.Activity.HandleTypes;
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
	private WorkflowProcess process;
	private IStorageService storageService;
	private IOrganizeService organizeService;

	public TaskManager(WorkItem workItem) {
		this.workItem = workItem;
		this.activityInstance = workItem.getActivityInstance();
		this.processInstance = activityInstance.getProcessInstance();
		this.process = processInstance.getProcess();
	}

	@Override
	public void setContext(IWorkflowContext context) {
		this.context = context;
		this.storageService = context.getStorageService();
		this.organizeService = context.getOrganizeService();
	}

	public TaskResult completed(TaskArgs args) {

		Actor handler = organizeService.findActorByActor(args.getHandler());

		// 更新任务的完成状态.
		completedWorkItem(args, handler);

		// 创建后续任务.
		
		return null;
	}

	private void completedWorkItem(TaskArgs args, Actor handler) {
		
		workItem.setHandler(handler);
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
				

				// 3. 如果没有下个序号任务，将可以完成当前环节实例了.

			} else {
				// 任意处理人、所有处理人、按比例处理；

			}
		}
	}

}
