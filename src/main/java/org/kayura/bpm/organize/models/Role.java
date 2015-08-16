package org.kayura.bpm.organize.models;

import java.util.ArrayList;
import java.util.List;

import org.kayura.bpm.types.DataStatus;

public class Role {
    
    private String id;
    private String displayName;
    private Role parent;
    private List<Role> subRoles = new ArrayList<Role>();
    private List<Employee> employees = new ArrayList<Employee>();
    private DataStatus status;
    
    public Role() {
	
    }
    
    public Role(Role parent) {
	this.parent = parent;
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
    
    public Role getParent() {
	return parent;
    }
    
    public void setParent(Role parent) {
	this.parent = parent;
    }
    
    public List<Role> getSubRoles() {
	return subRoles;
    }
    
    public void setSubRoles(List<Role> subRoles) {
	this.subRoles = subRoles;
    }
    
    public List<Employee> getEmployees() {
	return employees;
    }
    
    public void setEmployees(List<Employee> employees) {
	this.employees = employees;
    }
    
    public DataStatus getStatus() {
	return status;
    }
    
    public void setStatus(DataStatus status) {
	this.status = status;
    }
}
