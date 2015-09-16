package org.kayura.bpm.models;

public class Transition extends WfElement {

	private Node fromNode;
	private Node toNode;
	private String conditions;

	public Transition() {

	}

	public Transition(WorkflowProcess parent, Node fromNode, Node toNode) {
		super(parent, null);
		this.setFromNode(fromNode);
		this.setToNode(toNode);
	}

	public void remove() {
		if (this.fromNode != null) {
			this.fromNode.removeTransition(this.getId());
			this.fromNode = null;
		}

		if (this.toNode != null) {
			this.toNode.removeTransition(this.getId());
			this.toNode = null;
		}
	}

	public Node getFromNode() {
		return fromNode;
	}

	public void setFromNode(Node fromNode) {
		this.fromNode = fromNode;
	}

	public Node getToNode() {
		return toNode;
	}

	public void setToNode(Node toNode) {
		this.toNode = toNode;
	}

	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

}
