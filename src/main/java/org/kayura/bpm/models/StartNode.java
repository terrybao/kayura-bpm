/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.models;

/**
 * 流程定义中唯一的开始环节.
 */
public class StartNode extends AbsRoute {

	public StartNode() {

	}

	public StartNode(WorkflowProcess parent, String code) {
		super(parent, NodeTypes.StartNode, code);
	}

}
