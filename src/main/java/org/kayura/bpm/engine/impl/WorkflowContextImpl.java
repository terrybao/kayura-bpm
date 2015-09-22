package org.kayura.bpm.engine.impl;

import java.util.List;

import org.kayura.bpm.engine.WorkflowContext;
import org.kayura.bpm.engine.WorkflowContextAware;
import org.kayura.bpm.organize.OrganizeService;
import org.kayura.bpm.storage.StorageService;

public class WorkflowContextImpl implements WorkflowContext {

	private StorageService storageService;
	private OrganizeService organizeService;

	public StorageService getStorageService() {
		return storageService;
	}

	public void setStorageService(StorageService storageService) {
		this.storageService = storageService;
	}

	public OrganizeService getOrganizeService() {
		return organizeService;
	}

	public void setOrganizeService(OrganizeService organizeService) {
		this.organizeService = organizeService;
	}

	public WorkflowContextImpl() {

	}

	public WorkflowContextImpl(StorageService storageService, OrganizeService organizeService) {
		this.storageService = storageService;
		this.organizeService = organizeService;
	}

	@Override
	public <T> T bind(T result) {
		if (result != null) {
			if (result instanceof WorkflowContextAware) {
				((WorkflowContextAware) result).setContext(this);
			}
			if (result instanceof List<?>) {
				List<?> items = (List<?>) result;
				for (Object o : items) {
					if (o instanceof WorkflowContextAware) {
						((WorkflowContextAware) o).setContext(this);
					}
				}
			}
		}
		return result;
	}

}
