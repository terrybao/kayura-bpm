package org.kayura.bpm.storage.impl.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.kayura.bpm.kernel.ActivityInstance;
import org.kayura.bpm.kernel.InstanceStatus;
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
	
	/**
	 * 查询活动实例指定条件的计数.
	 * 
	 * @param args 可选参数：
	 * <p> processInstanceId 所属的过程实例Id.
	 * <p> preActInstanceId 前一步活动实例Id.
	 * <p> status 活动实例的状态 {@link InstanceStatus}.
	 * @return 返回活动实例计数.
	 */
	Integer activityInstanceCountByMap(Map<String, Object> args);

	void deleteActivityInstance(String id);

	/* WorkItem */

	WorkItem getWorkItemById(String workItemId);

	WorkItem findWorkItemByFirst(String actorId);
	
	/**
	 * 查询指定组合条件的工作项集合.
	 * 
	 * @param args 可选参数:
	 * <p> activityInstanceId 活动实例Id.
	 * <p> sn 顺序号.
	 * <p> ownerId 所有者Id.
	 * <p> taskType 类型.
	 * <p> depth 深度.
	 * <p> status 状态.
	 * <p> parentId 父级工作项Id,当值为字符 null 时查询根级任务.
	 * @return 返回满足符合条件的工作项.
	 */
	List<WorkItem> findWorkItemsByMap(Map<String, Object> args);
	
	/**
	 * 查询下一个任务的处理序号.
	 * 
	 * @param activityInstanceId 活动实例id.
	 * @param sn 当前任务序号值.
	 * @return 返回下一个序号，若没有下一个任务，将返回 null.
	 */
	Integer findNextTaskSequence(@Param("activityInstanceId") String activityInstanceId,
			@Param("sn") Integer sn);

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
