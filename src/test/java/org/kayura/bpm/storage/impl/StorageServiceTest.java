package org.kayura.bpm.storage.impl;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.kayura.bpm.models.WorkflowProcess;
import org.kayura.bpm.models.WorkflowProcessTest;
import org.kayura.bpm.storage.IStorageService;
import org.kayura.bpm.storage.impl.mapper.DefineMapper;

public class StorageServiceTest {
    
    private IStorageService storageService;
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession session;
    
    /*
     * @Before public void setUp() { }
     * @After public void setDown() { session.close(); }
     */
    
    @Test
    public void syncWorkflowProcessTest() {
	
	InputStream inputStream;
	try {
	    inputStream = Resources.getResourceAsStream("mybatisConfig.xml");
	    sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	    session = sqlSessionFactory.openSession();
	    DefineMapper mapper = session.getMapper(DefineMapper.class);
	    storageService = new StorageServiceImpl(mapper);
	    
	    WorkflowProcess wp = WorkflowProcessTest.createWorkflowProcess();
	    storageService.syncWorkflowProcess(wp);
	    
	    session.commit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	
    }
    
}
