/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.models;

public class EndNode extends AbsRoute {

	public EndNode() {

	}

	public EndNode(WorkflowProcess parent, String code) {
		super(parent, NodeTypes.EndNode, code);
	}
}
