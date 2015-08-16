package org.kayura.bpm.models;

import java.util.ArrayList;
import java.util.List;

/**
 * 有参与者处理的环节类型.
 */
public class Activity extends Node {
    
    private List<ActivityActor> actors = new ArrayList<ActivityActor>();
    private HandleTypes handleType = HandleTypes.all;
    
    public Activity(WorkflowProcess parent, String code) {
	super(parent, NodeTypes.activity, code);
    }
    
    public HandleTypes getHandleType() {
	return handleType;
    }
    
    public void setHandleType(HandleTypes handleType) {
	this.handleType = handleType;
    }
    
    public List<ActivityActor> getActors() {
	return actors;
    }
    
    public void setActors(List<ActivityActor> actors) {
	this.actors = actors;
    }
    
    /*
     * 0 单人签; 1 顺序签; 2 全签;3 比例签;
     */
    public enum HandleTypes {
	single, sequence, all, scale
    }
}
