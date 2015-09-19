package org.kayura.bpm.storage.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.kayura.bpm.kernel.ActivityInstance;
import org.kayura.bpm.kernel.ProcessInstance;
import org.kayura.bpm.kernel.WorkItem;
import org.kayura.bpm.kernel.WorkItem.TaskStatus;
import org.kayura.bpm.models.Activity;
import org.kayura.bpm.models.ActivityActor;
import org.kayura.bpm.models.BizForm;
import org.kayura.bpm.models.EndNode;
import org.kayura.bpm.models.Route;
import org.kayura.bpm.models.StartNode;
import org.kayura.bpm.models.Transition;
import org.kayura.bpm.models.WorkflowProcess;
import org.kayura.bpm.storage.IStorageService;
import org.kayura.bpm.storage.impl.mapper.StorageMapper;
import org.kayura.bpm.storage.impl.po.IdNodeType;
import org.kayura.bpm.types.TaskListItem;
import org.kayura.mybatis.type.PageBounds;
import org.kayura.type.PageList;
import org.kayura.type.PageParams;
import org.kayura.utils.StringUtils;

public class StorageServiceImpl implements IStorageService {

	private StorageMapper mapper;

	public StorageServiceImpl() {

	}

	public StorageServiceImpl(StorageMapper mapper) {
		this.mapper = mapper;
	}

	/* BizForm */

	@Override
	public BizForm getBizFormById(String id) {
		return mapper.getBizFormById(id);
	}

	@Override
	public PageList<BizForm> findBizForms(Map<String, Object> args, PageParams pageParams) {
		return mapper.findBizForms(args, new PageBounds(pageParams));
	}

	@Override
	public void saveOrUpdateBizForm(BizForm bizForm) {

		if (mapper.bizFormExists(bizForm.getId())) {
			mapper.updateBizForm(bizForm);
		} else {
			mapper.insertBizForm(bizForm);
		}
	}

	@Override
	public void updateBizFormForStatus(String id, Integer status) {
		mapper.updateBizFormForStatus(id, status);
	}

	/* WorkflowProcess */

	@Override
	public WorkflowProcess getWorkflowProcess(String id) {

		WorkflowProcess workflowProcess = mapper.selectWorkflowProcessById(id);
		return workflowProcess;
	}

	@Override
	public WorkflowProcess getWorkflowProcess(String flowCode, Integer version) {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("flowCode", flowCode);
		args.put("status", 1);
		if (version != null) {
			args.put("version", version);
		}

		WorkflowProcess workflowProcess = null;
		String id = mapper.getWorkflowProcessIdByMap(args);
		if (!StringUtils.isEmpty(id)) {
			workflowProcess = mapper.selectWorkflowProcessById(id);
		}
		return workflowProcess;
	}

	@Override
	public void syncWorkflowProcess(WorkflowProcess workflowProcess) {

		String processId = workflowProcess.getId();
		Boolean newProcess = mapper.workflowProcessExists(processId);

		// workflowProcess
		if (newProcess == true) {

			BizForm bizForm = workflowProcess.getBizForm();
			String bizFormId = bizForm != null ? bizForm.getId() : null;

			Integer maxValue = mapper.getWorkflowProcessMaxVersion(bizFormId);
			Integer newVersion = maxValue != null ? (maxValue + 1) : 1;

			workflowProcess.setVersion(newVersion);

			mapper.insertWorkflowProcess(workflowProcess);
		} else {
			mapper.updateWorkflowProcess(workflowProcess);
		}

		// idNodeTypes
		List<IdNodeType> idNodeTypes = mapper.findNodeIdsNodeType(processId);

		// startNode
		StartNode startNode = workflowProcess.getStartNode();
		boolean exists = idNodeTypes.stream().anyMatch(s -> s.getId().equals(startNode.getId()));
		if (exists) {
			idNodeTypes.removeIf(s -> s.getId().equals(startNode.getId()));
			mapper.updateNodeStartNode(startNode);
		} else {
			mapper.insertNodeStartNode(startNode);
		}

		// activity
		List<Activity> activities = workflowProcess.getActivities();
		for (Activity activity : activities) {
			exists = idNodeTypes.stream().anyMatch(s -> s.getId().equals(activity.getId()));
			if (exists) {
				idNodeTypes.removeIf(s -> s.getId().equals(activity.getId()));
				mapper.updateNodeActivity(activity);
			} else {
				mapper.insertNodeActivity(activity);
			}
		}

		// route
		List<Route> routes = workflowProcess.getRoutes();
		for (Route route : routes) {
			exists = idNodeTypes.stream().anyMatch(s -> s.getId().equals(route.getId()));
			if (exists) {
				idNodeTypes.removeIf(s -> s.getId().equals(route.getId()));
				mapper.updateNodeRoute(route);
			} else {
				mapper.insertNodeRoute(route);
			}
		}

		// endNode
		List<EndNode> endNodes = workflowProcess.getEndNodes();
		for (EndNode endNode : endNodes) {
			exists = idNodeTypes.stream().anyMatch(s -> s.getId().equals(endNode.getId()));
			if (exists) {
				idNodeTypes.removeIf(s -> s.getId().equals(endNode.getId()));
				mapper.updateNodeEndNode(endNode);
			} else {
				mapper.insertNodeEndNode(endNode);
			}
		}

		// ActivityActor
		List<String> activityIds = activities.stream().map(s -> s.getId())
				.collect(Collectors.toList());
		mapper.deleteActivityActorByActivityIds(activityIds);

		List<ActivityActor> actors = new ArrayList<ActivityActor>();
		activities.stream().map(s -> s.getActors()).forEach(a -> actors.addAll(a));

		for (ActivityActor actor : actors) {
			mapper.insertActivityActor(actor);
		}

		// transition
		List<Transition> transitions = workflowProcess.getTransitions();
		mapper.deleteTransitionByProcess(processId);
		for (Transition t : transitions) {
			mapper.insertTransition(t);
		}
	}

