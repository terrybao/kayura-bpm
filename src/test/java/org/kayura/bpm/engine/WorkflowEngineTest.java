/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.engine;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.kayura.bpm.engine.impl.WorkflowContextImpl;
import org.kayura.bpm.engine.impl.WorkflowRuntimeImpl;
import org.kayura.bpm.organize.IOrganizeService;
import org.kayura.bpm.organize.impl.OrganizeServiceImpl;
import org.kayura.bpm.organize.impl.mapper.OrganizeMapper;
import org.kayura.bpm.storage.IStorageService;
import org.kayura.bpm.storage.impl.StorageServiceImpl;
import org.kayura.bpm.storage.impl.mapper.StorageMapper;

/**
 * @author liangxia@live.com
 */
public abstract class WorkflowEngineTest {
	
	protected SqlSessionFactory sqlSessionFactory;
	protected SqlSession session;
	protected IWorkflowRuntime runtime;
	protected IWorkflowContext context;

	public WorkflowEngineTest() throws IOException {
		InputStream inputStream = Resources.getResourceAsStream("mybatisConfig.xml");
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Before
	public void setUp() {

		session = sqlSessionFactory.openSession();

		StorageMapper dMapper = session.getMapper(StorageMapper.class);
		OrganizeMapper oMapper = session.getMapper(OrganizeMapper.class);

		IStorageService storageService = new StorageServiceImpl(dMapper);
		IOrganizeService organizeService = new OrganizeServiceImpl(oMapper);

		context = new WorkflowContextImpl(storageService, organizeService);

		runtime = new WorkflowRuntimeImpl(context);
	}

	@After
	public void setDown() {
		session.commit();
		session.close();
	}
}
