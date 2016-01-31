/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class TaskArgs {

	private String workItemId;
	private Actor handler;
	private String comment;
	private List<Actor> copyTo = new ArrayList<Actor>();
	private Map<String, List<Actor>> targetActivities = new HashMap<String, List<Actor>>();
	private Properties variables = new Properties();

	public TaskArgs() {

	}

	public TaskArgs(String workItemId, Actor handler) {
		this.workItemId = workItemId;
		this.handler = handler;
	}

	public String getWorkItemId() {
		return workItemId;
	}

	public void setWorkItemId(String workItemId) {
		this.workItemId = workItemId;
	}

	public Actor getHandler() {
		return handler;
	}

	public void setHandler(Actor handler) {
		this.handler = handler;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<Actor> getCopyTo() {
		return copyTo;
	}

	public void setCopyTo(List<Actor> copyTo) {
		this.copyTo = copyTo;
	}

	public Map<String, List<Actor>> getTargetActivities() {
		return targetActivities;
	}

	public void setTargetActivities(Map<String, List<Actor>> targetActivities) {
		this.targetActivities = targetActivities;
	}

	public Properties getVariables() {
		return variables;
	}

	public void setVariables(Properties variables) {
		this.variables = variables;
	}

}
