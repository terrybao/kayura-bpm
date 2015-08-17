package org.kayura.bpm.storage.impl.po;

import org.kayura.bpm.models.NodeTypes;

public class IdNodeType {
    
    private String id;
    private NodeTypes nodeType;
    
    public String getId() {
	return id;
    }
    
    public void setId(String id) {
	this.id = id;
    }
    
    public NodeTypes getNodeType() {
	return nodeType;
    }
    
    public void setNodeType(NodeTypes nodeType) {
	this.nodeType = nodeType;
    }
    
}
