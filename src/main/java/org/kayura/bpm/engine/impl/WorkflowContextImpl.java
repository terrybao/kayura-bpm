package org.kayura.bpm.engine.impl;

import java.util.List;

import org.kayura.bpm.engine.IWorkflowContext;
import org.kayura.bpm.engine.IWorkflowContextAware;
import org.kayura.bpm.organize.IOrganizeService;
import org.kayura.bpm.storage.IStorageService;

public class WorkflowContextImpl implements IWorkflowContext {

	private IStorageService storageService;
	private IOrganizeService organizeService;

	public IStorageService getStorageService() {
		return storageService;
	}

	public void setStorageService(IStorageService storageService) {
		this.storageService = storageService;
	}

	public IOrganizeService getOrganizeService() {
		return organizeService;
	}

	public void setOrganizeService(IOrganizeService organizeService) {
		this.organizeService = organizeService;
	}

	public WorkflowContextImpl() {

	}

	public WorkflowContextImpl(IStorageService storageService, IOrganizeService organizeService) {
		this.storageService = storageService;
		this.organizeService = organizeService;
	}

	@Override
	public <T> T bind(T result) {
		if (result != null) {
			if (result instanceof IWorkflowContextAware) {
				((IWorkflowContextAware) result).setContext(this);
			}
			if (result instanceof List<?>) {
				List<?> items = (List<?>) result;
				for (Object o : items) {
					if (o instanceof IWorkflowContextAware) {
						((IWorkflowContextAware) o).setContext(this);
					}
				}
			}
		}
		return result;
	}

}
