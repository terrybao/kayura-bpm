package org.kayura.bpm.models;

public class Route extends AbsRoute {

	public Route(WorkflowProcess parent, String code) {
		super(parent, NodeTypes_Route, code);
	}

}
