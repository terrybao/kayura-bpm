package org.kayura.bpm.engine;

import org.kayura.bpm.types.StartArgs;
import org.kayura.bpm.types.StartResult;

public interface IWorkflowRuntime extends IWorkflowContextAware {

    StartResult startup(StartArgs args);
    
}
