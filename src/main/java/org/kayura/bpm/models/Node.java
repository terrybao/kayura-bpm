package org.kayura.bpm.models;

import java.util.ArrayList;
import java.util.List;

public class Node extends WfElement {
    
    protected List<Transition> fromTransitions = new ArrayList<Transition>();
    protected List<Transition> toTransitions = new ArrayList<Transition>();
    private NodeTypes nodeType;
    
    public Node(WorkflowProcess parent, NodeTypes nodeType, String code) {
	super(parent, code);
	this.nodeType = nodeType;
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
