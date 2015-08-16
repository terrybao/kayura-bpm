package org.kayura.bpm.organize.models;

import java.util.ArrayList;
import java.util.List;

import org.kayura.bpm.types.DataStatus;

public class OrgMember {
    
    private String id;
    private OrgMember parent;
    private String displayName;
    private List<OrgMember> items = new ArrayList<OrgMember>();
    private DataStatus status;
    
    public OrgMember() {
	
    }
    
    public OrgMember(OrgMember parent, String id) {
	this.parent = parent;
	this.id = id;
    }
    
    public String getId() {
	return id;
    }
    
    public void setId(String id) {
	this.id = id;
    }
    
    public OrgMember getParent() {
	return parent;
    }
    
    public void setParent(OrgMember parent) {
	this.parent = parent;
    }
    
    public String getDisplayName() {
	return displayName;
    }
    
    public void setDisplayName(String displayName) {
	this.displayName = displayName;
    }
    
    public List<OrgMember> getItems() {
	return items;
    }
    
    public void setItems(List<OrgMember> items) {
	this.items = items;
    }
    
    public DataStatus getStatus() {
	return status;
    }
    
    public void setStatus(DataStatus status) {
	this.status = status;
    }
    
}
