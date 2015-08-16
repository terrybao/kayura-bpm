package org.kayura.bpm.models;

public class EndNode extends Node {
    
    public EndNode(WorkflowProcess parent, String code) {
	super(parent, NodeTypes.endNode, code);
    }
    
}