	/* ProcessInstance */

	@Override
	public void saveOrUpdateProcessInstance(ProcessInstance instance) {
		if (mapper.processInstanceExists(instance.getId())) {
			mapper.updateProcessInstance(instance);
		} else {
			mapper.insertProcessInstance(instance);
		}
	}

	@Override
	public ProcessInstance findProcessInstance(String bizFlowCode, String bizDataId) {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("bizFlowCode", bizFlowCode);
		args.put("bizDataId", bizDataId);

		List<ProcessInstance> list = mapper.findProcessInstanceByMap(args);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public void deleteProcessInstance(String id) {
		mapper.deleteProcessInstance(id);
	}

	/* ActivityInstance */

	@Override
	public void insertActivityInstance(ActivityInstance instance) {
		mapper.insertActivityInstance(instance);
	}

	@Override
	public void updateActivityInstance(ActivityInstance instance) {
		mapper.updateActivityInstance(instance);
	}

	@Override
	public Integer activityInstanceCount(String processInstanceId, Integer[] status) {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("processInstanceId", processInstanceId);
		args.put("status", StringUtils.join(",", status));

		Integer count = mapper.activityInstanceCountByMap(args);
		return count;
	}

	/* WorkItem */

	@Override
	public WorkItem getWorkItemById(String workItemId) {
		WorkItem workItem = mapper.getWorkItemById(workItemId);
		return workItem;
	}

	@Override
	public WorkItem findWorkItemByFirst(String processInstanceId, String actorId,
			Integer[] status) {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("processInstanceId", processInstanceId);
		args.put("actorId", actorId);
		args.put("status", StringUtils.join(",", status));

		WorkItem workItem = mapper.findWorkItemByFirst(args);
		return workItem;
	}

	public PageList<TaskListItem> findWorkItems(String keyword, String actorId, String status,
			Integer pageNum, Integer pageSize) {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("keyword", keyword);
		args.put("actorId", actorId);
		args.put("status", status);

		PageList<TaskListItem> list = mapper.findWorkItems(args, new PageBounds(pageNum, pageSize));
		return list;
	}

	public List<WorkItem> findWorkItemsBySn(String activityInstanceId, Integer sn) {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("activityInstanceId", activityInstanceId);
		args.put("sn", sn);
		args.put("parentId", "null");

		List<WorkItem> list = mapper.findWorkItemsByMap(args);
		return list;
	}

	@Override
	public Integer findNextTaskSequence(String activityInstanceId, Integer currentSn) {

		Integer nextSn = mapper.findNextTaskSequence(activityInstanceId, currentSn);
		return nextSn;
	}

	@Override
	public void insertWorkItem(WorkItem workItem) {
		mapper.insertWorkItem(workItem);
	}

	@Override
	public void updateWorkItemStatus(WorkItem workItem) {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("status", workItem.getStatus());
		args.put("id", workItem.getId());

		mapper.updateWorkItemByMap(args);
	}

	@Override
	public void completedWorkItem(WorkItem workItem) {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("handlerId", workItem.getHandler().getId());
		args.put("comment", workItem.getComment());
		args.put("completedTime", workItem.getCompletedTime());
		args.put("status", workItem.getStatus());
		args.put("id", workItem.getId());

		mapper.updateWorkItemByMap(args);
	}

	@Override
	public Integer childTaskCount(String parentId, Integer[] status) {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("parentId", parentId);
		args.put("taskType", 0);
		if (status.length > 0) {
			args.put("status", StringUtils.join(",", status));
		}

		Integer count = mapper.workItemCountByMap(args);
		return count;
	}

	@Override
	public Integer taskCountByActivity(String activityInstanceId, Integer sn, Integer[] status) {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("activityInstanceId", activityInstanceId);
		args.put("taskType", 0);

		if (sn != null) {
			args.put("sn ", sn);
		}

		if (status.length > 0) {
			args.put("status", StringUtils.join(",", status));
		}

		Integer count = mapper.workItemCountByMap(args);
		return count;
	}

}
