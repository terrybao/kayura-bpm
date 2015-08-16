package org.kayura.bpm.models;

import java.util.ArrayList;
import java.util.List;

/**
 * 流程定义中唯一的开始环节.
 * 
 * @author XPMobile
 *
 */
public class StartNode extends Node {
    
    public StartNode(WorkflowProcess parent, String code) {
	super(parent, NodeTypes.startNode, code);
    }
    
    @Override
    public List<Transition> getFromTransitions() {
	return new ArrayList<Transition>();
    }
    
    @Override
    public void setFromTransitions(List<Transition> fromTransitions) {
	// 什么也不做.
    }
}
