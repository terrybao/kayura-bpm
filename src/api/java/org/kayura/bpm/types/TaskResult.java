/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.types;

import org.kayura.type.Result;

public class TaskResult extends Result<String> {

	private static final long serialVersionUID = -4636847672053074606L;

	public TaskResult() {

	}

	public TaskResult(String message) {
		this.addMessage(message);
	}
}
