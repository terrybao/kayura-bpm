package org.kayura.bpm.types;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class StartArgs {
    
    private String flowCode;
    private Integer version;
    private String workflowProcessId;
    private BizData bizData;
    private Actor actor;
    private Map<String, List<Actor>> nextActivities = new HashMap<String, List<Actor>>();
    private Properties variables = new Properties();
    
    public String getFlowCode() {
	return flowCode;
    }
    
    public void setFlowCode(String flowCode) {
	this.flowCode = flowCode;
    }
    
    public BizData getBizData() {
	return bizData;
    }
    
    public void setBizData(BizData bizData) {
	this.bizData = bizData;
    }
    
    public Actor getActor() {
	return actor;
    }
    
    public void setActor(Actor actor) {
	this.actor = actor;
    }
    
    public Properties getVariables() {
	return variables;
    }
    
    public void setVariables(Properties variables) {
	this.variables = variables;
    }
    
    public Map<String, List<Actor>> getNextActivities() {
	return nextActivities;
    }
    
    public void setNextActivities(Map<String, List<Actor>> nextActivities) {
	this.nextActivities = nextActivities;
    }
    
    public Integer getVersion() {
	return version;
    }
    
    public void setVersion(Integer version) {
	this.version = version;
    }
    
    public String getWorkflowProcessId() {
	return workflowProcessId;
    }
    
    public void setWorkflowProcessId(String workflowProcessId) {
	this.workflowProcessId = workflowProcessId;
    }
    
}
