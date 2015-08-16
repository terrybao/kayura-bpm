package org.kayura.bpm.types;

public class Actor {
    
    private String id;
    private String displayName;
    private String deptId;
    
    public Actor(String id, String deptId) {
	this.id = id;
	this.setDeptId(deptId);
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
    
    public String getDeptId() {
	return deptId;
    }
    
    public void setDeptId(String deptId) {
	this.deptId = deptId;
    }
}
