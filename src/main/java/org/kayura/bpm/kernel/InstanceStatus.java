package org.kayura.bpm.kernel;

/*
 *  0 正常、1 挂起、2 完成、3 终止
 */
public class InstanceStatus {

	/*
	 * 运行状态.
	 */
	public final static Integer Running = 0;

	/*
	 * 挂起状态.
	 */
	public final static Integer Hangup = 1;

	/*
	 * 完成状态.
	 */
	public final static Integer Complete = 2;

	/*
	 * 结束状态.
	 */
	public final static Integer End = 3;
}
