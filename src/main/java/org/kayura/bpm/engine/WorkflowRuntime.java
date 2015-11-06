package org.kayura.bpm.engine;

import org.kayura.bpm.kernel.WorkItem;
import org.kayura.bpm.types.Actor;
import org.kayura.bpm.types.StartArgs;
import org.kayura.bpm.types.StartResult;
import org.kayura.bpm.types.TaskArgs;
import org.kayura.bpm.types.TaskResult;

public interface WorkflowRuntime extends WorkflowContextAware {

	WorkItem findWorkItemByFirst(String bizFlowCode, String bizDataId, Actor user);

	WorkItem findTodoTaskByFirst(String bizFlowCode, String bizDataId, Actor user);

	/**
	 * 启动一个工作流.
	 */
	StartResult startup(StartArgs args);

	/**
	 * 完成一个工作项.
	 */
	TaskResult completeTask(TaskArgs args);

	/**
	 * 退回一个工作项.
	 */
	TaskResult backTask(TaskArgs args);

	/**
	 * 指派一个工作项给他人.
	 */
	TaskResult reasignTask(TaskArgs args);

	/**
	 * 签收一个工作项.
	 */
	TaskResult claimTask(TaskArgs args);
}
