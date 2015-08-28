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
import org.kayura.bpm.kernel.WorkItem.Prioritys;
import org.kayura.bpm.kernel.WorkItem.TaskTypes;
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

			Actor creator = args.getCreator();
			if (creator == null) {
				throw new WorkflowException("必须定义流程的启动者。");
			}

			// 创建工作流实例.
			ProcessInstance instance = execute(new CreateProcessInstanceExecutor(args.getFlowCode(),
					args.getBizData(), creator));

			// 取得可用的后续活动实例.
			StartNodeInstance si = instance.getStartNodeInstance();
			Map<Activity, List<Actor>> activityActors = si.findNextActivityActors(args.getVariables());

			Map<String, List<Actor>> nextActivities = args.getNextActivities();
			for (Activity nextAct : activityActors.keySet()) {

				if (nextActivities.size() > 0 && !activityActors.keySet().contains(nextAct.getId())) {
					continue;
				}

				CreateActivityInstanceExecutor ae = new CreateActivityInstanceExecutor(instance, nextAct);
				ae.setCreator(args.getCreator());
				
				ActivityInstance ai = this.execute(ae);

				List<Actor> actors = activityActors.get(nextAct);
				for (Actor actor : actors) {

					CreateWorkItemExecutor we = new CreateWorkItemExecutor(ai, creator, actor);
					we.setPriority(Prioritys.Lower);
					we.setTaskType(TaskTypes.Task);
					we.setSn(0);

					this.execute(we);
				}
			}

		} catch (Exception e) {
			result.setError("流程启动失败。原因：%s", e.getMessage());
		}

		return result;
	}

}
