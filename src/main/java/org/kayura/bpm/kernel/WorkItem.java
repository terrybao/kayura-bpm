package org.kayura.bpm.kernel;

import org.kayura.bpm.types.Actor;

public class WorkItem {

	public static class Prioritys {
		public final static Integer Lower = -1;
		public final static Integer Medium = 0;
		public final static Integer High = 1;
	}

	public static class TaskTypes {
		public final static Integer Task = 0;
		public final static Integer Read = 1;
	}

	private String id;
	private WorkItem parent;
	private ActivityInstance activityInstance;
	private Actor sender;
	private Actor owner;
	private Actor handler;
	private Integer sn;
	private Integer priority;
	private Integer depth;
	private Integer taskType;
	private Integer status;
	private String commint;
	private String completedTime;

	public WorkItem() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public WorkItem getParent() {
		return parent;
	}

	public void setParent(WorkItem parent) {
		this.parent = parent;
	}

	public ActivityInstance getActivityInstance() {
		return activityInstance;
	}

	public void setActivityInstance(ActivityInstance activityInstance) {
		this.activityInstance = activityInstance;
	}

	public Actor getSender() {
		return sender;
	}

	public void setSender(Actor sender) {
		this.sender = sender;
	}

	public Actor getOwner() {
		return owner;
	}

	public void setOwner(Actor owner) {
		this.owner = owner;
	}

	public Actor getHandler() {
		return handler;
	}

	public void setHandler(Actor handler) {
		this.handler = handler;
	}

	public Integer getSn() {
		return sn;
	}

	public void setSn(Integer sn) {
		this.sn = sn;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}

	public Integer getTaskType() {
		return taskType;
	}

	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCommint() {
		return commint;
	}

	public void setCommint(String commint) {
		this.commint = commint;
	}

	public String getCompletedTime() {
		return completedTime;
	}

	public void setCompletedTime(String completedTime) {
		this.completedTime = completedTime;
	}

}
