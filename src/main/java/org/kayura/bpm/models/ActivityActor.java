package org.kayura.bpm.models;

public class ActivityActor {
    
    private Activity activity;
    private String actorId;
    private Integer sn = 0;
    private ActorTypes actorType;
    
    public ActivityActor(Activity activity, ActorTypes actorType, String actorId) {
	this.activity = activity;
	this.actorType = actorType;
	this.actorId = actorId;
    }
    
    public Activity getActivity() {
	return activity;
    }
    
    public void setActivity(Activity activity) {
	this.activity = activity;
    }
    
    public String getActorId() {
	return actorId;
    }
    
    public void setActorId(String actorId) {
	this.actorId = actorId;
    }
    
    public ActorTypes getActorType() {
	return actorType;
    }
    
    public void setActorType(ActorTypes actorType) {
	this.actorType = actorType;
    }
    
    public Integer getSn() {
	return sn;
    }
    
    public void setSn(Integer sn) {
	this.sn = sn;
    }
    
}
