/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.kernel;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.kayura.bpm.engine.IWorkflowContext;
import org.kayura.bpm.engine.IWorkflowContextAware;
import org.kayura.bpm.models.Node;
import org.kayura.bpm.models.Transition;
import org.kayura.bpm.models.WfElement;
import org.kayura.utils.PropertiesUtils;

/**
 * @author liangxia@live.com
 */
public class AbsNodeInstance implements IWorkflowContextAware {

	private Node node;
	protected IWorkflowContext context;

	public AbsNodeInstance() {

	}

	public AbsNodeInstance(Node node) {
		this.node = node;
	}

	@Override
	public void setContext(IWorkflowContext context) {
		this.context = context;
	}

	public List<Node> findNextNodes(Properties vars) {

		List<Node> nextNodes = new ArrayList<Node>();
		List<Transition> toTransitions = node.getToTransitions();

		WfElement process = node.getParent();
		Properties props = PropertiesUtils.merge(process.getAttributes(), node.getAttributes(), vars);
		for (Transition t : toTransitions) {
			TransitionInstance ti = new TransitionInstance(t);
			if (ti.checkExpress(props)) {
				Node toNode = t.getToNode();
				nextNodes.add(toNode);
			}
		}

		return nextNodes;
	}

}
