package org.kayura.bpm.models;

public class Transition extends WfElement {
    
    private Node fromNode;
    private Node toNode;
    private String condition;
    
    public Transition(WorkflowProcess parent, Node fromNode, Node toNode) {
	super(parent, null);
	this.setFromNode(fromNode);
	this.setToNode(toNode);
    }
    
    public Node getFromNode() {
	return fromNode;
    }
    
    public void setFromNode(Node fromNode) {
	this.fromNode = fromNode;
    }
    
    public Node getToNode() {
	return toNode;
    }
    
    public void setToNode(Node toNode) {
	this.toNode = toNode;
    }
    
    public String getCondition() {
	return condition;
    }
    
    public void setCondition(String condition) {
	this.condition = condition;
    }
    
}
