package org.kayura.bpm.builder;

import java.util.Date;
import java.util.Properties;

import org.kayura.bpm.exceptions.WorkflowException;
import org.kayura.bpm.models.Activity;
import org.kayura.bpm.models.EndNode;
import org.kayura.bpm.models.Listener;
import org.kayura.bpm.models.Node;
import org.kayura.bpm.models.Route;
import org.kayura.bpm.models.StartNode;
import org.kayura.bpm.models.Transition;
import org.kayura.bpm.models.WfElement;
import org.kayura.bpm.models.WorkflowProcess;
import org.kayura.utils.DateUtils;
import org.kayura.utils.KeyUtils;
import org.kayura.utils.StringUtils;
import org.kayura.xml.XDocument;
import org.kayura.xml.XNode;

@SuppressWarnings("unused")
public class WorkflowProcessBuilder {

	private Integer convertType;
	private XDocument document;
	private WorkflowProcess workflowProcess;

	public WorkflowProcessBuilder(String xml) {
		this.document = new XDocument(xml);
		this.workflowProcess = new WorkflowProcess();
	}

	public WorkflowProcessBuilder(WorkflowProcess workflowProcess) {
		this.workflowProcess = workflowProcess;
	}

	public WorkflowProcess parseWorkflowProcess() {

		XNode root = this.document.evalNode("/workflowProcess");

		parseWorkflowElement(root, this.workflowProcess);
		parseStartNode(root.evalNode("startNode"));
		parseEndNodes(root.evalNode("endNodes"));
		parseActivities(root.evalNode("activities"));
		parseRoutes(root.evalNode("routes"));
		parseRoutes(root.evalNode("routes"));
		parseTransitions(root.evalNode("transitions"));

		return this.workflowProcess;
	}

	private void parseTransitions(XNode context) {

		if (context != null) {
			for (XNode child : context.getChildren()) {

				String to = child.getStringAttribute("to");
				String from = child.getStringAttribute("from");

				if (!StringUtils.isEmpty(to) && !StringUtils.isEmpty(from)) {
					WorkflowProcess process = this.workflowProcess;

					Node toNode = process.findNodeByCode(to);
					Node fromNode = process.findNodeByCode(from);

					if (toNode != null && fromNode != null) {
						Transition transition = process.createTransition(fromNode, toNode);
						parseWorkflowElement(context, transition);
					}
				}
			}
		}
	}

	private void parseRoutes(XNode context) {

		if (context != null) {
			for (XNode child : context.getChildren()) {
				String code = child.getStringAttribute("code");
				Route route = this.workflowProcess.createRoute(code);
				parseWorkflowElement(context, route);
			}
		}
	}

	private void parseActivities(XNode context) {

		if (context != null) {
			for (XNode child : context.getChildren()) {
				String code = child.getStringAttribute("code");
				Activity activity = this.workflowProcess.createActivity(code);
				parseWorkflowElement(context, activity);

			}
		}
	}

	private void parseEndNodes(XNode context) {

		if (context != null) {
			for (XNode child : context.getChildren()) {
				String code = child.getStringAttribute("code");
				EndNode endNode = this.workflowProcess.createEndNode(code);
				parseWorkflowElement(context, endNode);
			}
		}
	}

	private void parseStartNode(XNode context) {

		if (context != null) {
			StartNode startNode = this.workflowProcess.createStartNode();
			parseWorkflowElement(context, startNode);
		}
	}

	private void parseWorkflowElement(XNode context, WfElement element) {

		if (context != null) {

			String id = context.getStringAttribute("id");
			if (StringUtils.isEmpty(id)) {
				id = KeyUtils.newId();
			}
			element.setId(id);

			String code = context.getStringAttribute("code");
			element.setCode(code);

			String name = context.getStringAttribute("name", code);
			element.setName(name);

			String description = context.evalString("description");
			if (!StringUtils.isEmpty(description)) {
				element.setDescription(description);
			}

			String createdTime = context.getStringAttribute("createdTime");
			if (!StringUtils.isEmpty(createdTime)) {
				Date ct = DateUtils.formatDate(createdTime);
				if (ct != null) {
					element.setCreatedTime(ct);
				} else {
					element.setCreatedTime(DateUtils.now());
				}
			}

			String modifiedTime = context.getStringAttribute("modifiedTime");
			if (!StringUtils.isEmpty(modifiedTime)) {
				Date mt = DateUtils.formatDate(modifiedTime);
				if (mt != null) {
					element.setModifiedTime(mt);
				} else {
					element.setModifiedTime(DateUtils.now());
				}
			}

			Properties attributes = context.getChildrenAsProperties();
			if (!attributes.isEmpty()) {
				element.setAttributes(attributes);
			}

			XNode listeners = context.evalNode("listeners");
			if (listeners != null) {
				for (XNode child : listeners.getChildren()) {

					String className = child.getStringAttribute("className");
					Integer sn = child.getIntAttribute("sn", 0);
					String lstDesc = child.getStringAttribute("description");

					if (!StringUtils.isEmpty(className)) {
						Listener listener = element.addistener(className, sn);
						listener.setDescription(lstDesc);
					}
				}
			}

		}

	}

	public String parseXml() {

		return null;
	}
}
