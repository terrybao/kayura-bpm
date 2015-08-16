package org.kayura.bpm.storage.impl.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.kayura.bpm.models.BizForm;
import org.kayura.bpm.models.WorkflowProcess;
import org.kayura.mybatis.type.PageBounds;
import org.kayura.type.PageList;

public interface DefineMapper {
    
    /* >>>>>>>>>>>>>>> BizForm <<<<<<<<<<<<< */
    
    BizForm getBizFormById(String id);
    
    PageList<BizForm> findBizForms(Map<String, Object> args, PageBounds pageBouds);
    
    void insertBizForm(BizForm bizForm);
    
    void updateBizForm(BizForm bizForm);
    
    void updateBizFormForStatus(@Param("id") String id, @Param("status") Integer status);
    
    void deleteBizFormById(String id);
    
    /* >>>>>>>>>>>>>> WorkflowProcess <<<<<<<<<<<<< */
    
    WorkflowProcess getWorkflowProcess(Map<String, Object> args);
    
    Boolean workflowProcessExists(String id);
    
    void insertWorkflowProcess(WorkflowProcess workflowProcess);
    
    void updateWorkflowProcess(WorkflowProcess workflowProcess);
    
    void updateWorkflowProcessForStatus(@Param("id") String id, @Param("status") Integer status);
    
    void deleteWorkflowProcessById(String id);
    
    void deleteWorkflowProcessByBizFromId(String bizFromId);
}
