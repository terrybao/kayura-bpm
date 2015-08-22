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
import org.kayura.bpm.kernel.WorkItem;
import org.kayura.bpm.models.Activity;
import org.kayura.bpm.types.Actor;
import org.kayura.bpm.types.StartArgs;
import org.kayura.bpm.types.StartResult;

public class WorkflowRuntimeImpl implements IWorkflowRuntime {

	private IWorkflowContext context;

	public IWorkflowContext getContext() {
		return context;
	}

	public void setContext(IWorkflowContext context) {
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

			Actor creator = args.getCreator();
			if (creator == null) {
				throw new WorkflowException("必须定义流程的启动者。");
			}

			// 创建工作流实例.
			ProcessInstance instance = execute(
					new CreateProcessInstanceExecutor(args.getFlowCode(), args.getBizData(), creator));

			// 取得可用的后续活动实例.
			StartNodeInstance startNodeInstance = instance.getStartNodeInstance();
			Map<Activity, List<Actor>> activityActors = startNodeInstance.findNextActivityActors(args.getVariables());

			Map<String, List<Actor>> nextActivities = args.getNextActivities();
			for (Activity activity : activityActors.keySet()) {
				if (nextActivities.size() > 0 && !nextActivities.keySet().contains(activity.getId())) {
					continue;
				}

				ActivityInstance ai = this.execute(new CreateActivityInstanceExecutor(instance, activity));
				List<Actor> actors = activityActors.get(activity);
				for (Actor actor : actors) {
					WorkItem workItem = this.execute(new CreateWorkItemExecutor(ai, creator, actor));
				}
			}

		} catch (Exception e) {
			result.setError("流程启动失败。原因：%s", e.getMessage());
		}

		return result;
	}

}
