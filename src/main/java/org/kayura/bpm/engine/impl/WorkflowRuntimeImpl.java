package org.kayura.bpm.engine.impl;

import java.util.List;
import java.util.Map;

import org.kayura.bpm.engine.IWorkflowContext;
import org.kayura.bpm.engine.IWorkflowRuntime;
import org.kayura.bpm.engine.executor.CreateActivityInstanceExecutor;
import org.kayura.bpm.engine.executor.CreateProcessInstanceExecutor;
import org.kayura.bpm.engine.executor.CreateWorkItemExecutor;
import org.kayura.bpm.engine.executor.Executor;
import org.kayura.bpm.exceptions.WorkflowException;
import org.kayura.bpm.kernel.ActivityInstance;
import org.kayura.bpm.kernel.ProcessInstance;
import org.kayura.bpm.kernel.StartNodeInstance;
import org.kayura.bpm.kernel.TaskManager;
import org.kayura.bpm.kernel.WorkItem;
import org.kayura.bpm.kernel.WorkItem.Prioritys;
import org.kayura.bpm.kernel.WorkItem.TaskTypes;
import org.kayura.bpm.models.Activity;
import org.kayura.bpm.models.Node;
import org.kayura.bpm.organize.IOrganizeService;
import org.kayura.bpm.storage.IStorageService;
import org.kayura.bpm.types.Actor;
import org.kayura.bpm.types.StartArgs;
import org.kayura.bpm.types.StartResult;
import org.kayura.bpm.types.TaskArgs;
import org.kayura.bpm.types.TaskResult;

public class WorkflowRuntimeImpl implements IWorkflowRuntime {

	private IWorkflowContext context;

	public IWorkflowContext getContext() {
		return context;
	}

	public void setContext(IWorkflowContext context) {
		this.context = context;
	}

	public WorkflowRuntimeImpl(IWorkflowContext context) {
		this.context = context;
	}

	public <T> T execute(Executor<T> exec) {
		return exec.execute(this.context);
	}

	public StartResult startup(StartArgs args) {

		StartResult result = new StartResult();

		try {
			if (args == null) {
				throw new WorkflowException("必须定义流程的启动参数，不允许为空。");
			}

			if (args.getCreator() == null) {
				throw new WorkflowException("必须定义流程的启动者。");
			}

			IOrganizeService organizeService = this.context.getOrganizeService();
			Map<String, List<Actor>> nextActivities = args.getNextActivities();
			Actor creator = organizeService.findActorByActor(args.getCreator());

			// 创建工作流实例.
			ProcessInstance instance = execute(new CreateProcessInstanceExecutor(args.getFlowCode(),
					args.getBizData(), creator));

			// 取得可用的后续活动实例.
			StartNodeInstance si = instance.getStartNodeInstance();
			Map<Node, List<Actor>> nodeActors = si.findNextNodesActors(args.getVariables());
			for (Node nextNode : nodeActors.keySet()) {

				if (nextActivities.size() > 0 && !nodeActors.keySet().contains(nextNode.getId())) {
					continue;
				}

				if (nextNode instanceof Activity) {
					Activity nextAct = (Activity) nextNode;

					CreateActivityInstanceExecutor ae = new CreateActivityInstanceExecutor(instance,
							nextAct);
					ae.setCreator(creator);

					ActivityInstance ai = this.execute(ae);

					List<Actor> actors = nodeActors.get(nextNode);
					for (Actor actor : actors) {

						CreateWorkItemExecutor we = new CreateWorkItemExecutor(ai, creator, actor);
						we.setPriority(Prioritys.Medium);
						we.setTaskType(TaskTypes.Task);
						we.setSn(0);

						this.execute(we);
					}

				}
			}

		} catch (Exception e) {
			result.setError("流程启动失败。原因：%s", e.getMessage());
		}

		return result;
	}

	public WorkItem findWorkItemByFirst(Actor user) {

		IStorageService storageService = context.getStorageService();
		IOrganizeService organizeService = context.getOrganizeService();

		Actor byActor = organizeService.findActorByActor(user);
		WorkItem byFirst = storageService.findWorkItemByFirst(byActor.getId());

		return byFirst;
	}

	public TaskResult completeWorkItem(TaskArgs args) {

		IStorageService storageService = context.getStorageService();

		WorkItem workItem = storageService.getWorkItemById(args.getWorkItemId());
		TaskManager taskMgr = this.context.bind(new TaskManager(workItem));

		return taskMgr.completed(args);
	}
}
