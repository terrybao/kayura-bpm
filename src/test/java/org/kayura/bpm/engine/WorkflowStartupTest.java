package org.kayura.bpm.engine;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kayura.bpm.engine.impl.WorkflowContextImpl;
import org.kayura.bpm.engine.impl.WorkflowRuntimeImpl;
import org.kayura.bpm.organize.IOrganizeService;
import org.kayura.bpm.organize.impl.OrganizeServiceImpl;
import org.kayura.bpm.organize.impl.mapper.OrganizeMapper;
import org.kayura.bpm.storage.IStorageService;
import org.kayura.bpm.storage.impl.StorageServiceImpl;
import org.kayura.bpm.storage.impl.mapper.DefineMapper;
import org.kayura.bpm.storage.impl.mapper.InstanceMapper;
import org.kayura.bpm.types.Actor;
import org.kayura.bpm.types.BizData;
import org.kayura.bpm.types.StartArgs;
import org.kayura.bpm.types.StartResult;
import org.kayura.utils.KeyUtils;

/**
 * 工作流引擎单元测试.
 * 
 * @author liangxia@live.com
 */
public class WorkflowStartupTest {

	private SqlSessionFactory sqlSessionFactory;
	private SqlSession session;
	private IWorkflowRuntime runtime;

	public WorkflowStartupTest() throws IOException {
		InputStream inputStream = Resources.getResourceAsStream("mybatisConfig.xml");
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Before
	public void setUp() {

		session = sqlSessionFactory.openSession();

		DefineMapper dmapper = session.getMapper(DefineMapper.class);
		InstanceMapper imapper = session.getMapper(InstanceMapper.class);
		OrganizeMapper omapper = session.getMapper(OrganizeMapper.class);

		IStorageService storageService = new StorageServiceImpl(dmapper, imapper);
		IOrganizeService organizeService = new OrganizeServiceImpl(omapper);

		IWorkflowContext context = new WorkflowContextImpl(storageService, organizeService);

		runtime = new WorkflowRuntimeImpl(context);
	}

	@After
	public void setDown() {
		session.commit();
		session.close();
	}

	private String bizDataId = KeyUtils.newId();

	@Test
	public void startupTest() {

		StartArgs args = new StartArgs();
		args.setCreator(new Actor("xialiang", "夏亮"));
		args.setBizData(new BizData(bizDataId, "测试流程"));
		args.setFlowCode("UnitTest");

		StartResult startResult = runtime.startup(args);

		System.out.println(startResult.getMessage());
	}
	
	

}
