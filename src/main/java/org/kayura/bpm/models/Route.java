/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.models;

public class Route extends AbsRoute {

	public Route(WorkflowProcess parent, String code) {
		super(parent, NodeTypes.Route, code);
	}

}
