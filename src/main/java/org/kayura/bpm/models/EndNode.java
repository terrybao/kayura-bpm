package org.kayura.bpm.models;

public class EndNode extends AbsRoute {

	public EndNode() {

	}

	public EndNode(WorkflowProcess parent, String code) {
		super(parent, NodeTypes_EndNode, code);
	}
}
