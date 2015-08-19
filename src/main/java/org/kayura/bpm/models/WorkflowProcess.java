package org.kayura.bpm.models;

import java.util.ArrayList;
import java.util.List;

import org.kayura.bpm.exceptions.WorkflowException;
import org.kayura.utils.KeyUtils;

public class WorkflowProcess extends WfElement {
    
    private BizForm bizForm;
    private Integer version;
    private String modifier;
    private Integer status = DefineStatus.design;
    
    private StartNode startNode;
    private List<Activity> activities = new ArrayList<Activity>();
    private List<Route> routes = new ArrayList<Route>();
    private List<EndNode> endNodes = new ArrayList<EndNode>();
    private List<Transition> transitions = new ArrayList<Transition>();
    
    public WorkflowProcess() {
	super();
    }
    
    public WorkflowProcess(WorkflowProcess parent, String code) {
	super(parent, code);
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
    
    public Integer getStatus() {
	return status;
    }
    
    public void setStatus(Integer status) {
	this.status = status;
    }
    
    public StartNode createStartNode() {
	if (this.startNode != null) {
	    throw new WorkflowException("流程定义中只允许有一个开始节点。");
	}
	this.startNode = new StartNode(this, "startNode");
	this.startNode.setName("开始");
	this.startNode.setId(KeyUtils.newId());
	return this.startNode;
    }
    
    public EndNode createEndNode(String code) {
	EndNode endNode = new EndNode(this, code);
	endNode.setId(KeyUtils.newId());
	this.endNodes.add(endNode);
	return endNode;
    }
    
    public Node removeNode(String nodeId) {
	Node node = findNodeById(nodeId);
	if (node != null) {
	    
	    if (this.startNode.getId().equals(nodeId)) {
		throw new WorkflowException("开始节点不能被移除。");
	    }
	    
	    List<Node> list = new ArrayList<Node>(this.activities);
	    for (Node n : list) {
		if (n.getId().equals(nodeId)) {
		    this.activities.remove(n);
		}
	    }
	    
	    list = new ArrayList<Node>(this.routes);
	    for (Node n : list) {
		if (n.getId().equals(nodeId)) {
		    this.routes.remove(n);
		}
	    }
	    
	    list = new ArrayList<Node>(this.endNodes);
	    for (Node n : list) {
		if (n.getId().equals(nodeId)) {
		    this.endNodes.remove(n);
		}
	    }
	    
	    node.removeFromTransitions();
	    node.removeToTransitions();
	}
	return node;
    }
    
    public Transition removeTransition(String transitionId) {
	List<Transition> list = new ArrayList<Transition>(this.transitions);
	Transition result = null;
	for (Transition t : list) {
	    if (t.getId().equals(transitionId)) {
		this.transitions.remove(t);
		t.remove();
		
		result = t;
		break;
	    }
	}
	return result;
    }
    
    public Activity createActivity(String code) {
	Activity activity = new Activity(this, code);
	activity.setId(KeyUtils.newId());
	this.activities.add(activity);
	return activity;
    }
    
    public Route createRoute(String code) {
	Route route = new Route(this, code);
	route.setId(KeyUtils.newId());
	this.routes.add(route);
	return route;
    }
    
    public Transition createTransition(String fromId, String toId) {
	
	Node fromNode = findNodeById(fromId);
	if (fromNode == null) {
	    throw new WorkflowException(String.format("来源环节Id %s 不存在。", fromId));
	}
	
	Node toNode = findNodeById(toId);
	if (toNode == null) {
	    throw new WorkflowException(String.format("到达环节Id %s 不存在。", toId));
	}
	
	return createTransition(fromNode, toNode);
    }
    
    public Transition createTransition(Node fromNode, Node toNode) {
	
	Transition transition = new Transition(this, fromNode, toNode);
	transition.setId(KeyUtils.newId());
	transition.setCode(String.format("%s->%s", fromNode.getCode(), toNode.getCode()));
	transition.setName(String.format("%s->%s", fromNode.getName(), toNode.getName()));
	this.transitions.add(transition);
	return transition;
    }
    
    public Node findNodeById(String nodeId) {
	
	if (startNode.getId().equals(nodeId)) {
	    return startNode;
	}
	
	for (Activity activity : activities) {
	    if (activity.getId().equals(nodeId)) {
		return activity;
	    }
	}
	
	for (Route route : routes) {
	    if (route.getId().equals(nodeId)) {
		return route;
	    }
	}
	
	for (EndNode endNode : endNodes) {
	    if (endNode.getId().equals(nodeId)) {
		return endNode;
	    }
	}
	
	return null;
    }
    
    public Node findNodeByCode(String nodeCode) {
	
	if (startNode.getCode().equals(nodeCode)) {
	    return startNode;
	}
	
	for (Activity activity : activities) {
	    if (activity.getCode().equals(nodeCode)) {
		return activity;
	    }
	}
	
	for (Route route : routes) {
	    if (route.getCode().equals(nodeCode)) {
		return route;
	    }
	}
	
	for (EndNode endNode : endNodes) {
	    if (endNode.getCode().equals(nodeCode)) {
		return endNode;
	    }
	}
	
	return null;
    }
    
    public StartNode getStartNode() {
	return this.startNode;
    }
    
    public List<EndNode> getEndNodes() {
	return this.endNodes;
    }
    
    public EndNode findEndNodeById(String nodeId) {
	Node node = findNodeById(nodeId);
	if (node != null && node instanceof EndNode) {
	    return (EndNode) node;
	}
	return null;
    }
    
    public List<Node> getNodes() {
	List<Node> nodes = new ArrayList<Node>();
	nodes.add(this.getStartNode());
	nodes.addAll(this.getActivities());
	nodes.addAll(this.getRoutes());
	nodes.addAll(this.getEndNodes());
	return nodes;
    }
    
    public List<Activity> getActivities() {
	return this.activities;
    }
    
    public Activity findActivityById(String nodeId) {
	Node node = findNodeById(nodeId);
	if (node != null && node instanceof Activity) {
	    return (Activity) node;
	}
	return null;
    }
    
    public List<Route> getRoutes() {
	return this.routes;
    }
    
    public Route findRouteById(String nodeId) {
	Node node = findNodeById(nodeId);
	if (node != null && node instanceof Route) {
	    return (Route) node;
	}
	return null;
    }
    
    public List<Transition> getTransitions() {
	return transitions;
    }
    
    public void setTransitions(List<Transition> transitions) {
	this.transitions = transitions;
    }
    
    public void setStartNode(StartNode startNode) {
	this.startNode = startNode;
    }
    
    public void setActivities(List<Activity> activities) {
	this.activities = activities;
    }
    
    public void setRoutes(List<Route> routes) {
	this.routes = routes;
    }
    
    public void setEndNodes(List<EndNode> endNodes) {
	this.endNodes = endNodes;
    }
}
