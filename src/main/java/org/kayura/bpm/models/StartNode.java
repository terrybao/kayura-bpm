package org.kayura.bpm.models;

import java.util.List;

/**
 * 流程定义中唯一的开始环节.
 * 
 * @author XPMobile
 *
 */
public class StartNode extends Node {
    
    public StartNode(WorkflowProcess parent, String code) {
	super(parent, NodeTypes_StartNode, code);
    }
    
    @Override
    public List<Transition> getToTransitions() {
	return super.getToTransitions();
    }
    
}
