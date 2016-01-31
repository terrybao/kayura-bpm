/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.types;

/**
 * 基础数据状态，用于表示数据是否可用.
 * <p> Enabled = 0, 表示可用.
 * <p> Disabled = 1, 表示禁用.
 * @author liangxia@live.com
 */
public abstract class DataStatus {
	
	/**
	 * 表示可用数据.
	 */
	public final static Integer Enabled = 0;
	
	/**
	 * 表示禁用数据.
	 */
	public final static Integer Disabled = 1;
}
