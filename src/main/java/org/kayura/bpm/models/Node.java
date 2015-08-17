package org.kayura.bpm.models;

import java.util.ArrayList;
import java.util.List;

public abstract class Node extends WfElement {
    
    protected List<Transition> fromTransitions = new ArrayList<Transition>();
    protected List<Transition> toTransitions = new ArrayList<Transition>();
    private NodeTypes nodeType;
    
    public Node(WorkflowProcess parent, NodeTypes nodeType, String code) {
	super(parent, code);
	this.nodeType = nodeType;
    }
    
    public void addFromTransition(Transition transition) {
	
	List<Transition> list = new ArrayList<Transition>(fromTransitions);
	
	for (Transition t : list) {
	    if (t.getId().equals(transition.getId())) {
		return;
	    }
	}
	this.fromTransitions.add(transition);
    }
    
    public void addToTransition(Transition transition) {
	
	List<Transition> list = new ArrayList<Transition>(toTransitions);
	
	for (Transition t : list) {
	    if (t.getId().equals(transition.getId())) {
		return;
	    }
	}
	this.toTransitions.add(transition);
    }
    
    public void removeFromTransitions() {
	for (Transition transition : fromTransitions) {
	    transition.remove();
	}
    }
    
    public void removeToTransitions() {
	for (Transition transition : toTransitions) {
	    transition.remove();
	}
    }
    
    public void removeTransition(String transitionId) {
	
	List<Transition> list1 = new ArrayList<Transition>(fromTransitions);
	for (Transition t : list1) {
	    if (t.getId().equals(transitionId)) {
		fromTransitions.remove(t);
	    }
	}
	
	List<Transition> list2 = new ArrayList<Transition>(toTransitions);
	for (Transition t : list2) {
	    if (t.getId().equals(transitionId)) {
		toTransitions.remove(t);
	    }
	}
    }
    
    public NodeTypes getNodeType() {
	return nodeType;
    }
    
    protected List<Transition> getFromTransitions() {
	return fromTransitions;
    }
    
    protected void setFromTransitions(List<Transition> fromTransitions) {
	this.fromTransitions = fromTransitions;
    }
    
    protected List<Transition> getToTransitions() {
	return toTransitions;
    }
    
    protected void setToTransitions(List<Transition> toTransitions) {
	this.toTransitions = toTransitions;
    }
}
