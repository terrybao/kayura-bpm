/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.kernel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.kayura.bpm.engine.WorkflowContext;
import org.kayura.bpm.engine.WorkflowContextAware;
import org.kayura.bpm.models.Activity;
import org.kayura.bpm.models.Node;
import org.kayura.bpm.models.Transition;
import org.kayura.bpm.models.WfElement;
import org.kayura.bpm.types.Actor;
import org.kayura.utils.PropertiesUtils;

/**
 * @author liangxia@live.com
 */
public class AbsNodeInstance implements WorkflowContextAware {

	protected Node node;
	protected WorkflowContext context;

	public AbsNodeInstance() {

	}

	public AbsNodeInstance(Node node) {
		this.node = node;
	}

	@Override
	public void setContext(WorkflowContext context) {
		this.context = context;
	}

	public List<Node> findNextNodes(Properties vars) {

		List<Node> nextNodes = new ArrayList<Node>();
		List<Transition> toTransitions = node.getToTransitions();

		WfElement process = node.getParent();
		Properties props = PropertiesUtils.merge(process.getAttributes(), node.getAttributes(),
				vars);
		for (Transition t : toTransitions) {
			TransitionInstance ti = new TransitionInstance(t);
			if (ti.checkExpress(props)) {
				Node toNode = t.getToNode();
				nextNodes.add(toNode);
			}
		}

		return nextNodes;
	}

	public Map<Node, List<Actor>> findNextNodesActors(Properties vars) {

		Map<Node, List<Actor>> nextNodes = new HashMap<Node, List<Actor>>();
		
		List<Node> nodes = this.findNextNodes(vars);
		for (Node node : nodes) {
			if (node instanceof Activity) {
				Activity act = (Activity) node;
				ActivityInstance ai = this.context.bind(new ActivityInstance(act));
				List<Actor> actors = ai.findActors();
				nextNodes.put(act, actors);
			} else {
				nextNodes.put(node, null);
			}
		}

		return nextNodes;
	}
}
