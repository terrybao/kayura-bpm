/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.engine.executor;

import java.util.List;

import org.kayura.bpm.engine.WorkflowContext;
import org.kayura.bpm.engine.WorkflowContextAware;

public abstract class Executor<TResult> {

	private WorkflowContext context;

	public TResult execute(WorkflowContext context) {
		this.context = context;
		return bind(doExecure(context));
	}

	public abstract TResult doExecure(WorkflowContext context);

	public TResult bind(TResult result) {
		if (result != null) {
			if (result instanceof WorkflowContextAware) {
				((WorkflowContextAware) result).setContext(context);
			}
			if (result instanceof List<?>) {
				List<?> items = (List<?>) result;
				for (Object o : items) {
					if (o instanceof WorkflowContextAware) {
						((WorkflowContextAware) o).setContext(context);
					}
				}
			}
		}
		return result;
	}
}
