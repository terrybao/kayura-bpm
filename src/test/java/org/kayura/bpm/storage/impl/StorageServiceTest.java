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
import org.kayura.bpm.kernel.WorkItem;
import org.kayura.bpm.models.BizForm;
import org.kayura.bpm.models.DefineStatus;
import org.kayura.bpm.models.WorkflowProcess;
import org.kayura.bpm.models.WorkflowProcessSimple;
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
		session.commit();
		session.close();
	}

	@Test
	public void syncLinear1WorkflowProcess() {
		try {

			BizForm bizForm = new BizForm();
			bizForm.setId("74C741FD-1A93-4431-B85B-5111D632073B");
			bizForm.setCode("OneNode");
			bizForm.setDisplayName("单环节直线流");
			bizForm.setStatus(DefineStatus.Release);
			storageService.saveOrUpdateBizForm(bizForm);

			WorkflowProcess wp = WorkflowProcessSimple.createLinear1Process();
			wp.setModifier("xialiang");
			wp.setBizForm(bizForm);
			wp.setStatus(DefineStatus.Release);
			storageService.syncWorkflowProcess(wp);

			WorkflowProcessBuilder builder = new WorkflowProcessBuilder(wp);
			System.out.println(builder.exportXml());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void syncLinear2WorkflowProcess() {
		try {

			BizForm bizForm = new BizForm();
			bizForm.setId("707B896E-5B52-11E5-A041-0021CCC9FA7E");
			bizForm.setCode("ThreeNode");
			bizForm.setDisplayName("三环节直线流");
			bizForm.setStatus(DefineStatus.Release);
			storageService.saveOrUpdateBizForm(bizForm);

			WorkflowProcess wp = WorkflowProcessSimple.createLinear2Process();
			wp.setModifier("xialiang");
			wp.setBizForm(bizForm);
			wp.setStatus(DefineStatus.Release);
			storageService.syncWorkflowProcess(wp);

			WorkflowProcessBuilder builder = new WorkflowProcessBuilder(wp);
			System.out.println(builder.exportXml());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void syncWorkflowProcessTest() {
		try {

			WorkflowProcess wp = WorkflowProcessSimple.createBranchWorkflowProcess();
			storageService.syncWorkflowProcess(wp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
