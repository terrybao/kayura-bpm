package org.kayura.bpm.engine.executor;

import java.util.List;

import org.kayura.bpm.engine.IWorkflowContext;
import org.kayura.bpm.engine.IWorkflowContextAware;

public abstract class Executor<TResult> {
    
    private IWorkflowContext context;
    
    public TResult run(IWorkflowContext context) {
	this.context = context;
	return bind(doRun(context));
    }
    
    public abstract TResult doRun(IWorkflowContext context);
    
    public TResult bind(TResult result) {
	if (result != null) {
	    if (result instanceof IWorkflowContextAware) {
		((IWorkflowContextAware) result).setContext(context);
	    }
	    if (result instanceof List<?>) {
		List<?> items = (List<?>) result;
		for (Object o : items) {
		    if (o instanceof IWorkflowContextAware) {
			((IWorkflowContextAware) o).setContext(context);
		    }
		}
	    }
	}
	return result;
    }
}
