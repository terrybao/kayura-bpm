package org.kayura.bpm.storage;

import java.util.List;
import java.util.Map;

import org.kayura.bpm.kernel.ActivityInstance;
import org.kayura.bpm.kernel.ProcessInstance;
import org.kayura.bpm.kernel.WorkItem;
import org.kayura.bpm.kernel.WorkItem.TaskStatus;
import org.kayura.bpm.models.BizForm;
import org.kayura.bpm.models.DefineStatus;
import org.kayura.bpm.models.WorkflowProcess;
import org.kayura.type.PageList;
import org.kayura.type.PageParams;

/**
 * 工作流系统存储服务接口.
 * 
 * @author liangxia@live.com
 *
 */
public interface IStorageService {

	// **************** 工作流表单存储接口 ***********************

	/**
	 * 获取一个工作流表单信息对象.
	 * 
	 * @param id 工作流表单Id.
	 * @return 返回获取的结果.
	 */
	BizForm getBizFormById(String id);

	/**
	 * 查询符件条件的工作流表单列表.
	 * 
	 * @param args 条件参数.
	 * @param pageParams 分页参数.
	 * @return 返回符合条件的列表.
	 */
	PageList<BizForm> findBizForms(Map<String, Object> args, PageParams pageParams);

	/**
	 * 保存或更新一个工作流表单定义信息.
	 * 
	 * @param bizForm 工作流表单对象.
	 */
	void saveOrUpdateBizForm(BizForm bizForm);

	/**
	 * 仅用于更新工作流表单的状态信息.
	 * 
	 * @param id 工作流表单Id.
	 * @param status 状态类型.{@link DefineStatus }
	 */
	void updateBizFormForStatus(String id, Integer status);

	// ****************** 工作流定义存储接口 **********************

	/**
	 * 获取一个工作流的完整定义信息.
	 * 
	 * @param id 工作流定义Id.
	 * @return 返回包括所有环节、连接线、活动参与者的流程定义信息对象.
	 */
	WorkflowProcess getWorkflowProcess(String id);

	/**
	 * 获取一个工作流的完整定义信息.
	 * 
	 * @param flowCode 工作流单据编号.
	 * @param version 工作流版本信息，该值为null时，为最新版本号.
	 * @return 返回包括所有环节、连接线、活动参与者的流程定义信息对象.
	 */
	WorkflowProcess getWorkflowProcess(String flowCode, Integer version);

	/**
	 * 用于将一个工作流完整定义信息同步存储到数据库中.
	 * 
	 * @param workflowProcess 包含完整工作流定义对象.
	 */
	void syncWorkflowProcess(WorkflowProcess workflowProcess);

	// 工作流实例存储接口.

	/**
	 * 新增或更新工作流过程实例信息.
	 * 
	 * @param instance 工作流过程实例对象.
	 */
	void saveOrUpdateProcessInstance(ProcessInstance instance);

	/**
	 * 查询一个工作流实例对象.
	 * 
	 * @param bizFlowCode 工作流单据编号.
	 * @param bizDataId 绑定的业务表单Id.
	 * @return 若存在返回 实例对象，否则返回 null.
	 */
	ProcessInstance findProcessInstance(String bizFlowCode, String bizDataId);

	/**
	 * 删除一个工作流实例，并一同删除所有相关联的实例信息.
	 * 
	 * @param id 工作流实例Id.
	 */
	void deleteProcessInstance(String id);

	/**
	 * 向数据库中插入一条新的活动实例.
	 * 
	 * @param instance 活动实例对象.
	 */
	void insertActivityInstance(ActivityInstance instance);

	/**
	 * 完成一个活动实例状态.
	 * 
	 * @param activityInstance
	 */
	void updateActivityInstance(ActivityInstance activityInstance);

	/**
	 * 
	 * @param processInstanceId
	 * @param status
	 * @return
	 */
	Integer activityInstanceCount(String processInstanceId, Integer[] status);

	/**
	 * 保存一个新的工作项至数据库.
	 * 
	 * @param workItem 工作项对象.
	 */
	void insertWorkItem(WorkItem workItem);

	/**
	 * 查询参与者的第一条待办.
	 * 
	 * @param processInstanceId 过程实例Id.
	 * @param actorId 参与者 Id.
	 * @param status 指定任务状态 {@link TaskStatus}
	 * @return 返回该工作项对象.
	 */
	WorkItem findWorkItemByFirst(String processInstanceId, String actorId, Integer[] status);

	/**
	 * 
	 * @param activityInstanceId
	 * @param sn
	 * @return
	 */
	List<WorkItem> findWorkItemsBySn(String activityInstanceId, Integer sn);

	/**
	 * 查找某个环节任务中的下一个序号.
	 * 
	 * @param activityInstance 活动实例Id.
	 * @param sn 当前序号值.
	 * @return 返回下一个序号.
	 */
	Integer findNextTaskSequence(String activityInstance, Integer sn);

	/**
	 * 获取一个工作项.
	 * 
	 * @param workItemId 工作项 Id.
	 * @return 返回该工作项对象.
	 */
	WorkItem getWorkItemById(String workItemId);

	/**
	 * 更新一个工作项状态.
	 * 
	 * @param workItem
	 */
	void updateWorkItemStatus(WorkItem workItem);

	/**
	 * 完成一个工作项任务.
	 * 
	 * @param workItem 工作项任务对象.
	 */
	void completedWorkItem(WorkItem workItem);

	/**
	 * 查询指定状态的子工作项计数.
	 * 
	 * @param parentWorkItemId 父级任务 Id.
	 * @param status 可指定的多个状态 {@link TaskStatus}.
	 * @return 返回工作项计数.
	 */
	Integer childTaskCount(String parentId, Integer[] status);

	/**
	 * 检查某个活动下的工作项是否均已经完成.
	 * 
	 * @param activityInstanceId 活动实例Id.
	 * @return 如果均已经完成返回 true, 否则返回 false.
	 */
	Integer taskCountByActivity(String activityInstanceId, Integer sn, Integer[] status);

}
