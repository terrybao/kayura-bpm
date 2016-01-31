/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.models;

/**
 * @author liangxia@live.com
 */
public abstract class AbsRoute extends Node {

	public static class RouteTypes {
		public final static Integer Single = 0;
		public final static Integer Multi = 1;
	}

	private Integer routeType;

	public AbsRoute() {

	}

	public AbsRoute(WorkflowProcess parent, Integer nodeType, String code) {
		super(parent, nodeType, code);
		this.routeType = RouteTypes.Single;
	}

	public Integer getRouteType() {
		return routeType;
	}

	public void setRouteType(Integer routeType) {
		this.routeType = routeType;
	}

}
