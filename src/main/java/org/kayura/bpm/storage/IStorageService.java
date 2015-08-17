package org.kayura.bpm.storage;

import org.kayura.bpm.models.WorkflowProcess;

public interface IStorageService {
    
    WorkflowProcess getWorkflowProcess(String id);
    
    WorkflowProcess getWorkflowProcess(String flowCode, Integer version);

    void saveOrUpdateWorkflowProcess(WorkflowProcess workflowProcess);

    void syncWorkflowProcess(WorkflowProcess workflowProcess);
    
}
