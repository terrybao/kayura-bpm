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
