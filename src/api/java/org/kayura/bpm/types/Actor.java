/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.types;

import org.kayura.utils.StringUtils;

public class Actor {

	private String id;
	private String parentId;
	private String employeeId;
	private String displayName;
	private String departmentId;
	private String positionId;

	public Actor() {

	}

	public Actor(String employeeId) {
		this.employeeId = employeeId;
	}

	public Actor(String employeeId, String departmentId) {
		this.employeeId = employeeId;
		this.departmentId = departmentId;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Actor)) {
			return false;
		}

		if (super.equals(obj)) {
			return true;
		}

		Actor target = (Actor) obj;

		if (StringUtils.equals(this.id, target.id) && StringUtils.equals(this.employeeId, target.employeeId)
				&& StringUtils.equals(this.departmentId, target.departmentId)
				&& StringUtils.equals(this.positionId, target.positionId)) {
			return true;
		}

		return false;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departId) {
		this.departmentId = departId;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
}
