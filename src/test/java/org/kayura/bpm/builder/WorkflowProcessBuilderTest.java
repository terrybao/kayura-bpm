/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.builder;

import java.io.InputStream;

import org.junit.Test;
import org.kayura.bpm.models.WorkflowProcess;
import org.kayura.bpm.models.WorkflowProcessSimple;

/**
 * @author liangxia@live.com
 */
public class WorkflowProcessBuilderTest {

	@Test
	public void formatXmlTest() {

		WorkflowProcess process = WorkflowProcessSimple.createBranchWorkflowProcess();

		WorkflowProcessBuilder builder = new WorkflowProcessBuilder(process);

		System.out.println(builder.exportXml());
	}

	@Test
	public void parseWorkflowProcessTest() {

		InputStream inputStream = ClassLoader.getSystemResourceAsStream("simpleWorkflow.xml");

		WorkflowProcessBuilder builder = new WorkflowProcessBuilder(inputStream);

		WorkflowProcess process = builder.parseWorkflowProcess();

		System.out.println(process);
	}
}
