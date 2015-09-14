package org.kayura.bpm.engine;

import org.kayura.bpm.kernel.WorkItem;
import org.kayura.bpm.types.Actor;
import org.kayura.bpm.types.StartArgs;
import org.kayura.bpm.types.StartResult;
import org.kayura.bpm.types.TaskArgs;
import org.kayura.bpm.types.TaskInfo;
import org.kayura.bpm.types.TaskResult;

public interface IWorkflowRuntime extends IWorkflowContextAware {

    StartResult startup(StartArgs args);

    WorkItem findWorkItemByFirst(Actor user);

	TaskResult completeWorkItem(TaskArgs args);
}
