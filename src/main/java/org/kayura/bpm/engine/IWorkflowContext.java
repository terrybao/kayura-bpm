package org.kayura.bpm.engine;

import org.kayura.bpm.organize.IOrganizeService;
import org.kayura.bpm.storage.IStorageService;

public interface IWorkflowContext {
    
    IStorageService getStorageService();
    
    IOrganizeService getOrganizeService();
}
