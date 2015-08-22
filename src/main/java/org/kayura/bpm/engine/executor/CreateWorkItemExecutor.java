/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.engine.executor;

import org.kayura.bpm.engine.IWorkflowContext;
import org.kayura.bpm.kernel.ActivityInstance;
import org.kayura.bpm.kernel.WorkItem;
import org.kayura.bpm.storage.IStorageService;
import org.kayura.bpm.types.Actor;
import org.kayura.utils.KeyUtils;

/**
 * @author liangxia@live.com
 */
public class CreateWorkItemExecutor extends Executor<WorkItem> {

	private WorkItem parent;
	private ActivityInstance instance;
	private Actor sender;
	private Actor owner;

	public CreateWorkItemExecutor(ActivityInstance instance, Actor sender, Actor owner) {
		this.instance = instance;
		this.sender = sender;
		this.owner = owner;
	}

	public CreateWorkItemExecutor(WorkItem parent, Actor sender, Actor owner) {
		this.parent = parent;
		this.instance = parent.getActivityInstance();
		this.sender = sender;
		this.owner = owner;
	}

	@Override
	public WorkItem doExecure(IWorkflowContext context) {

		IStorageService storageService = context.getStorageService();

		Integer depth = parent != null ? (parent.getDepth() + 1) : 1;

		WorkItem task = new WorkItem();
		task.setId(KeyUtils.newId());
		task.setActivityInstance(this.instance);
		task.setParent(this.parent);
		task.setDepth(depth);
		task.setSender(sender);
		task.setOwner(owner);

		storageService.insertWorkItem(task);

		return task;
	}

}
