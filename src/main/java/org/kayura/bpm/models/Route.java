package org.kayura.bpm.models;

public class Route extends Node {
    
    private RouteTypes routeType = RouteTypes.single;
    
    public Route(WorkflowProcess parent, String code) {
	super(parent, NodeTypes.route, code);
    }
    
    public RouteTypes getRouteType() {
	return routeType;
    }
    
    public void setRouteType(RouteTypes routeType) {
	this.routeType = routeType;
    }
    
    /*
     * 0 单路; 1 多路;
     */
    public enum RouteTypes {
	single, multi
    }
}
