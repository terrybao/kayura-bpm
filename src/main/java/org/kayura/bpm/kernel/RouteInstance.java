/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.kernel;

import org.kayura.bpm.models.Route;

/**
 * 
 * @author liangxia@live.com
 */
public class RouteInstance extends AbsNodeInstance {

	private Route route;

	public RouteInstance(Route node) {
		super(node);
		route = node;
	}

	public Route getRoute() {
		return route;
	}
	
	
}
