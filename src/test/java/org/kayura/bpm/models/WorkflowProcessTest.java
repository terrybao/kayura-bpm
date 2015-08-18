package org.kayura.bpm.models;

import org.junit.Test;
import org.kayura.utils.KeyUtils;

public class WorkflowProcessTest {
    
    public static WorkflowProcess createWorkflowProcess() {
	
	WorkflowProcess wp = new WorkflowProcess(null, "master");
	StartNode startNode = wp.createStartNode();
	startNode.putAttribute("key1", "xialiang");
	startNode.addistener(WorkflowProcessTest.class, 1);
	
	Activity bumen = wp.createActivity("bumen");
	bumen.putAttribute("key1", "xialiang");
	bumen.addistener(WorkflowProcessTest.class, 1);
	bumen.addActor(KeyUtils.newId(), ActivityActor.ActorTypes_User);
	bumen.addActor(KeyUtils.newId(), ActivityActor.ActorTypes_Special);
	
	Route fz = wp.createRoute("fz");
	fz.putAttribute("key1", "xialiang");
	fz.addistener(WorkflowProcessTest.class, 1);
	
	Transition t1 = wp.createTransition(startNode, fz);
	t1.putAttribute("key1", "xialiang");
	t1.addistener(WorkflowProcessTest.class, 1);
	
	Activity jinli1 = wp.createActivity("jinli1");
	jinli1.putAttribute("key1", "xialiang");
	jinli1.addistener(WorkflowProcessTest.class, 1);
	jinli1.addActor(KeyUtils.newId(), ActivityActor.ActorTypes_Role);
	jinli1.addActor(KeyUtils.newId(), ActivityActor.ActorTypes_User);
	
	Activity jinli2 = wp.createActivity("jinli2");
	jinli2.putAttribute("key1", "xialiang");
	jinli2.addistener(WorkflowProcessTest.class, 1);
	jinli2.addActor(KeyUtils.newId(), ActivityActor.ActorTypes_Role);
	jinli2.addActor(KeyUtils.newId(), ActivityActor.ActorTypes_User);
	
	Transition t2 = wp.createTransition(fz, jinli1);
	t2.putAttribute("key1", "xialiang");
	t2.addistener(WorkflowProcessTest.class, 1);
	
	Transition t3 = wp.createTransition(fz, jinli2);
	t3.putAttribute("key1", "xialiang");
	t3.addistener(WorkflowProcessTest.class, 1);
	
	Route jiuhe = wp.createRoute("jiuhe");
	jiuhe.putAttribute("key1", "xialiang");
	jiuhe.addistener(WorkflowProcessTest.class, 1);

	Transition t4 = wp.createTransition(jinli1, jiuhe);
	t4.putAttribute("key1", "xialiang");
	t4.addistener(WorkflowProcessTest.class, 1);
	
	Transition t5 = wp.createTransition(jinli2, jiuhe);
	t5.putAttribute("key1", "xialiang");
	t5.addistener(WorkflowProcessTest.class, 1);
	
	Activity zongjili = wp.createActivity("zongjili");
	zongjili.putAttribute("key1", "xialiang");
	zongjili.addistener(WorkflowProcessTest.class, 1);
	zongjili.addActor(KeyUtils.newId(), ActivityActor.ActorTypes_User);
	zongjili.addActor(KeyUtils.newId(), ActivityActor.ActorTypes_Special);
	
	Transition t6 = wp.createTransition(jiuhe, zongjili);
	t6.putAttribute("key1", "xialiang");
	t6.addistener(WorkflowProcessTest.class, 1);
	
	EndNode nodeNode = wp.createEndNode("endNode");
	nodeNode.putAttribute("key1", "xialiang");
	nodeNode.addistener(WorkflowProcessTest.class, 1);
	
	Transition t7 = wp.createTransition(zongjili, nodeNode);
	t7.putAttribute("key1", "xialiang");
	t7.addistener(WorkflowProcessTest.class, 1);
	
	return wp;
    }
    
    @Test
    public void createWorkflowProcesstest() {
	
    }
    
}
