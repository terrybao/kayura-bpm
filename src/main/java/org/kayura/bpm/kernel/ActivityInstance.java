/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.kernel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.kayura.bpm.kernel.ActivityInstance.ExecuteTypes;
import org.kayura.bpm.models.Activity;
import org.kayura.bpm.models.ActivityActor;
import org.kayura.bpm.models.ActivityActor.ActorTypes;
import org.kayura.bpm.organize.IOrganizeService;
import org.kayura.bpm.types.Actor;
import org.kayura.utils.DateUtils;

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
	private String preActInstanceId;
	private Integer status;
	private Integer executeType;

	public ActivityInstance() {
	}

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
		this.node = activity;
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

	public String getPreActInstanceId() {
		return preActInstanceId;
	}

	public void setPreActInstanceId(String preActInstanceId) {
		this.preActInstanceId = preActInstanceId;
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

		IOrganizeService service = this.context.getOrganizeService();
		List<ActivityActor> actActors = activity.getActors();

		List<Actor> actors = new ArrayList<Actor>();

		// 处理公司.
		List<String> companyIds = actActors.stream()
				.filter(s -> s.getActorType() == ActorTypes.Company).map(s -> s.getActorId())
				.collect(Collectors.toList());
		if (companyIds.size() > 0) {
			List<Actor> list = service.findActorsByCompany(companyIds);
			if (list.size() > 0) {
				actors.addAll(list);
			}
		}

		// 处理部门人员.
		List<String> departIds = actActors.stream()
				.filter(s -> s.getActorType() == ActorTypes.Depart).map(s -> s.getActorId())
				.collect(Collectors.toList());
		if (departIds.size() > 0) {
			List<Actor> list = service.findActorsByDepartment(departIds);
			if (list.size() > 0) {
				actors.addAll(list);
			}
		}

		// 处理岗位人员.
		List<String> positionIds = actActors.stream()
				.filter(s -> s.getActorType() == ActorTypes.Position).map(s -> s.getActorId())
				.collect(Collectors.toList());
		if (positionIds.size() > 0) {
			List<Actor> list = service.findActorsByPosition(positionIds);
			if (list.size() > 0) {
				actors.addAll(list);
			}
		}

		// 处理角色人员.
		List<String> roleIds = actActors.stream().filter(s -> s.getActorType() == ActorTypes.Role)
				.map(s -> s.getActorId()).collect(Collectors.toList());
		if (roleIds.size() > 0) {
			List<Actor> list = service.findActorsByRole(roleIds);
			if (list.size() > 0) {
				actors.addAll(list);
			}
		}

		// 处理与参者.
		List<String> actorIds = actActors.stream().filter(s -> s.getActorType() == ActorTypes.Actor)
				.map(s -> s.getActorId()).collect(Collectors.toList());
		if (actorIds.size() > 0) {
			List<Actor> list = service.findActorsByIds(actorIds);
			if (list.size() > 0) {
				actors.addAll(list);
			}
		}

		// 处理特殊人员.
		List<String> specials = actActors.stream()
				.filter(s -> s.getActorType() == ActorTypes.Special).map(s -> s.getActorId())
				.collect(Collectors.toList());
		if (specials.size() > 0) {
			List<Actor> list = findSpecialUsers(specials);
			if (list.size() > 0) {
				actors.addAll(list);
			}
		}

		return actors.stream().distinct().collect(Collectors.toList());
	}

	private List<Actor> findSpecialUsers(List<String> specials) {

		List<Actor> actors = new ArrayList<Actor>();

		return actors;
	}

	public void completed() {
		this.setStatus(InstanceStatus.Complete);
		this.setCompletedTime(DateUtils.now());
		this.setExecuteType(ExecuteTypes.Normal);
		context.getStorageService().updateActivityInstance(this);
	}

}
