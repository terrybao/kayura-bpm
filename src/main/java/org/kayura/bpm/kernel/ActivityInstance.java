/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.kernel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.kayura.bpm.models.Activity;
import org.kayura.bpm.types.Actor;

public class ActivityInstance extends AbsNodeInstance {

	// 0 未处理、1 正常、2 退回、3 重置、4 回滚

	public static class ExecuteTypes {
		public final static Integer Unhandled = 0;
		public final static Integer Normal = 1;
		public final static Integer Back = 2;
		public final static Integer Start = 3;
		public final static Integer Special = 4;
	}

	private String id;
	private ProcessInstance processInstance;
	private Activity activity;
	private String displayName;
	private Actor creator;
	private Date createdTime;
	private Date completedTime;
	private ActivityInstance preActInstance;
	private Integer status;
	private Integer executeType;

	public ActivityInstance(Activity activity) {
		super(activity);
		this.activity = activity;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ProcessInstance getProcessInstance() {
		return processInstance;
	}

	public void setProcessInstance(ProcessInstance processInstance) {
		this.processInstance = processInstance;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Actor getCreator() {
		return creator;
	}

	public void setCreator(Actor creator) {
		this.creator = creator;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getCompletedTime() {
		return completedTime;
	}

	public void setCompletedTime(Date completedTime) {
		this.completedTime = completedTime;
	}

	public ActivityInstance getPreActInstance() {
		return preActInstance;
	}

	public void setPreActInstance(ActivityInstance preActInstance) {
		this.preActInstance = preActInstance;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getExecuteType() {
		return executeType;
	}

	public void setExecuteType(Integer executeType) {
		this.executeType = executeType;
	}

	public List<Actor> findActors() {
		List<Actor> actors = new ArrayList<Actor>();

		return actors;
	}

}
