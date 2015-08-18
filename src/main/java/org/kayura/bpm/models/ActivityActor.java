package org.kayura.bpm.models;

import org.kayura.utils.KeyUtils;

public class ActivityActor {
    
    // user, role, depart, special
    public final static Integer ActorTypes_User = 0;
    public final static Integer ActorTypes_Role = 1;
    public final static Integer ActorTypes_Depart = 2;
    public final static Integer ActorTypes_Special = 3;
    
    private String id;
    private Activity activity;
    private String actorId;
    private String displayName;
    private Integer sn = 0;
    private Integer actorType;
    
    public ActivityActor() {
	this.id = KeyUtils.newId();
    }
    
    public ActivityActor(Activity activity, Integer actorType, String actorId) {
	this();
	
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
    
    public Integer getActorType() {
	return actorType;
    }
    
    public void setActorType(Integer actorType) {
	this.actorType = actorType;
    }
    
    public Integer getSn() {
	return sn;
    }
    
    public void setSn(Integer sn) {
	this.sn = sn;
    }
    
    public String getDisplayName() {
	return displayName;
    }
    
    public void setDisplayName(String displayName) {
	this.displayName = displayName;
    }
    
    public String getId() {
	return id;
    }
    
    public void setId(String id) {
	this.id = id;
    }
    
}
