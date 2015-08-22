package org.kayura.bpm.storage.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.kayura.bpm.kernel.ActivityInstance;
import org.kayura.bpm.kernel.ProcessInstance;
import org.kayura.bpm.kernel.WorkItem;
import org.kayura.bpm.models.Activity;
import org.kayura.bpm.models.ActivityActor;
import org.kayura.bpm.models.BizForm;
import org.kayura.bpm.models.EndNode;
import org.kayura.bpm.models.Route;
import org.kayura.bpm.models.StartNode;
import org.kayura.bpm.models.Transition;
import org.kayura.bpm.models.WorkflowProcess;
import org.kayura.bpm.storage.IStorageService;
import org.kayura.bpm.storage.impl.mapper.DefineMapper;
import org.kayura.bpm.storage.impl.mapper.InstanceMapper;
import org.kayura.bpm.storage.impl.po.IdNodeType;
import org.kayura.utils.StringUtils;

public class StorageServiceImpl implements IStorageService {

	private DefineMapper defineMapper;
	private InstanceMapper instanceMapper;

	public StorageServiceImpl() {

	}

	public void setDefineMapper(DefineMapper defineMapper) {
		this.defineMapper = defineMapper;
	}

	public void setInstanceMapper(InstanceMapper instanceMapper) {
		this.instanceMapper = instanceMapper;
	}

	public WorkflowProcess getWorkflowProcess(String id) {

		WorkflowProcess workflowProcess = defineMapper.selectWorkflowProcessById(id);
		return workflowProcess;
	}

	public WorkflowProcess getWorkflowProcess(String flowCode, Integer version) {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("flowCode", flowCode);
		args.put("status", 1);
		if (version != null) {
			args.put("version", version);
		}

		WorkflowProcess workflowProcess = null;
		String id = defineMapper.getWorkflowProcessIdByMap(args);
		if (StringUtils.isEmpty(id)) {
			workflowProcess = defineMapper.selectWorkflowProcessById(id);
		}
		return workflowProcess;
	}

	public void syncWorkflowProcess(WorkflowProcess workflowProcess) {

		String processId = workflowProcess.getId();
		Boolean newProcess = defineMapper.workflowProcessExists(processId);

		// workflowProcess
		if (newProcess == true) {

			BizForm bizForm = workflowProcess.getBizForm();
			Integer maxValue = defineMapper.getWorkflowProcessMaxVersion(bizForm != null ? bizForm.getId() : null);
			workflowProcess.setVersion(maxValue != null ? (maxValue + 1) : 1);

			defineMapper.insertWorkflowProcess(workflowProcess);
		} else {
			defineMapper.updateWorkflowProcess(workflowProcess);
		}

		// idNodeTypes
		List<IdNodeType> idNodeTypes = defineMapper.findNodeIdsNodeType(processId);

		// startNode
		StartNode startNode = workflowProcess.getStartNode();
		boolean exists = idNodeTypes.stream().anyMatch(s -> s.getId().equals(startNode.getId()));
		if (exists) {
			idNodeTypes.removeIf(s -> s.getId().equals(startNode.getId()));
			defineMapper.updateNodeStartNode(startNode);
		} else {
			defineMapper.insertNodeStartNode(startNode);
		}

		// activity
		List<Activity> activities = workflowProcess.getActivities();
		for (Activity activity : activities) {
			exists = idNodeTypes.stream().anyMatch(s -> s.getId().equals(activity.getId()));
			if (exists) {
				idNodeTypes.removeIf(s -> s.getId().equals(activity.getId()));
				defineMapper.updateNodeActivity(activity);
			} else {
				defineMapper.insertNodeActivity(activity);
			}
		}

		// route
		List<Route> routes = workflowProcess.getRoutes();
		for (Route route : routes) {
			exists = idNodeTypes.stream().anyMatch(s -> s.getId().equals(route.getId()));
			if (exists) {
				idNodeTypes.removeIf(s -> s.getId().equals(route.getId()));
				defineMapper.updateNodeRoute(route);
			} else {
				defineMapper.insertNodeRoute(route);
			}
		}

		// endNode
		List<EndNode> endNodes = workflowProcess.getEndNodes();
		for (EndNode endNode : endNodes) {
			exists = idNodeTypes.stream().anyMatch(s -> s.getId().equals(endNode.getId()));
			if (exists) {
				idNodeTypes.removeIf(s -> s.getId().equals(endNode.getId()));
				defineMapper.updateNodeEndNode(endNode);
			} else {
				defineMapper.insertNodeEndNode(endNode);
			}
		}

		// ActivityActor
		List<String> activityIds = activities.stream().map(s -> s.getId()).collect(Collectors.toList());
		defineMapper.deleteActivityActorByActivityIds(activityIds);

		List<ActivityActor> actors = new ArrayList<ActivityActor>();
		activities.stream().map(s -> s.getActors()).forEach(a -> actors.addAll(a));

		for (ActivityActor actor : actors) {
			defineMapper.insertActivityActor(actor);
		}

		// transition
		List<Transition> transitions = workflowProcess.getTransitions();
		defineMapper.deleteTransitionByProcess(processId);
		for (Transition t : transitions) {
			defineMapper.insertTransition(t);
		}
	}

	@Override
	public void saveOrUpdateProcessInstance(ProcessInstance instance) {
		if (instanceMapper.processInstanceExists(instance.getId())) {
			instanceMapper.updateProcessInstance(instance);
		} else {
			instanceMapper.insertProcessInstance(instance);
		}
	}

	public void deleteProcessInstance(String id) {
		instanceMapper.deleteProcessInstance(id);
	}

	public void insertActivityInstance(ActivityInstance instance) {
		instanceMapper.insertActivityInstance(instance);
	}

	public void insertWorkItem(WorkItem workItem) {
		instanceMapper.insertWorkItem(workItem);
	}
}
