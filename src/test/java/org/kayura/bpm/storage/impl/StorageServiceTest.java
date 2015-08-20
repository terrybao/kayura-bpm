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
import org.kayura.bpm.models.WorkflowProcess;
import org.kayura.bpm.models.WorkflowProcessTest;
import org.kayura.bpm.storage.IStorageService;
import org.kayura.bpm.storage.impl.mapper.DefineMapper;

import com.fasterxml.jackson.databind.ObjectMapper;

public class StorageServiceTest {
    
    ObjectMapper objectMapper = new ObjectMapper();
    
    private IStorageService storageService;
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession session;
    
    @Before
    public void setUp() throws IOException {
	InputStream inputStream = Resources.getResourceAsStream("mybatisConfig.xml");
	sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	session = sqlSessionFactory.openSession();
	DefineMapper mapper = session.getMapper(DefineMapper.class);
	storageService = new StorageServiceImpl(mapper);
    }
    
    @After
    public void setDown() {
	session.close();
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
