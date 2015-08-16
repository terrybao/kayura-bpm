package org.kayura.bpm.services.impl;

import org.kayura.bpm.engine.IWorkflowRuntime;
import org.kayura.bpm.services.IIntegrationService;
import org.kayura.bpm.types.StartArgs;
import org.kayura.bpm.types.StartResult;

public class IntegrationServiceImpl implements IIntegrationService {
    
    private IWorkflowRuntime runtime;
    
    public IWorkflowRuntime getRuntime() {
	return runtime;
    }
    
    public void setRuntime(IWorkflowRuntime runtime) {
	this.runtime = runtime;
    }
    
    public StartResult startup(StartArgs args) {
	
	return null;
    }
}
