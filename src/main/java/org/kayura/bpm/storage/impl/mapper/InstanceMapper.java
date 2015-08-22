package org.kayura.bpm.storage.impl.mapper;

import org.kayura.bpm.kernel.ActivityInstance;
import org.kayura.bpm.kernel.ProcessInstance;
import org.kayura.bpm.kernel.WorkItem;

public interface InstanceMapper {

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

	void insertWorkItem(WorkItem workItem);

}
