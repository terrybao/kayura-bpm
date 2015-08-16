package org.kayura.bpm.engine.impl;

import org.kayura.bpm.engine.IWorkflowContext;
import org.kayura.bpm.engine.IWorkflowRuntime;
import org.kayura.bpm.engine.executor.StartupWorkflowExecutor;
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
    
    public StartResult startup(StartArgs args) {
	StartResult result = new StartupWorkflowExecutor(args).run(context);
	return result;
    }
    
}
