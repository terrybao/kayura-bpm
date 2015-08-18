package org.kayura.bpm.models;

import java.util.List;

public class EndNode extends Node {
    
    public EndNode() {
	
    }
    
    public EndNode(WorkflowProcess parent, String code) {
	super(parent, NodeTypes_EndNode, code);
    }
    
    @Override
    protected List<Transition> getFromTransitions() {
	return super.getFromTransitions();
    }
}
