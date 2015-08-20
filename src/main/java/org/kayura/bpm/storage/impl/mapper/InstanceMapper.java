package org.kayura.bpm.storage.impl.mapper;

import org.kayura.bpm.kernel.ProcessInstance;

public interface InstanceMapper {
    
    void insertProcessInstance(ProcessInstance instance);
}
