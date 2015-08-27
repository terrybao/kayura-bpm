package org.kayura.bpm.storage.impl;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kayura.bpm.builder.WorkflowProcessBuilder;
import org.kayura.bpm.engine.WorkflowProcessSimple;
import org.kayura.bpm.models.BizForm;
import org.kayura.bpm.models.DefineStatus;
import org.kayura.bpm.models.WorkflowProcess;
import org.kayura.bpm.models.WorkflowProcessTest;
import org.kayura.bpm.storage.impl.mapper.DefineMapper;
import org.kayura.utils.KeyUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class StorageServiceTest {

	ObjectMapper objectMapper = new ObjectMapper();

	private StorageServiceImpl storageService;
	private SqlSessionFactory sqlSessionFactory;
	private SqlSession session;

	@Before
	public void setUp() throws IOException {
		InputStream inputStream = Resources.getResourceAsStream("mybatisConfig.xml");
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		session = sqlSessionFactory.openSession();
		DefineMapper mapper = session.getMapper(DefineMapper.class);
		storageService = new StorageServiceImpl();
		storageService.setDefineMapper(mapper);
	}

	@After
	public void setDown() {
		session.close();
	}

	@Test
	public void saveBizFormTest() {
		try {

			BizForm bizForm = new BizForm();
			bizForm.setId(KeyUtils.newId());
			bizForm.setCode("UnitTest");
			bizForm.setDisplayName("仅供开发调用");
			bizForm.setStatus(DefineStatus.Release);

			storageService.saveOrUpdateBizForm(bizForm);

			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void syncLineWorkflowProcess() {
		try {
			WorkflowProcess wp = WorkflowProcessSimple.getLineProcess();

			BizForm bizForm = storageService.getBizFormById("74C741FD-1A93-4431-B85B-5111D632073B");
			wp.setBizForm(bizForm);

			storageService.syncWorkflowProcess(wp);

			session.commit();

			WorkflowProcessBuilder builder = new WorkflowProcessBuilder(wp);
			System.out.println(builder.formatXml());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void syncWorkflowProcessTest() {
		try {

			WorkflowProcess wp = WorkflowProcessTest.createWorkflowProcess();
			storageService.syncWorkflowProcess(wp);

			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getWorkflowProcessTest() {
		try {
			WorkflowProcess wp = storageService
					.getWorkflowProcess("A2E467A4-8BA0-4BC3-B192-56475E1A01E0");

			System.out.println(wp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
