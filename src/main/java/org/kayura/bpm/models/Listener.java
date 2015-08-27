package org.kayura.bpm.models;

public class Listener {

	private String className;
	private Integer sn;
	private String description;

	public Listener() {

	}

	public Listener(String className, int sn) {
		this.className = className;
		this.sn = sn;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getSn() {
		return sn;
	}

	public void setSn(Integer sn) {
		this.sn = sn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
