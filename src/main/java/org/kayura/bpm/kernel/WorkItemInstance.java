package org.kayura.bpm.kernel;

import org.kayura.bpm.engine.IWorkflowContext;
import org.kayura.bpm.engine.IWorkflowContextAware;

public class WorkItemInstance implements IWorkflowContextAware {

	
	private IWorkflowContext context;

	@Override
	public void setContext(IWorkflowContext context) {
		this.context = context;
	}

	
	
}
