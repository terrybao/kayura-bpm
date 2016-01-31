/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.models;

import java.util.ArrayList;
import java.util.List;

public abstract class Node extends WfElement {

	public static class NodeTypes {
		public final static Integer StartNode = 0;
		public final static Integer Activity = 1;
		public final static Integer Route = 2;
		public final static Integer EndNode = 3;
	}

	protected List<Transition> fromTransitions = new ArrayList<Transition>();
	protected List<Transition> toTransitions = new ArrayList<Transition>();
	private Integer nodeType;

	public Node() {

	}

	public Node(WorkflowProcess parent, Integer nodeType, String code) {
		super(parent, code);
		this.nodeType = nodeType;
	}

	public void addFromTransition(Transition transition) {

		if (!fromTransitions.stream().anyMatch(s -> s.id == transition.id)) {
			this.fromTransitions.add(transition);
		}
	}

	public void addToTransition(Transition transition) {

		if (!toTransitions.stream().anyMatch(s -> s.id == transition.id)) {
			this.toTransitions.add(transition);
		}
	}

	public void removeFromTransitions() {
		for (Transition transition : fromTransitions) {
			transition.remove();
		}
	}

	public void removeToTransitions() {
		for (Transition transition : toTransitions) {
			transition.remove();
		}
	}

	public void removeTransition(String transitionId) {

		List<Transition> list1 = new ArrayList<Transition>(fromTransitions);
		for (Transition t : list1) {
			if (t.getId().equals(transitionId)) {
				fromTransitions.remove(t);
			}
		}

		List<Transition> list2 = new ArrayList<Transition>(toTransitions);
		for (Transition t : list2) {
			if (t.getId().equals(transitionId)) {
				toTransitions.remove(t);
			}
		}
	}

	public Integer getNodeType() {
		return nodeType;
	}

	public List<Transition> getFromTransitions() {
		return fromTransitions;
	}

	protected void setFromTransitions(List<Transition> fromTransitions) {
		this.fromTransitions = fromTransitions;
	}

	public List<Transition> getToTransitions() {
		return toTransitions;
	}

	protected void setToTransitions(List<Transition> toTransitions) {
		this.toTransitions = toTransitions;
	}
}
