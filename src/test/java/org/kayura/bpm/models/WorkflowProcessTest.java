package org.kayura.bpm.models;

import org.junit.Test;

public class WorkflowProcessTest {
    
    public static WorkflowProcess createWorkflowProcess() {
	
	WorkflowProcess wp = new WorkflowProcess(null, "master");
	StartNode startNode = wp.createStartNode();
	
	Activity bumen = wp.createActivity("bumen");
	Activity jinli = wp.createActivity("jinli");
	
	EndNode nodeNode = wp.createEndNode("endNode");
	
	wp.createTransition(startNode, bumen);
	wp.createTransition(bumen, jinli);
	wp.createTransition(jinli, nodeNode);
	
	return wp;
    }
    
    @Test
    public void createWorkflowProcesstest() {
	
    }
    
}
