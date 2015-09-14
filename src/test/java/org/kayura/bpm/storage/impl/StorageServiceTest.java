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
import org.kayura.bpm.kernel.WorkItem;
import org.kayura.bpm.models.BizForm;
import org.kayura.bpm.models.DefineStatus;
import org.kayura.bpm.models.WorkflowProcess;
import org.kayura.bpm.models.WorkflowProcessTest;
import org.kayura.bpm.storage.impl.mapper.StorageMapper;

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
		StorageMapper mapper = session.getMapper(StorageMapper.class);
		storageService = new StorageServiceImpl(mapper);
	}

	@After
	public void setDown() {
		session.close();
	}

	@Test
	public void initData() {
		saveBizFormTest();
		syncLineWorkflowProcess();
	}

	@Test
	public void saveBizFormTest() {
		try {

			BizForm bizForm = new BizForm();
			bizForm.setId("74C741FD-1A93-4431-B85B-5111D632073B");
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
			wp.setModifier("xialiang");
			wp.setBizForm(bizForm);
			wp.setStatus(DefineStatus.Release);

			storageService.syncWorkflowProcess(wp);

			session.commit();

			WorkflowProcessBuilder builder = new WorkflowProcessBuilder(wp);
			System.out.println(builder.exportXml());

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
			WorkflowProcess wp = storageService.getWorkflowProcess("A2E467A4-8BA0-4BC3-B192-56475E1A01E0");

			System.out.println(wp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getWorkItemByFirstTest() {
		try {
			WorkItem task = storageService.findWorkItemByFirst("4588F8DC-4DF8-11E5-9FEC-10BF48BBBEC9");

			System.out.println(task);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
