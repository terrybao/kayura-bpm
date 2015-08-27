package org.kayura.bpm.types;

import org.kayura.utils.StringUtils;

public class Actor {

	private String id;
	private String displayName;
	private String departId;
	private String positionId;

	public Actor() {

	}

	public Actor(String id) {
		this.id = id;
	}

	public Actor(String id, String displayName) {
		this.id = id;
		this.displayName = displayName;
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

		Actor tar = (Actor) obj;

		if (StringUtils.equals(this.id, tar.getId())
				&& StringUtils.equals(this.departId, tar.getDepartId())
				&& StringUtils.equals(this.positionId, tar.getPositionId())) {
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

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
}
