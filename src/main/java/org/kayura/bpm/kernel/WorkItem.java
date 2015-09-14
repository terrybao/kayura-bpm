package org.kayura.bpm.kernel;

import java.util.Date;

import org.kayura.bpm.types.Actor;

/**
 * 工作流执行过程中产生的工作项,由人工处理的任务.
 * 
 * @author liangxia@live.com
 */
public class WorkItem {

	/**
	 * 任务的优先级,数字越大优先级越高.
	 */
	public static class Prioritys {
		/**
		 * 0 低优先级.
		 */
		public final static Integer Lower = 0;
		/**
		 * 1 中优先级.
		 */
		public final static Integer Medium = 1;
		/**
		 * 2 高优先级.
		 */
		public final static Integer High = 2;
	}

	/**
	 * 工作项类型：0 任务；1 查阅.
	 */
	public static class TaskTypes {
		/**
		 * 0 任务.
		 */
		public final static Integer Task = 0;
		/**
		 * 1 查阅.
		 */
		public final static Integer Read = 1;
	}

	/**
	 * 0 正常、1 挂起、2 完成、3 终止
	 */
	public static class TaskStatus {
		/**
		 * 0 正常（运行中）.
		 */
		public final static Integer Todo = 0;
		/**
		 * 1 挂起（表示该任务不可处理）.
		 */
		public final static Integer Suspend = 1;
		/**
		 * 2 完成（正常处理完成的任务）.
		 */
		public final static Integer Completed = 2;
		/**
		 * 3 结束（表示非正常处理完成的任务）.
		 */
		public final static Integer End = 3;
	}

	private String id;
	private WorkItem parent;
	private ActivityInstance activityInstance;
	private Actor sender;
	private Actor owner;
	private Date createdTime;
	private Date alarmTime;
	private Integer sn;
	private Integer priority;
	private Integer depth;
	private Integer taskType;
	private Integer status;
	private Actor handler;
	private String comment;
	private Date completedTime;

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

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Actor getHandler() {
		return handler;
	}

	public void setHandler(Actor handler) {
		this.handler = handler;
	}

	public Date getAlarmTime() {
		return alarmTime;
	}

	public void setAlarmTime(Date alarmTime) {
		this.alarmTime = alarmTime;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCompletedTime() {
		return completedTime;
	}

	public void setCompletedTime(Date completedTime) {
		this.completedTime = completedTime;
	}

}
