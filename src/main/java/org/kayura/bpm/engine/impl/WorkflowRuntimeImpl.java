package org.kayura.bpm.engine.impl;

import org.kayura.bpm.engine.IWorkflowContext;
import org.kayura.bpm.engine.IWorkflowRuntime;
import org.kayura.bpm.engine.executor.CreateProcessInstanceExecutor;
import org.kayura.bpm.engine.executor.Executor;
import org.kayura.bpm.exceptions.WorkflowException;
import org.kayura.bpm.kernel.ProcessInstance;
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

			Actor actor = args.getActor();
			if (actor == null) {
				throw new WorkflowException("必须定义流程的启动者。");
			}

			ProcessInstance instance = execute(
					new CreateProcessInstanceExecutor(args.getFlowCode(), args.getBizData(), args.getActor()));
			
			
			

		} catch (Exception e) {
			result.setError("流程启动失败。原因：%s", e.getMessage());
		}

		return result;
	}

}
