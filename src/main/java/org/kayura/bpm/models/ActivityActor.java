/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.models;

import org.kayura.utils.KeyUtils;

public class ActivityActor {

	/**
	 * Actor = 0, Company = 1, depart = 2, Position = 3, Role = 4, Special = 5
	 */
	public static class ActorTypes {
		public final static Integer Actor = 0;
		public final static Integer Company = 1;
		public final static Integer Depart = 2;
		public final static Integer Position = 3;
		public final static Integer Employee = 4;
		public final static Integer Role = 5;
		public final static Integer Special = 6;
	}

	private String id;
	private Activity activity;
	private String actorId;
	private String displayName;
	private Integer sn = 0;
	private Integer actorType;

	public ActivityActor() {
		this.id = KeyUtils.newId();
	}

	public ActivityActor(Activity activity, Integer actorType, String actorId) {
		this();

		this.activity = activity;
		this.actorType = actorType;
		this.actorId = actorId;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public String getActorId() {
		return actorId;
	}

	public void setActorId(String actorId) {
		this.actorId = actorId;
	}

	public Integer getActorType() {
		return actorType;
	}

	public void setActorType(Integer actorType) {
		this.actorType = actorType;
	}

	public Integer getSn() {
		return sn;
	}

	public void setSn(Integer sn) {
		this.sn = sn;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
