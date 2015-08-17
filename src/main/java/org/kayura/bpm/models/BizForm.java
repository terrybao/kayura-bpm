package org.kayura.bpm.models;

import java.util.Properties;

public class BizForm {
    
    private String id;
    private String code;
    private String displayName;
    private Integer sn;
    private String description;
    private Properties attributes;
    private Integer status;
    
    public BizForm() {
	
    }
    
    public String getId() {
	return id;
    }
    
    public void setId(String id) {
	this.id = id;
    }
    
    public String getCode() {
	return code;
    }
    
    public void setCode(String code) {
	this.code = code;
    }
    
    public String getDisplayName() {
	return displayName;
    }
    
    public void setDisplayName(String displayName) {
	this.displayName = displayName;
    }
    
    public Integer getSn() {
	return sn;
    }
    
    public void setSn(Integer sn) {
	this.sn = sn;
    }
    
    public String getDescription() {
	return description;
    }
    
    public void setDescription(String description) {
	this.description = description;
    }
    
    public Properties getAttributes() {
	return attributes;
    }
    
    public void setAttributes(Properties attributes) {
	this.attributes = attributes;
    }
    
    public Integer getStatus() {
	return status;
    }
    
    public void setStatus(Integer status) {
	this.status = status;
    }
    
}
