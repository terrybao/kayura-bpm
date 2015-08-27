package org.kayura.bpm.builder;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.kayura.bpm.exceptions.WorkflowException;
import org.kayura.bpm.models.Activity;
import org.kayura.bpm.models.ActivityActor;
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
import org.kayura.utils.PingYinUtils;
import org.kayura.utils.StringUtils;
import org.kayura.xml.XDocument;
import org.kayura.xml.XNode;

public class WorkflowProcessBuilder {

	private XDocument document;
	private WorkflowProcess workflowProcess;

	public WorkflowProcessBuilder(String xml) {
		this.document = new XDocument(xml);
	}

	public WorkflowProcessBuilder(InputStream inputStream) {
		this.document = new XDocument(inputStream);
	}

	public WorkflowProcessBuilder(WorkflowProcess workflowProcess) {
		this.workflowProcess = workflowProcess;
	}

	public WorkflowProcess parseWorkflowProcess() {

		this.workflowProcess = new WorkflowProcess();
		XNode root = this.document.evalNode("/workflowProcess");

		if (root != null) {
			parseProcess(root);
			parseStartNode(root.evalNode("startNode"));
			parseEndNodes(root.evalNode("endNodes"));
			parseActivities(root.evalNode("activities"));
			parseRoutes(root.evalNode("routes"));
			parseTransitions(root.evalNode("transitions"));
		}

		return this.workflowProcess;
	}

	private void parseProcess(XNode root) {

		parseElement(root, this.workflowProcess);

	}

	private void parseStartNode(XNode context) {

		if (context != null) {
			StartNode startNode = this.workflowProcess.createStartNode();
			parseElement(context, startNode);
		}
	}

	private void parseEndNodes(XNode context) {

		if (context != null) {
			for (XNode child : context.getChildren()) {
				String code = child.getStringAttribute("code");
				EndNode endNode = this.workflowProcess.createEndNode(code);
				parseElement(child, endNode);
			}
		}
	}

