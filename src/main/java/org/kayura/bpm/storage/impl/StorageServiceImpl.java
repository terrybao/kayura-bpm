package org.kayura.bpm.storage.impl;

import java.util.HashMap;
import java.util.Map;

import org.kayura.bpm.models.WorkflowProcess;
import org.kayura.bpm.storage.IStorageService;
import org.kayura.bpm.storage.impl.mapper.DefineMapper;

public class StorageServiceImpl implements IStorageService {
    
    private DefineMapper defineMapper;
    
    public WorkflowProcess getWorkflowProcess(String id) {
	
	Map<String, Object> args = new HashMap<String, Object>();
	args.put("id", id);
	
	WorkflowProcess workflowProcess = defineMapper.getWorkflowProcess(args);
	return workflowProcess;
    }
    
    public WorkflowProcess getWorkflowProcess(String flowCode, Integer version) {
	
	Map<String, Object> args = new HashMap<String, Object>();
	args.put("flowCode", flowCode);
	args.put("status", 1);
	if (version != null) {
	    args.put("version", version);
	}
	
	WorkflowProcess workflowProcess = defineMapper.getWorkflowProcess(args);
	return workflowProcess;
    }
    
    public void saveOrUpdateWorkflowProcess(WorkflowProcess workflowProcess) {
	
    }
}
