package org.kayura.bpm.storage.impl.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
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
import org.kayura.bpm.storage.impl.po.IdNodeType;
import org.kayura.bpm.types.TaskListItem;
import org.kayura.mybatis.type.PageBounds;
import org.kayura.type.PageList;

public interface StorageMapper {

	/* BizForm */

	BizForm getBizFormById(String id);

	Boolean bizFormExists(String id);

	PageList<BizForm> findBizForms(Map<String, Object> args, PageBounds pageBouds);

	void insertBizForm(BizForm bizForm);

	void updateBizForm(BizForm bizForm);

	void updateBizFormForStatus(@Param("id") String id, @Param("status") Integer status);

	void deleteBizFormById(String id);

	/* WorkflowProcess */

	String getWorkflowProcessIdByMap(Map<String, Object> args);

	WorkflowProcess selectWorkflowProcessById(String processId);

	Boolean workflowProcessExists(String id);

	Integer getWorkflowProcessMaxVersion(String bizFlowId);

	void insertWorkflowProcess(WorkflowProcess workflowProcess);

	void updateWorkflowProcess(WorkflowProcess workflowProcess);

	void updateWorkflowProcessForStatus(@Param("id") String id, @Param("status") Integer status);

	void deleteWorkflowProcessById(String id);

	void deleteWorkflowProcessByBizFromId(String bizFromId);

	/* Nodes */

	List<IdNodeType> findNodeIdsNodeType(String processId);

	void insertNodeStartNode(StartNode startNode);

	void insertNodeActivity(Activity activity);

	void insertNodeRoute(Route route);

	void insertNodeEndNode(EndNode endNode);

	void updateNodeStartNode(StartNode startNode);

	void updateNodeActivity(Activity activity);

	void updateNodeRoute(Route route);

	void updateNodeEndNode(EndNode endNode);

	void deleteNodeByIds(List<String> ids);

	/* Transition */

	List<String> findTransitionIds(String processId);

	void insertTransition(Transition transition);

	void deleteTransitionByProcess(String processId);

	/* ActivityActor */

	List<String> findActivityActorIds(String activityId);

	void insertActivityActor(ActivityActor actor);

	void deleteActivityActorByActivityIds(List<String> ids);

	/* ProcessInstance */

	ProcessInstance getProcessInstanceById(String id);

	boolean processInstanceExists(String id);

	void insertProcessInstance(ProcessInstance instance);

	void updateProcessInstance(ProcessInstance instance);

	void deleteProcessInstance(String id);

	/* ActivityInstance */

	void insertActivityInstance(ActivityInstance instance);

	void updateActivityInstance(ActivityInstance instance);

	void deleteActivityInstance(String id);

	/* WorkItem */

	WorkItem getWorkItemById(String workItemId);

	WorkItem findWorkItemByFirst(String actorId);

	void insertWorkItem(WorkItem workItem);

	PageList<TaskListItem> findWorkItems(Map<String, Object> args, PageBounds pageBounds);

	/**
	 * 动态更新指定列的工作项.
	 * 
	 * @param args 可更新列键值.
	 * <p>priority,alarmTime,sn,handlerId,comment,completedTime,status 最少需要指定一个.
	 * <p>id 为必需参数，工作项主键值.
	 */
	void updateWorkItemByMap(Map<String, Object> args);

	/**
	 * 根据条件查询出符合条件的工作项计数.
	 * 
	 * @param args 可选条件参数:
	 * <p>activityInstanceId 活动实例 Id
	 * <p>sn 指定的处理顺号
	 * <p>excludeId 排除掉的工作项Id 
	 * <p>parentId 父级工作项Id
	 * <p>taskType 工作项类型.
	 * <p>status 工作项状态.
	 * @return 返回工作项计数.
	 */
	Integer workItemCountByMap(Map<String, Object> args);
}
