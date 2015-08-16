package org.kayura.bpm.kernel;

import org.kayura.bpm.types.Actor;

public class WorkItem {
    
    public final static Integer Priority_Lower = -1;
    public final static Integer Priority_Medium = 0;
    public final static Integer Priority_High = 1;
    
    public final static Integer TaskType_Task = 0;
    public final static Integer TaskType_Read = 1;
    
    private String id;
    private WorkItem parent;
    private ActivityInstance activityInstance;
    private Actor sender;
    private Actor owner;
    private Actor handler;
    private Integer sn;
    private Integer priority;
    private Integer depth;
    private Integer taskType;
    private InstanceStatus status;
    private String commint;
    private String completedTime;
    
    public WorkItem() {
	
    }
    
    public String getId() {
	return id;
    }
    
    public void setId(String id) {
	this.id = id;
    }
    
    public WorkItem getParent() {
	return parent;
    }
    
    public void setParent(WorkItem parent) {
	this.parent = parent;
    }
    
    public ActivityInstance getActivityInstance() {
	return activityInstance;
    }
    
    public void setActivityInstance(ActivityInstance activityInstance) {
	this.activityInstance = activityInstance;
    }
    
    public Actor getSender() {
	return sender;
    }
    
    public void setSender(Actor sender) {
	this.sender = sender;
    }
    
    public Actor getOwner() {
	return owner;
    }
    
    public void setOwner(Actor owner) {
	this.owner = owner;
    }
    
    public Actor getHandler() {
	return handler;
    }
    
    public void setHandler(Actor handler) {
	this.handler = handler;
    }
    
    public Integer getSn() {
	return sn;
    }
    
    public void setSn(Integer sn) {
	this.sn = sn;
    }
    
    public Integer getPriority() {
	return priority;
    }
    
    public void setPriority(Integer priority) {
	this.priority = priority;
    }
    
    public Integer getDepth() {
	return depth;
    }
    
    public void setDepth(Integer depth) {
	this.depth = depth;
    }
    
    public Integer getTaskType() {
	return taskType;
    }
    
    public void setTaskType(Integer taskType) {
	this.taskType = taskType;
    }
    
    public InstanceStatus getStatus() {
	return status;
    }
    
    public void setStatus(InstanceStatus status) {
	this.status = status;
    }
    
    public String getCommint() {
	return commint;
    }
    
    public void setCommint(String commint) {
	this.commint = commint;
    }
    
    public String getCompletedTime() {
	return completedTime;
    }
    
    public void setCompletedTime(String completedTime) {
	this.completedTime = completedTime;
    }
    
}
