/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.builder;

import java.io.InputStream;

import org.kayura.bpm.engine.Configuration;
import org.kayura.xml.XDocument;

/**
 * @author liangxia@live.com
 */
@SuppressWarnings("unused")
public class WorkflowConfigBuilder {

	private XDocument document;
	private Configuration configuration;

	public WorkflowConfigBuilder(InputStream inputStream) {
		this.document = new XDocument(inputStream);
	}

	

}
