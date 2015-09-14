package org.kayura.bpm.engine;

import java.io.IOException;

import org.junit.Test;
import org.kayura.bpm.kernel.WorkItem;
import org.kayura.bpm.types.Actor;
import org.kayura.bpm.types.TaskArgs;
import org.kayura.bpm.types.TaskResult;

public class WorkflowTaskTest  extends WorkflowEngineTest {

	public WorkflowTaskTest() throws IOException {
		super();
	}

	@Test
	public void findWorkItemByFirstTest(){
		
		Actor actor = new Actor("luirenjia");
		WorkItem item = runtime.findWorkItemByFirst(actor);
		
		System.out.println(item.getCreatedTime());
	}

	@Test
	public void completedWorkItemTest(){
		
		Actor actor = new Actor("luirenjia");
		WorkItem item = runtime.findWorkItemByFirst(actor);
		
		TaskArgs args = new TaskArgs();
		args.setHandler(actor);
		args.setWorkItemId(item.getId());
		
		TaskResult taskResult = runtime.completeWorkItem(args);
		
		System.out.println(taskResult);
	}
}
