/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.models;

import org.kayura.bpm.models.ActivityActor.ActorTypes;
import org.kayura.utils.KeyUtils;

public class WorkflowProcessSimple {

	// 路人甲.
	private static String Actor_Luirenjia = "F0F1A397-4A70-11E5-99D9-0021CCC9FA7E";

	public static WorkflowProcess createLinear1Process() {

		WorkflowProcess process = new WorkflowProcess(null, "one_node");

		StartNode startNode = process.createStartNode();

		Activity shenpi = process.createActivity("shenpi");
		shenpi.addActor(Actor_Luirenjia, ActorTypes.Actor);

		process.createTransition(startNode, shenpi);

		EndNode endNode = process.createEndNode("endnode");
		process.createTransition(shenpi, endNode);

		return process;
	}

	public static WorkflowProcess createLinear2Process() {

		WorkflowProcess wp = new WorkflowProcess(null, "three_node");

		StartNode startNode = wp.createStartNode();

		Activity bumen = wp.createActivity("bumen");
		bumen.addActor(Actor_Luirenjia, ActivityActor.ActorTypes.Actor);

		Activity jinli = wp.createActivity("jinli1");
		jinli.addActor(Actor_Luirenjia, ActivityActor.ActorTypes.Actor);

		Activity zongjili = wp.createActivity("zongjili");
		zongjili.addActor(Actor_Luirenjia, ActivityActor.ActorTypes.Actor);

		EndNode endNode = wp.createEndNode("endNode");

		wp.createTransition(startNode, bumen);
		wp.createTransition(bumen, jinli);
		wp.createTransition(zongjili, zongjili);
		wp.createTransition(zongjili, endNode);

		return wp;
	}

	public static WorkflowProcess createBranchWorkflowProcess() {

		WorkflowProcess wp = new WorkflowProcess(null, "master");
		wp.putAttribute("key1", "xialiang");
		wp.addistener(Object.class, 1);

		StartNode startNode = wp.createStartNode();
		startNode.putAttribute("key1", "xialiang");
		startNode.addistener(Object.class, 1);

		Activity bumen = wp.createActivity("bumen");
		bumen.putAttribute("key1", "xialiang");
		bumen.addistener(Object.class, 1);
		bumen.addActor(KeyUtils.newId(), ActivityActor.ActorTypes.Actor);
		bumen.addActor(KeyUtils.newId(), ActivityActor.ActorTypes.Special);

		Route fz = wp.createRoute("fz");
		fz.putAttribute("key1", "xialiang");
		fz.addistener(Object.class, 1);

		Transition t1 = wp.createTransition(startNode, fz);
		t1.putAttribute("key1", "xialiang");
		t1.addistener(Object.class, 1);

		Activity jinli1 = wp.createActivity("jinli1");
		jinli1.putAttribute("key1", "xialiang");
		jinli1.addistener(Object.class, 1);
		jinli1.addActor(KeyUtils.newId(), ActivityActor.ActorTypes.Role);
		jinli1.addActor(KeyUtils.newId(), ActivityActor.ActorTypes.Actor);

		Activity jinli2 = wp.createActivity("jinli2");
		jinli2.putAttribute("key1", "xialiang");
		jinli2.addistener(Object.class, 1);
		jinli2.addActor(KeyUtils.newId(), ActivityActor.ActorTypes.Role);
		jinli2.addActor(KeyUtils.newId(), ActivityActor.ActorTypes.Actor);

		Transition t2 = wp.createTransition(fz, jinli1);
		t2.putAttribute("key1", "xialiang");
		t2.addistener(Object.class, 1);

		Transition t3 = wp.createTransition(fz, jinli2);
		t3.putAttribute("key1", "xialiang");
		t3.addistener(Object.class, 1);

		Route jiuhe = wp.createRoute("jiuhe");
		jiuhe.putAttribute("key1", "xialiang");
		jiuhe.addistener(Object.class, 1);

		Transition t4 = wp.createTransition(jinli1, jiuhe);
		t4.putAttribute("key1", "xialiang");
		t4.addistener(Object.class, 1);

		Transition t5 = wp.createTransition(jinli2, jiuhe);
		t5.putAttribute("key1", "xialiang");
		t5.addistener(Object.class, 1);

		Activity zongjili = wp.createActivity("zongjili");
		zongjili.putAttribute("key1", "xialiang");
		zongjili.addistener(Object.class, 1);
		zongjili.addActor(KeyUtils.newId(), ActivityActor.ActorTypes.Actor);
		zongjili.addActor(KeyUtils.newId(), ActivityActor.ActorTypes.Special);

		Transition t6 = wp.createTransition(jiuhe, zongjili);
		t6.putAttribute("key1", "xialiang");
		t6.addistener(Object.class, 1);

		EndNode nodeNode = wp.createEndNode("endNode");
		nodeNode.putAttribute("key1", "xialiang");
		nodeNode.addistener(Object.class, 1);

		Transition t7 = wp.createTransition(zongjili, nodeNode);
		t7.putAttribute("key1", "xialiang");
		t7.addistener(Object.class, 1);

		return wp;
	}

}
