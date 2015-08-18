package org.kayura.bpm.models;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;

import org.kayura.utils.StringUtils;

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
    
    public Activity() {
	
    }
    
    public Activity(WorkflowProcess parent, String code) {
	super(parent, NodeTypes_Activity, code);
    }
    
    public Integer getHandleType() {
	return handleType;
    }
    
    public void setHandleType(Integer handleType) {
	this.handleType = handleType;
    }
    
    public ActivityActor addActor(ActivityActor actor) {
	
	boolean exists = actors.stream().anyMatch(
		s -> StringUtils.equals(s.getActorId(), actor.getActorId()));
	
	if (!exists) {
	    this.actors.add(actor);
	}
	
	return actor;
    }
    
    public ActivityActor addActor(String actorId, Integer actorType) {
	
	Integer maxValue = 0;
	
	if (this.actors.size() > 0) {
	    IntSummaryStatistics stats = this.actors.stream().mapToInt(s -> s.getSn())
		    .summaryStatistics();
	    maxValue = stats.getMax() + 1;
	}
	
	ActivityActor actor = new ActivityActor(this, actorType, actorId);
	actor.setSn(maxValue);
	
	return this.addActor(actor);
    }
    
    public void removeActor(ActivityActor actor) {
	removeActor(actor.getActorId());
    }
    
    public ActivityActor removeActor(String actorId) {
	
	Predicate<ActivityActor> func = s -> StringUtils.equals(s.getActorId(), actorId)
		|| StringUtils.equals(s.getId(), actorId);
	
	ActivityActor actor = null;
	if (this.actors.stream().anyMatch(func)) {
	    
	    actor = this.actors.stream().filter(func).findFirst().get();
	    if (actor != null) {
		this.actors.remove(actor);
	    }
	}
	
	return actor;
    }
    
    public List<ActivityActor> getActors() {
	return actors;
    }
    
    public void setActors(List<ActivityActor> actors) {
	this.actors = actors;
    }
}