	private void parseActivities(XNode context) {

		if (context != null) {

			for (XNode child : context.getChildren()) {

				String code = child.getStringAttribute("code");
				Activity activity = this.workflowProcess.createActivity(code);
				parseElement(child, activity);

				// others
				Integer handleType = child.getIntAttribute("handleType");
				if (handleType != null) {
					activity.setHandleType(handleType);
				}

				// actors
				XNode actorNodes = child.evalNode("actors");
				if (actorNodes != null) {
					for (XNode actorNode : actorNodes.getChildren()) {

						String actorId = actorNode.getStringAttribute("actorId");
						Integer actorType = actorNode.getIntAttribute("actorType");

						if (!StringUtils.isEmpty(actorId) && actorType != null) {
							activity.addActor(actorId, actorType);
						}
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
				parseElement(child, route);
			}
		}
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
						parseElement(child, transition);
					}
				}
			}
		}
	}

	private void parseElement(XNode context, WfElement element) {

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
			if (StringUtils.isEmpty(createdTime)) {
				element.setCreatedTime(DateUtils.now());
			} else {
				Date ct = DateUtils.formatDate(createdTime);
				if (ct != null) {
					element.setCreatedTime(ct);
				} else {
					element.setCreatedTime(DateUtils.now());
				}
			}

			String modifiedTime = context.getStringAttribute("modifiedTime");
			if (StringUtils.isEmpty(modifiedTime)) {
				element.setCreatedTime(DateUtils.now());
			} else {
				Date mt = DateUtils.formatDate(modifiedTime);
				if (mt != null) {
					element.setModifiedTime(mt);
				} else {
					element.setModifiedTime(DateUtils.now());
				}
			}

			XNode attributes = context.evalNode("properties");
			if (attributes != null) {
				for (XNode child : attributes.getChildren()) {

					String attName = child.getStringAttribute("name");
					String attValue = child.getStringAttribute("value");

					element.putAttribute(attName, attValue);
				}
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

	public String formatXml() {

		this.document = new XDocument();
		XNode root = this.document.createChildNode("workflowProcess");

		formatProcess(root);
		formatStartNode(root);
		formatEndNodes(root);
		formatActivities(root);
		formatRoutes(root);
		formatTransitions(root);

		return this.document.toString();
	}

	private void formatTransitions(XNode root) {

		if (root != null) {

			List<Transition> transitions = this.workflowProcess.getTransitions();
			if (transitions.size() > 0) {

				XNode nodes = root.createChildNode("transitions");
				for (Transition transition : transitions) {

					Node fromNode = transition.getFromNode();
					Node toNode = transition.getToNode();

					XNode cnode = nodes.createChildNode("transition");

					cnode.setAttribute("from", fromNode.getCode());
					cnode.setAttribute("to", toNode.getCode());

					formatElement(transition, cnode);
				}
			}
		}
	}

	private void formatRoutes(XNode root) {

		if (root != null) {

			List<Route> routes = this.workflowProcess.getRoutes();
			if (routes.size() > 0) {

				XNode nodes = root.createChildNode("routes");
				for (Route route : routes) {

					XNode cnode = nodes.createChildNode("route");
					formatElement(route, cnode);
				}
			}
		}
	}

	private void formatActivities(XNode root) {

		if (root != null) {

			List<Activity> activities = this.workflowProcess.getActivities();
			if (activities.size() > 0) {

				XNode nodes = root.createChildNode("activities");
				for (Activity activity : activities) {

					XNode cnode = nodes.createChildNode("activity");
					formatElement(activity, cnode);

					// others
					Integer handleType = activity.getHandleType();
					cnode.setAttribute("handleType", handleType);

					// actors
					List<ActivityActor> actors = activity.getActors();
					if (actors.size() > 0) {

						XNode actorNodes = cnode.createChildNode("actors");
						for (ActivityActor actor : actors) {

							String actorId = actor.getActorId();
							Integer actorType = actor.getActorType();

							if (!StringUtils.isEmpty(actorId) && actorType != null) {

								XNode actorNode = actorNodes.createChildNode("actor");
								actorNode.setAttribute("actorId", actorId);
								actorNode.setAttribute("actorType", actorType);
							}
						}
					}
				}
			}
		}
	}

	private void formatEndNodes(XNode root) {

		if (root != null) {

			List<EndNode> endNodes = this.workflowProcess.getEndNodes();
			if (endNodes.size() > 0) {

				XNode nodes = root.createChildNode("endNodes");
				for (EndNode endNode : endNodes) {

					XNode cnode = nodes.createChildNode("endNode");
					formatElement(endNode, cnode);
				}
			}
		}
	}

	private void formatStartNode(XNode root) {

		if (root != null) {

			StartNode startNode = this.workflowProcess.getStartNode();
			XNode node = root.createChildNode("startNode");

			formatElement(startNode, node);
		}
	}

	private void formatProcess(XNode root) {
		formatElement(this.workflowProcess, root);
	}

	private void formatElement(WfElement element, XNode context) {

		if (element != null && context != null) {

			// code or name
			String code = element.getCode();
			String name = element.getName();

			if (StringUtils.isEmpty(name)) {
				throw new WorkflowException("存在未定义名字的环节。");
			}

			context.setAttribute("name", name);

			if (!StringUtils.isEmpty(code)) {
				context.setAttribute("code", code);
			} else {
				String pyName = PingYinUtils.convert(name);
				context.setAttribute("code", pyName);
			}

			// description
			String description = element.getDescription();
			if (!StringUtils.isEmpty(description)) {
				context.createChildNode("description", description);
			}

			// listeners
			List<Listener> listeners = element.getListeners();
			if (listeners.size() > 0) {
				XNode lstNode = context.createChildNode("listeners");

				for (Listener listener : listeners) {

					String className = listener.getClassName();
					Integer sn = listener.getSn();
					String lstDesc = listener.getDescription();

					if (!StringUtils.isEmpty(className)) {

						XNode listenerNode = lstNode.createChildNode("listener");

						listenerNode.setAttribute("className", className);
						if (sn != null) {
							listenerNode.setAttribute("sn", sn);
						}

						if (!StringUtils.isEmpty(lstDesc)) {
							listenerNode.setAttribute("description", lstDesc);
						}
					}
				}
			}

			// properties
			Properties attributes = element.getAttributes();
			if (attributes.size() > 0) {

				XNode nodes = context.createChildNode("properties");
				for (String key : attributes.stringPropertyNames()) {

					String value = attributes.getProperty(key);
					XNode node = nodes.createChildNode("property");
					node.setAttribute("name", key);
					node.setAttribute("value", value);
				}
			}
		}

	}
}
