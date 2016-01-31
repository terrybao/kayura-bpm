/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.kernel;

import java.util.Properties;

import org.kayura.bpm.models.Transition;

/**
 * @author liangxia@live.com
 */
public class TransitionInstance {

	private Transition transition;

	public TransitionInstance(Transition transition) {
		this.transition = transition;
	}

	public Transition getTransition() {
		return transition;
	}

	public void setTransition(Transition transition) {
		this.transition = transition;
	}

	public boolean checkExpress(Properties vars) {
		return true;
	}

}
