package org.kayura.bpm.models;

/**
 * 流程定义中唯一的开始环节.
 */
public class StartNode extends AbsRoute {

	public StartNode() {

	}

	public StartNode(WorkflowProcess parent, String code) {
		super(parent, NodeTypes.StartNode, code);
	}

}
