/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.models;

/**
 * @author liangxia@live.com
 */
public abstract class AbsRoute extends Node {

	// single, multi
	public final static Integer RouteTypes_Single = 0;
	public final static Integer RouteTypes_Multi = 1;

	private Integer routeType;

	public AbsRoute() {

	}

	public AbsRoute(WorkflowProcess parent, Integer nodeType, String code) {
		super(parent, nodeType, code);
		this.routeType = RouteTypes_Single;
	}

	public Integer getRouteType() {
		return routeType;
	}

	public void setRouteType(Integer routeType) {
		this.routeType = routeType;
	}

}
