/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.kernel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.kayura.bpm.models.Activity;
import org.kayura.bpm.models.Node;
import org.kayura.bpm.models.StartNode;
import org.kayura.bpm.types.Actor;

/**
 * @author liangxia@live.com
 */
public class StartNodeInstance extends AbsNodeInstance {

	public StartNodeInstance(StartNode startNode) {
		super(startNode);
	}

	public Map<Activity, List<Actor>> findNextActivityActors(Properties vars) {

		Map<Activity, List<Actor>> nextActivities = new HashMap<Activity, List<Actor>>();
		List<Node> nextNodes = this.findNextNodes(vars);
		for (Node node : nextNodes) {
			if (node instanceof Activity) {
				Activity act = (Activity) node;
				ActivityInstance ai = this.context.bind(new ActivityInstance(act));
				List<Actor> actors = ai.findActors();
				nextActivities.put(act, actors);
			}
		}

		return nextActivities;
	}

}
