/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.kernel;

/**
 *  0 正常、1 挂起、2 完成、3 终止
 */
public class InstanceStatus {

	/**
	 * 0 运行状态.
	 */
	public final static Integer Running = 0;

	/**
	 * 1 挂起状态.
	 */
	public final static Integer Hangup = 1;

	/**
	 * 2 完成状态.
	 */
	public final static Integer Complete = 2;

	/**
	 * 3 结束状态.
	 */
	public final static Integer End = 3;
}
