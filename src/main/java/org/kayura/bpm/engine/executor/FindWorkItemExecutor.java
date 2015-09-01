package org.kayura.bpm.engine.executor;

import org.kayura.bpm.engine.IWorkflowContext;
import org.kayura.bpm.models.WorkItem;
import org.kayura.bpm.storage.IStorageService;

public class FindWorkItemExecutor extends Executor<WorkItem> {

	private String workItemId;

	public FindWorkItemExecutor(String workItemId) {
		this.workItemId = workItemId;
	}

	@Override
	public WorkItem doExecure(IWorkflowContext context) {

		IStorageService storageService = context.getStorageService();
		WorkItem workItem = storageService.getWorkItemById(workItemId);		

		return workItem;
	}

}
