/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.engine;

import org.kayura.bpm.models.Activity;
import org.kayura.bpm.models.ActivityActor.ActorTypes;
import org.kayura.bpm.models.EndNode;
import org.kayura.bpm.models.StartNode;
import org.kayura.bpm.models.WorkflowProcess;

/**
 * @author liangxia@live.com
 */
public class WorkflowProcessSimple {

	public static WorkflowProcess getLineProcess() {

		WorkflowProcess process = new WorkflowProcess(null, "line");

		StartNode startNode = process.createStartNode();

		Activity shenpi = process.createActivity("shenpi");
		shenpi.addActor("xialiang", ActorTypes.Actor);

		EndNode endNode = process.createEndNode("endnode");

		process.createTransition(startNode, shenpi);
		process.createTransition(shenpi, endNode);

		return process;
	}

}
