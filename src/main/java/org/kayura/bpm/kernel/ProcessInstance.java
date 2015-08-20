package org.kayura.bpm.kernel;

import java.util.Date;

import org.kayura.bpm.models.WorkflowProcess;
import org.kayura.bpm.types.Actor;

public class ProcessInstance {
    
    private String id;
    private String parentId;
    private WorkflowProcess process;
    private String title;
    private String bizDataId;
    private Actor creater;
    private Date createdTime;
    private Date completedTime;
    private Integer Status;
    
    public ProcessInstance() {
	
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
    
    public Actor getCreater() {
	return creater;
    }
    
    public void setCreater(Actor creater) {
	this.creater = creater;
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
	return Status;
    }
    
    public void setStatus(Integer status) {
	Status = status;
    }
    
    public String getParentId() {
	return parentId;
    }
    
    public void setParentId(String parentId) {
	this.parentId = parentId;
    }
    
}
