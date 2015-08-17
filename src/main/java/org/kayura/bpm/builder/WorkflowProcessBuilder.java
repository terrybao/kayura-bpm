package org.kayura.bpm.builder;

import org.kayura.bpm.exceptions.WorkflowException;
import org.kayura.bpm.models.WorkflowProcess;
import org.kayura.xml.XDocument;

@SuppressWarnings("unused")
public class WorkflowProcessBuilder {
    
    private final static Integer XMLTOOBJ = 0;
    private final static Integer OBJTOXML = 1;
    
    private Integer convertType;
    private XDocument document;
    private WorkflowProcess workflowProcess;
    
    public WorkflowProcessBuilder(String xml) {
	this.document = new XDocument(xml);
	this.convertType = XMLTOOBJ;
    }
    
    public WorkflowProcessBuilder(WorkflowProcess workflowProcess) {
	this.workflowProcess = workflowProcess;
	this.convertType = OBJTOXML;
    }
    
    public WorkflowProcess parseWorkflowProcess() {
	
	if (this.convertType != XMLTOOBJ) {
	    throw new WorkflowException("");
	}
	
	return null;
    }
    
    public String parseXml() {
	
	if (this.convertType != OBJTOXML) {
	    throw new WorkflowException("");
	}
	
	return null;
    }
}
