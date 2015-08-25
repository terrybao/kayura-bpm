/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.builder;

import org.kayura.bpm.engine.Configuration;
import org.kayura.xml.XDocument;

/**
 * @author liangxia@live.com
 */
public class WorkflowConfigBuilder {

	private XDocument document;
	private Configuration configuration;

	public XDocument getDocument() {
		return document;
	}

	public void setDocument(XDocument document) {
		this.document = document;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

}
