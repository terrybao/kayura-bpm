package org.kayura.bpm.builder;

import org.kayura.bpm.exceptions.WorkflowException;
import org.kayura.bpm.models.WorkflowProcess;
import org.kayura.xml.XDocument;

@SuppressWarnings("unused")
public class WorkflowProcessBuilder {

	private Integer convertType;
	private XDocument document;
	private WorkflowProcess workflowProcess;

	public WorkflowProcessBuilder(String xml) {
		this.document = new XDocument(xml);
	}

	public WorkflowProcessBuilder(WorkflowProcess workflowProcess) {
		this.workflowProcess = workflowProcess;
	}

	public WorkflowProcess parseWorkflowProcess() {

		return null;
	}

	public String parseXml() {

		return null;
	}
}
