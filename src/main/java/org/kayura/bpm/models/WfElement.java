package org.kayura.bpm.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public abstract class WfElement {
    
    private String id;
    private WfElement parent;
    private String code;
    private String name;
    private String description;
    private Date createdTime;
    private Date modifiedTime;
    private List<EventListener> listeners;
    private Properties attributes;
    
    public WfElement() {
	
    }
    
    public WfElement(WfElement parent, String code) {
	this.parent = parent;
	this.code = code;
	this.listeners = new ArrayList<EventListener>();
	this.attributes = new Properties();
    }
    
    @Override
    public boolean equals(Object obj) {
	if (obj instanceof WfElement) {
	    return ((WfElement) obj).id.equals(this.id);
	}
	return false;
    }
    
    public String getId() {
	return id;
    }
    
    public void setId(String id) {
	this.id = id;
    }
    
    public WfElement getParent() {
	return parent;
    }
    
    public void setParent(WfElement parent) {
	this.parent = parent;
    }
    
    public String getCode() {
	return code;
    }
    
    public void setCode(String code) {
	this.code = code;
    }
    
    public String getName() {
	return name;
    }
    
    public void setName(String name) {
	this.name = name;
    }
    
    public String getDescription() {
	return description;
    }
    
    public void setDescription(String description) {
	this.description = description;
    }
    
    public Date getCreatedTime() {
	return createdTime;
    }
    
    public void setCreatedTime(Date createdTime) {
	this.createdTime = createdTime;
    }
    
    public Date getModifiedTime() {
	return modifiedTime;
    }
    
    public void setModifiedTime(Date modifiedTime) {
	this.modifiedTime = modifiedTime;
    }
    
    public List<EventListener> getListeners() {
	return listeners;
    }
    
    public void setListeners(List<EventListener> listeners) {
	this.listeners = listeners;
    }
    
    public void addListener(EventListener listener) {
	this.listeners.add(listener);
    }
    
    public EventListener addistener(String className, int sn) {
	EventListener listener = new EventListener(className, sn);
	this.listeners.add(listener);
	return listener;
    }
    
    public Properties getAttributes() {
	return attributes;
    }
    
    public void setAttributes(Properties attributes) {
	this.attributes.clear();
	if (attributes != null) {
	    this.attributes.putAll(attributes);
	}
    }
    
    public void putAttribute(String key, Object value) {
	this.attributes.put(key, value);
    }
    
    public void pubAllAttributes(Properties attributes) {
	this.attributes.putAll(attributes);
    }
    
}
