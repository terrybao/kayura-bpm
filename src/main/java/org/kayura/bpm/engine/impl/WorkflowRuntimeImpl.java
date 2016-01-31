/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.engine.impl;

import java.util.List;
import java.util.Map;

import org.kayura.bpm.engine.WorkflowContext;
import org.kayura.bpm.engine.WorkflowRuntime;
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
import org.kayura.bpm.kernel.WorkItem.TaskStatus;
import org.kayura.bpm.kernel.WorkItem.TaskTypes;
import org.kayura.bpm.models.Activity;
import org.kayura.bpm.models.Node;
import org.kayura.bpm.organize.OrganizeService;
import org.kayura.bpm.storage.StorageService;
import org.kayura.bpm.types.Actor;
import org.kayura.bpm.types.StartArgs;
import org.kayura.bpm.types.StartResult;
import org.kayura.bpm.types.TaskArgs;
import org.kayura.bpm.types.TaskResult;

public class WorkflowRuntimeImpl implements WorkflowRuntime {

	private WorkflowContext context;

	public WorkflowContext getContext() {
		return context;
	}

	public void setContext(WorkflowContext context) {
		this.context = context;
	}

	public WorkflowRuntimeImpl(WorkflowContext context) {
		this.context = context;
	}

	public <T> T execute(Executor<T> exec) {
		return exec.execute(this.context);
	}

	private WorkItem findWorkItemByFirst(String bizFlowCode, String bizDataId, Actor user,
			Integer[] status) {

		StorageService storageService = context.getStorageService();
		OrganizeService organizeService = context.getOrganizeService();

		Actor byActor = organizeService.findActorByActor(user);
		ProcessInstance instance = storageService.findProcessInstance(bizFlowCode, bizDataId);

		WorkItem byFirst = storageService.findWorkItemByFirst(instance.getId(), byActor.getId(),
				status);

		return byFirst;
	}

	@Override
	public WorkItem findWorkItemByFirst(String bizFlowCode, String bizDataId, Actor user) {

		Integer[] status = { TaskStatus.Todo, TaskStatus.Completed };
		return findWorkItemByFirst(bizFlowCode, bizDataId, user, status);
	}

	@Override
	public WorkItem findTodoTaskByFirst(String bizFlowCode, String bizDataId, Actor user) {

		Integer[] status = { TaskStatus.Todo };
		return findWorkItemByFirst(bizFlowCode, bizDataId, user, status);
	}

	@Override
	public StartResult startup(StartArgs args) {

		StartResult result = new StartResult("流程启动成功。");

		if (args == null) {
			throw new WorkflowException("必须定义流程的启动参数，不允许为空。");
		}

		if (args.getCreator() == null) {
			throw new WorkflowException("必须定义流程的启动者。");
		}

		OrganizeService organizeService = this.context.getOrganizeService();
		Map<String, List<Actor>> nextActivities = args.getNextActivities();
		Actor creator = organizeService.findActorByActor(args.getCreator());

		// 创建工作流实例.
		ProcessInstance instance = execute(
				new CreateProcessInstanceExecutor(args.getFlowCode(), args.getBizData(), creator));

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

		return result;
	}

	@Override
	public TaskResult claimTask(TaskArgs args) {

		return null;
	}

	@Override
	public TaskResult completeTask(TaskArgs args) {

		StorageService storageService = context.getStorageService();

		WorkItem workItem = storageService.getWorkItemById(args.getWorkItemId());
		if (workItem.getStatus() == TaskStatus.Completed
				|| workItem.getStatus() == TaskStatus.End) {
			return new TaskResult("该任务已经完成。");
		}

		TaskManager taskMgr = this.context.bind(new TaskManager(workItem));

		return taskMgr.completed(args);
	}

	@Override
	public TaskResult backTask(TaskArgs args) {

		StorageService storageService = context.getStorageService();

		WorkItem workItem = storageService.getWorkItemById(args.getWorkItemId());
		if (workItem.getStatus() == TaskStatus.Completed
				|| workItem.getStatus() == TaskStatus.End) {
			return new TaskResult("该任务已经完成。");
		}

		TaskManager taskMgr = this.context.bind(new TaskManager(workItem));

		return taskMgr.back(args);
	}

	@Override
	public TaskResult reasignTask(TaskArgs args) {

		return null;
	}
}
