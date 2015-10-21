package org.kayura.bpm.services;

import org.kayura.bpm.types.StartArgs;
import org.kayura.bpm.types.StartResult;
import org.kayura.bpm.types.TaskArgs;
import org.kayura.bpm.types.TaskResult;

public interface IntegrationService {
	StartResult startup(StartArgs args);
	TaskResult completeTask(TaskArgs args);
}
