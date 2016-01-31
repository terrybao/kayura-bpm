/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.types;

import org.kayura.type.Result;

public class StartResult extends Result<String> {

	private static final long serialVersionUID = -5932208440194569552L;

	public StartResult() {
	}

	public StartResult(String message) {
		this.addMessage(message);
	}

}
