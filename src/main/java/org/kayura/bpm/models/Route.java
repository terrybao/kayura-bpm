package org.kayura.bpm.models;

import java.util.List;

public class Route extends Node {

	// single, multi
	public final static Integer RouteTypes_Single = 0;
	public final static Integer RouteTypes_Multi = 1;

	private Integer routeType;

	public Route() {

	}

	public Route(WorkflowProcess parent, String code) {
		super(parent, NodeTypes_Route, code);
		this.routeType = RouteTypes_Single;
	}

	public Integer getRouteType() {
		return routeType;
	}

	public void setRouteType(Integer routeType) {
		this.routeType = routeType;
	}

	@Override
	public List<Transition> getToTransitions() {
		return super.getToTransitions();
	}

	@Override
	protected List<Transition> getFromTransitions() {
		return super.getFromTransitions();
	}

}
