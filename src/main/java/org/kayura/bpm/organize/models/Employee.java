package org.kayura.bpm.organize.models;

import org.kayura.bpm.types.DataStatus;

public class Employee {
    
    private String id;
    private String displayName;
    private OrgMember orgMember;
    private DataStatus status;
    
    public Employee() {
	
    }
    
    public Employee(OrgMember orgMember) {
	this.orgMember = orgMember;
    }
    
    public String getId() {
	return id;
    }
    
    public void setId(String id) {
	this.id = id;
    }
    
    public String getDisplayName() {
	return displayName;
    }
    
    public void setDisplayName(String displayName) {
	this.displayName = displayName;
    }
    
    public OrgMember getOrgMember() {
	return orgMember;
    }
    
    public void setOrgMember(OrgMember orgMember) {
	this.orgMember = orgMember;
    }
    
    public DataStatus getStatus() {
	return status;
    }
    
    public void setStatus(DataStatus status) {
	this.status = status;
    }
    
}
