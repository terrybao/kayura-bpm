package org.kayura.bpm.engine;

import java.io.IOException;

import org.junit.Test;
import org.kayura.bpm.kernel.WorkItem;
import org.kayura.bpm.types.Actor;

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
		
	}
}
