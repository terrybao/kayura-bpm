package org.kayura.bpm.engine;

import java.io.IOException;

import org.junit.Test;
import org.kayura.bpm.kernel.WorkItem;
import org.kayura.bpm.types.Actor;
import org.kayura.bpm.types.BizData;
import org.kayura.bpm.types.StartArgs;
import org.kayura.bpm.types.StartResult;
import org.kayura.bpm.types.TaskArgs;
import org.kayura.bpm.types.TaskResult;
import org.kayura.utils.KeyUtils;

/**
 * 工作流引擎单元测试.
 * 
 * @author liangxia@live.com
 */
public class WorkflowOneNodeTest extends WorkflowEngineTest {

	public WorkflowOneNodeTest() throws IOException {
		super();
	}

	private String bizFlowCode = "OneNode";
	private String bizDataId = KeyUtils.newId();

	@Test
	public void allTest() {
		startupTest();
		completedWorkItemTest();
	}

	@Test
	public void startupTest() {

		try {
			
			StartArgs args = new StartArgs();
			args.setCreator(new Actor("luirenjia"));
			args.setBizData(new BizData(bizDataId, "测试流程"));
			args.setFlowCode(bizFlowCode);

			StartResult startResult = runtime.startup(args);

			System.out.println(startResult.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void completedWorkItemTest() {

		try {

			Actor actor = new Actor("luirenjia");
			WorkItem item = runtime.findWorkItemByFirst(bizFlowCode, bizDataId, actor);

			TaskArgs args = new TaskArgs();
			args.setHandler(actor);
			args.setWorkItemId(item.getId());

			TaskResult taskResult = runtime.completeWorkItem(args);

			System.out.println(taskResult);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
