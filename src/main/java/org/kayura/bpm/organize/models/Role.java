package org.kayura.bpm.organize.models;

public class Role {

	private String id;
	private String parentId;
	private String displayName;
	private Integer status;

	public Role() {

	}

	public Role(String parentId) {
		this.setParentId(parentId);
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

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
