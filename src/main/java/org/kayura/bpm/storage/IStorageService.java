package org.kayura.bpm.storage;

import org.kayura.bpm.kernel.ProcessInstance;
import org.kayura.bpm.models.WorkflowProcess;

/**
 * 工作流系统存储服务接口.
 * 
 * @author liangxia@live.com
 *
 */
public interface IStorageService {
    
    // 工作流定义存储接口.
    
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
}
