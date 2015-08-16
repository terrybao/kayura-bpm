package org.kayura.bpm.models;

import java.util.ArrayList;
import java.util.List;

import org.kayura.bpm.exceptions.WorkflowException;
import org.kayura.utils.KeyUtils;

public class WorkflowProcess extends WfElement {
    
    private BizForm bizForm;
    private Integer version;
    private String modifier;
    private DefineStatus status;
    private List<Node> nodes;
    private List<Transition> transitions;
    
    public WorkflowProcess() {
	
    }
    
    public WorkflowProcess(String code) {
	super(null, code);
    }
    
    public BizForm getBizForm() {
	return bizForm;
    }
    
    public void setBizForm(BizForm bizForm) {
	this.bizForm = bizForm;
    }
    
    public Integer getVersion() {
	return version;
    }
    
    public void setVersion(Integer version) {
	this.version = version;
    }
    
    public String getModifier() {
	return modifier;
    }
    
    public void setModifier(String modifier) {
	this.modifier = modifier;
    }
    
    public DefineStatus getStatus() {
	return status;
    }
    
    public void setStatus(DefineStatus status) {
	this.status = status;
    }
    
    public StartNode createStartNode() {
	StartNode startNode = getStartNode();
	if (startNode != null) {
	    throw new WorkflowException("流程定义中只允许有一个开始节点。");
	}
	startNode = new StartNode(this, "startNode");
	startNode.setId(KeyUtils.newId());
	this.nodes.add(startNode);
	return startNode;
    }
    
    public EndNode createEndNode(String code) {
	EndNode endNode = new EndNode(this, code);
	endNode.setId(KeyUtils.newId());
	this.nodes.add(endNode);
	return endNode;
    }
    
    public Activity createActivity(String code) {
	Activity activity = new Activity(this, code);
	activity.setId(KeyUtils.newId());
	this.nodes.add(activity);
	return activity;
    }
    
    public Route createRoute(String code) {
	Route route = new Route(this, code);
	route.setId(KeyUtils.newId());
	this.nodes.add(route);
	return route;
    }
    
    public Node findNodeById(String nodeId) {
	List<Node> list = this.getNodes();
	for (Node node : list) {
	    if (node.getId().equals(nodeId)) {
		return node;
	    }
	}
	return null;
    }
    
    public Node findNodeByCode(String nodeCode) {
	List<Node> list = this.getNodes();
	for (Node node : list) {
	    if (node.getCode().equals(nodeCode)) {
		return node;
	    }
	}
	return null;
    }
    
    public StartNode getStartNode() {
	StartNode startNode = null;
	List<Node> list = this.getNodes();
	for (Node node : list) {
	    if (node.getNodeType() == NodeTypes.startNode && node instanceof StartNode) {
		startNode = (StartNode) node;
	    }
	}
	return startNode;
    }
    
    public List<EndNode> getEndNodes() {
	List<EndNode> endNodes = new ArrayList<EndNode>();
	List<Node> list = this.getNodes();
	for (Node node : list) {
	    if (node.getNodeType() == NodeTypes.endNode && node instanceof EndNode) {
		endNodes.add((EndNode) node);
	    }
	}
	return endNodes;
    }
    
    public EndNode findEndNodeById(String nodeId) {
	Node node = findNodeById(nodeId);
	if (node != null && node.getNodeType() == NodeTypes.endNode && node instanceof EndNode) {
	    return (EndNode) node;
	}
	return null;
    }
    
    public List<Activity> getActivities() {
	List<Activity> activities = new ArrayList<Activity>();
	List<Node> list = this.getNodes();
	for (Node node : list) {
	    if (node.getNodeType() == NodeTypes.activity && node instanceof Activity) {
		activities.add((Activity) node);
	    }
	}
	return activities;
    }
    
    public Activity findActivityById(String nodeId) {
	Node node = findNodeById(nodeId);
	if (node != null && node.getNodeType() == NodeTypes.activity && node instanceof Activity) {
	    return (Activity) node;
	}
	return null;
    }
    
    public List<Route> getRoutes() {
	List<Route> routes = new ArrayList<Route>();
	List<Node> list = this.getNodes();
	for (Node node : list) {
	    if (node.getNodeType() == NodeTypes.route && node instanceof Route) {
		routes.add((Route) node);
	    }
	}
	return routes;
    }
    
    public Route findRouteById(String nodeId) {
	Node node = findNodeById(nodeId);
	if (node != null && node.getNodeType() == NodeTypes.route && node instanceof Route) {
	    return (Route) node;
	}
	return null;
    }
    
    public List<Node> getNodes() {
	return nodes;
    }
    
    public void setNodes(List<Node> nodes) {
	this.nodes = nodes;
    }
    
    public List<Transition> getTransitions() {
	return transitions;
    }
    
    public void setTransitions(List<Transition> transitions) {
	this.transitions = transitions;
    }
}
