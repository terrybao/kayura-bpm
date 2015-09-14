package org.kayura.bpm.engine;

import java.io.IOException;

import org.junit.Test;
import org.kayura.bpm.types.Actor;
import org.kayura.bpm.types.BizData;
import org.kayura.bpm.types.StartArgs;
import org.kayura.bpm.types.StartResult;
import org.kayura.utils.KeyUtils;

/**
 * 工作流引擎单元测试.
 * 
 * @author liangxia@live.com
 */
public class WorkflowStartupTest extends WorkflowEngineTest {

	public WorkflowStartupTest() throws IOException {
		super();
	}

	private String bizDataId = KeyUtils.newId();

	@Test
	public void startupTest() {

		StartArgs args = new StartArgs();
		args.setCreator(new Actor("luirenjia"));
		args.setBizData(new BizData(bizDataId, "测试流程"));
		args.setFlowCode("UnitTest");

		StartResult startResult = runtime.startup(args);

		System.out.println(startResult.getMessage());
	}

}
