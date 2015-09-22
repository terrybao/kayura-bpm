package org.kayura.bpm.engine.executor;

import org.kayura.bpm.engine.WorkflowContext;
import org.kayura.bpm.kernel.WorkItem;
import org.kayura.bpm.storage.StorageService;

public class FindWorkItemExecutor extends Executor<WorkItem> {

	private String workItemId;

	public FindWorkItemExecutor(String workItemId) {
		this.workItemId = workItemId;
	}

	@Override
	public WorkItem doExecure(WorkflowContext context) {

		StorageService storageService = context.getStorageService();
		WorkItem workItem = storageService.getWorkItemById(workItemId);		

		return workItem;
	}

}
