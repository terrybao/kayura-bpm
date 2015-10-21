package org.kayura.bpm.services.impl;

import org.kayura.bpm.engine.WorkflowRuntime;
import org.kayura.bpm.services.IntegrationService;
import org.kayura.bpm.types.StartArgs;
import org.kayura.bpm.types.StartResult;
import org.kayura.bpm.types.TaskArgs;
import org.kayura.bpm.types.TaskResult;

public class IntegrationServiceImpl implements IntegrationService {

	private WorkflowRuntime runtime;

	public WorkflowRuntime getRuntime() {
		return runtime;
	}

	public void setRuntime(WorkflowRuntime runtime) {
		this.runtime = runtime;
	}

	@Override
	public StartResult startup(StartArgs args) {

		return runtime.startup(args);
	}

	@Override
	public TaskResult completeTask(TaskArgs args) {

		return runtime.completeTask(args);
	}
}
