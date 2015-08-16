package org.kayura.bpm.storage.impl.mapper;

import java.util.Map;

import org.kayura.bpm.models.WorkflowProcess;

public interface DefineMapper {
    WorkflowProcess getWorkflowProcessByMap(Map<String, Object> args);
    WorkflowProcess getWorkflowProcessById(String id);
}
