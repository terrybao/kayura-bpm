package org.kayura.bpm.models;

import java.util.ArrayList;
import java.util.List;

/**
 * 有参与者处理的环节类型.
 */
public class Activity extends Node {
    
    // single, sequence, all, scale
    public final static Integer HandleTypes_Single = 0;
    public final static Integer HandleTypes_Sequence = 0;
    public final static Integer HandleTypes_All = 0;
    public final static Integer HandleTypes_Scale = 0;
    
    private List<ActivityActor> actors = new ArrayList<ActivityActor>();
    private Integer handleType = HandleTypes_All;
    
    public Activity(WorkflowProcess parent, String code) {
	super(parent, NodeTypes_Activity, code);
    }
    
    public Integer getHandleType() {
	return handleType;
    }
    
    public void setHandleType(Integer handleType) {
	this.handleType = handleType;
    }
    
    public List<ActivityActor> getActors() {
	return actors;
    }
    
    public void setActors(List<ActivityActor> actors) {
	this.actors = actors;
    }
}
