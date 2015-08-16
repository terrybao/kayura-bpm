package org.kayura.bpm.models;

import java.io.Serializable;

public class EventListener implements Serializable {
    
    private static final long serialVersionUID = 9084285660620966038L;
    
    private String className;
    private int sn;
    private String description;
    
    public EventListener(String className, int sn) {
	this.className = className;
	this.sn = sn;
    }
    
    public String getClassName() {
	return className;
    }
    
    public void setClassName(String className) {
	this.className = className;
    }
    
    public int getSn() {
	return sn;
    }
    
    public void setSn(int sn) {
	this.sn = sn;
    }
    
    public String getDescription() {
	return description;
    }
    
    public void setDescription(String description) {
	this.description = description;
    }
    
}
