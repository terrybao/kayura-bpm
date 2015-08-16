package org.kayura.bpm.kernel;

import java.util.Date;

import org.kayura.bpm.models.Activity;
import org.kayura.bpm.types.Actor;

public class ActivityInstance {
    
    private String id;
    private ProcessInstance processInstance;
    private Activity activity;
    private String displayName;
    private Actor creator;
    private Date createdTime;
    private Date completedTime;
    private ActivityInstance preActInstance;
    private InstanceStatus status;
    private HandleTypes handleType;
    
    public ActivityInstance() {
	
    }
    
    public String getId() {
	return id;
    }
    
    public void setId(String id) {
	this.id = id;
    }
    
    public ProcessInstance getProcessInstance() {
	return processInstance;
    }
    
    public void setProcessInstance(ProcessInstance processInstance) {
	this.processInstance = processInstance;
    }
    
    public Activity getActivity() {
	return activity;
    }
    
    public void setActivity(Activity activity) {
	this.activity = activity;
    }
    
    public String getDisplayName() {
	return displayName;
    }
    
    public void setDisplayName(String displayName) {
	this.displayName = displayName;
    }
    
    public Actor getCreator() {
	return creator;
    }
    
    public void setCreator(Actor creator) {
	this.creator = creator;
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
    
    public ActivityInstance getPreActInstance() {
	return preActInstance;
    }
    
    public void setPreActInstance(ActivityInstance preActInstance) {
	this.preActInstance = preActInstance;
    }
    
    public InstanceStatus getStatus() {
	return status;
    }
    
    public void setStatus(InstanceStatus status) {
	this.status = status;
    }
    
    public HandleTypes getHandleType() {
	return handleType;
    }
    
    public void setHandleType(HandleTypes handleType) {
	this.handleType = handleType;
    }
    
}
