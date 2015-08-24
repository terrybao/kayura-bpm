/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.organize.impl;

import org.kayura.bpm.organize.impl.mapper.OrganizeMapper;
import org.kayura.bpm.organize.models.Company;
import org.kayura.bpm.organize.models.Role;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author liangxia@live.com
 */
public class OrganizeServiceTest {

	ObjectMapper om = new ObjectMapper();

	private OrganizeServiceImpl organizeService;
	private SqlSessionFactory sqlSessionFactory;
	private SqlSession session;

	@Before
	public void setUp() throws IOException {
		InputStream inputStream = Resources.getResourceAsStream("mybatisConfig.xml");
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		session = sqlSessionFactory.openSession();
		OrganizeMapper mapper = session.getMapper(OrganizeMapper.class);
		organizeService = new OrganizeServiceImpl();
		organizeService.setOrganizeMapper(mapper);
	}

	@After
	public void setDown() {
		session.close();
	}

	@Test
	public void findOrgMemberByAllTest() {
		try {

			List<Company> list = organizeService.findCompanies(null, null, null);

			System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void findRolesByAllTest() {
		try {

			List<Role> list = organizeService.findRoles(null, null, null);

			System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
