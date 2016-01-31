/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.engine.executor;

import org.kayura.bpm.engine.WorkflowContext;
import org.kayura.bpm.kernel.ActivityInstance;
import org.kayura.bpm.kernel.ActivityInstance.ExecuteTypes;
import org.kayura.bpm.kernel.InstanceStatus;
import org.kayura.bpm.kernel.ProcessInstance;
import org.kayura.bpm.models.Activity;
import org.kayura.bpm.storage.StorageService;
import org.kayura.bpm.types.Actor;
import org.kayura.utils.DateUtils;
import org.kayura.utils.KeyUtils;

/**
 * @author liangxia@live.com
 */
public class CreateActivityInstanceExecutor extends Executor<ActivityInstance> {

	private Activity activity;
	private ProcessInstance parent;
	private Actor creator;

	public CreateActivityInstanceExecutor(ProcessInstance parent, Activity activity) {
		this.parent = parent;
		this.activity = activity;
	}

	@Override
	public ActivityInstance doExecure(WorkflowContext context) {

		StorageService storageService = context.getStorageService();

		ActivityInstance instance = new ActivityInstance(activity);
		instance.setId(KeyUtils.newId());
		instance.setCreator(creator);
		instance.setDisplayName(activity.getName());
		instance.setCreatedTime(DateUtils.now());
		instance.setProcessInstance(parent);
		instance.setStatus(InstanceStatus.Running);
		instance.setExecuteType(ExecuteTypes.Unhandled);

		storageService.insertActivityInstance(instance);

		return instance;
	}

	public void setCreator(Actor creator) {
		this.creator = creator;
	}

}
