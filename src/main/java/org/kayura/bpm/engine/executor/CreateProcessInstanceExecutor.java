/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.engine.executor;

import org.kayura.bpm.engine.WorkflowContext;
import org.kayura.bpm.kernel.InstanceStatus;
import org.kayura.bpm.kernel.ProcessInstance;
import org.kayura.bpm.models.WorkflowProcess;
import org.kayura.bpm.storage.StorageService;
import org.kayura.bpm.types.Actor;
import org.kayura.bpm.types.BizData;
import org.kayura.utils.DateUtils;
import org.kayura.utils.KeyUtils;
import org.kayura.utils.StringUtils;

public class CreateProcessInstanceExecutor extends Executor<ProcessInstance> {

	private String parentId;
	private String flowCode;
	private Integer version;
	private String workflowProcessId;
	private BizData bizData;
	private Actor creator;

	public CreateProcessInstanceExecutor(String flowCode, BizData bizData, Actor creator) {
		this.flowCode = flowCode;
		this.bizData = bizData;
		this.creator = creator;
	}

	@Override
	public ProcessInstance doExecure(WorkflowContext context) {

		WorkflowProcess workflowProcess = null;
		StorageService storageService = context.getStorageService();

		if (!StringUtils.isEmpty(workflowProcessId)) {
			workflowProcess = storageService.getWorkflowProcess(workflowProcessId);
		} else {
			workflowProcess = storageService.getWorkflowProcess(flowCode, version);
		}

		ProcessInstance instance = new ProcessInstance();
		instance.setId(KeyUtils.newId());
		instance.setParentId(parentId);
		instance.setProcess(workflowProcess);
		instance.setBizDataId(bizData.getId());
		instance.setTitle(bizData.getTitle());
		instance.setCreator(creator);
		instance.setCreatedTime(DateUtils.now());
		instance.setStatus(InstanceStatus.Running);

		storageService.saveOrUpdateProcessInstance(instance);

		return instance;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public BizData getBizData() {
		return bizData;
	}

	public void setBizData(BizData bizData) {
		this.bizData = bizData;
	}

	public Actor getCreater() {
		return creator;
	}

	public void setCreater(Actor creater) {
		this.creator = creater;
	}

	public String getWorkflowProcessId() {
		return workflowProcessId;
	}

	public void setWorkflowProcessId(String workflowProcessId) {
		this.workflowProcessId = workflowProcessId;
	}

}
