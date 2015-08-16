package org.kayura.bpm.engine;

public interface IWorkflowContextAware {
    IWorkflowContext getContext();
    void setContext(IWorkflowContext context);
}
