package org.kayura.bpm.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.kayura.utils.DateUtils;
import org.kayura.utils.KeyUtils;

public abstract class WfElement {

	protected String id;
	protected WfElement parent;
	protected String code;
	protected String name;
	protected String description;
	private Date createdTime;
	protected Date modifiedTime;
	protected List<Listener> listeners;
	protected Properties attributes;

	public WfElement() {
		this.id = KeyUtils.newId();
		this.createdTime = DateUtils.now();
		this.modifiedTime = this.createdTime;
	}

	public WfElement(WfElement parent, String code) {
		this();

		this.parent = parent;
		this.code = code;
		this.name = code;
		this.listeners = new ArrayList<Listener>();
		this.attributes = new Properties();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof WfElement) {
			return ((WfElement) obj).id.equals(this.id);
		}
		return false;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public WfElement getParent() {
		return parent;
	}

	public void setParent(WfElement parent) {
		this.parent = parent;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public List<Listener> getListeners() {
		return listeners;
	}

	public void setListeners(List<Listener> listeners) {
		this.listeners = listeners;
	}

	public void addListener(Listener listener) {
		this.listeners.add(listener);
	}

	public Listener addistener(Class<?> classType, int sn) {
		return addistener(classType.getName(), sn);
	}

	public Listener addistener(String className, int sn) {
		Listener listener = new Listener(className, sn);
		this.listeners.add(listener);
		return listener;
	}

	public Properties getAttributes() {
		return attributes;
	}

	public void setAttributes(Properties attributes) {
		this.attributes = attributes;
	}

	public void putAttribute(String key, Object value) {
		this.attributes.put(key, value);
	}

	public void pubAllAttributes(Properties attributes) {
		this.attributes.putAll(attributes);
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

}
