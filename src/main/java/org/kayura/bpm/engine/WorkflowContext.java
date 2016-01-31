/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.engine;

import org.kayura.bpm.organize.OrganizeService;
import org.kayura.bpm.storage.StorageService;

public interface WorkflowContext {
    
    StorageService getStorageService();
    
    OrganizeService getOrganizeService();
    
    <T> T bind(T o);
}
