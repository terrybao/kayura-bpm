/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.kernel;

import java.util.Date;

import org.kayura.bpm.engine.WorkflowContext;
import org.kayura.bpm.engine.WorkflowContextAware;
import org.kayura.bpm.models.WorkflowProcess;
import org.kayura.bpm.types.Actor;
import org.kayura.utils.DateUtils;

public class ProcessInstance implements WorkflowContextAware {

	private String id;
	private String parentId;
	private WorkflowProcess process;
	private String title;
	private String bizDataId;
	private Actor creator;
	private Date createdTime;
	private Date completedTime;
	private Integer status;
	private WorkflowContext context;

	public ProcessInstance() {

	}

	@Override
	public void setContext(WorkflowContext context) {
		this.context = context;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public WorkflowProcess getProcess() {
		return process;
	}

	public void setProcess(WorkflowProcess process) {
		this.process = process;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBizDataId() {
		return bizDataId;
	}

	public void setBizDataId(String bizDataId) {
		this.bizDataId = bizDataId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getCompletedTime() {
		return completedTime;
	}

	public void setCompletedTime(Date completedTime) {
		this.completedTime = completedTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Actor getCreator() {
		return creator;
	}

	public void setCreator(Actor creator) {
		this.creator = creator;
	}

	public StartNodeInstance getStartNodeInstance() {
		return context.bind(new StartNodeInstance(this.process.getStartNode()));
	}

	public void completed() {

		this.setCompletedTime(DateUtils.now());
		this.setStatus(InstanceStatus.Complete);

		context.getStorageService().saveOrUpdateProcessInstance(this);
	}
}
