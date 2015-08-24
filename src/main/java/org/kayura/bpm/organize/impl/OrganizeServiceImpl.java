package org.kayura.bpm.organize.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kayura.bpm.organize.IOrganizeService;
import org.kayura.bpm.organize.impl.mapper.OrganizeMapper;
import org.kayura.bpm.organize.models.Company;
import org.kayura.bpm.organize.models.Department;
import org.kayura.bpm.organize.models.Employee;
import org.kayura.bpm.organize.models.Position;
import org.kayura.bpm.organize.models.Role;
import org.kayura.bpm.types.Actor;
import org.kayura.utils.StringUtils;

public class OrganizeServiceImpl implements IOrganizeService {

	private OrganizeMapper mapper;

	public void setOrganizeMapper(OrganizeMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<Company> findCompanies(String parentId, String keyword, Integer status) {

		Map<String, Object> args = new HashMap<String, Object>();

		if (StringUtils.isEmpty(parentId)) {
			args.put("parentId", parentId);
		}
		if (StringUtils.isEmpty(keyword)) {
			args.put("keyword", keyword);
		}
		if (status != null) {
			args.put("status", status);
		}

		return mapper.findCompanies(args);
	}

	@Override
	public List<Department> findDepartments(String companyId, String parentId,
			String keyword, Integer status) {

		Map<String, Object> args = new HashMap<String, Object>();

		if (StringUtils.isEmpty(companyId)) {
			args.put("companyId", companyId);
		}
		if (StringUtils.isEmpty(parentId)) {
			args.put("parentId", parentId);
		}
		if (StringUtils.isEmpty(keyword)) {
			args.put("keyword", keyword);
		}
		if (status != null) {
			args.put("status", status);
		}

		return mapper.findDepartments(args);
	}

	@Override
	public List<Position> findPositions(String departmentId, String keyword,
			Integer status) {

		Map<String, Object> args = new HashMap<String, Object>();

		if (StringUtils.isEmpty(departmentId)) {
			args.put("departmentId", departmentId);
		}
		if (StringUtils.isEmpty(keyword)) {
			args.put("keyword", keyword);
		}
		if (status != null) {
			args.put("status", status);
		}

		return mapper.findPositions(args);
	}

	@Override
	public List<Role> findRoles(String parentId, String keyword, Integer status) {

		Map<String, Object> args = new HashMap<String, Object>();

		if (StringUtils.isEmpty(parentId)) {
			args.put("parentId", parentId);
		}
		if (StringUtils.isEmpty(keyword)) {
			args.put("keyword", keyword);
		}
		if (status != null) {
			args.put("status", status);
		}

		return mapper.findRoles(args);
	}

	@Override
	public List<Employee> findEmployees(String companyId, String departmentId,
			String positionId, String roleId, String keyword, Integer status) {

		Map<String, Object> args = new HashMap<String, Object>();

		if (StringUtils.isEmpty(companyId)) {
			args.put("companyId", companyId);
		}
		if (StringUtils.isEmpty(departmentId)) {
			args.put("departmentId", departmentId);
		}
		if (StringUtils.isEmpty(positionId)) {
			args.put("positionId", positionId);
		}
		if (StringUtils.isEmpty(roleId)) {
			args.put("roleId", roleId);
		}
		if (StringUtils.isEmpty(keyword)) {
			args.put("keyword", keyword);
		}
		if (status != null) {
			args.put("status", status);
		}

		return mapper.findEmployees(args);
	}

	@Override
	public List<Actor> findActorsByCompany(String companyId) {

		Company comapny = mapper.getCompanyById(companyId);
		if (comapny != null) {

			Map<String, Object> args = new HashMap<String, Object>();
			args.put("companyPath", comapny.getPath());

			return mapper.findActors(args);
		} else {
			return new ArrayList<Actor>();
		}
	}

	@Override
	public List<Actor> findActorsByDepartment(String departmentId) {

		Department department = mapper.getDepartmentById(departmentId);
		if (department != null) {

			Map<String, Object> args = new HashMap<String, Object>();
			args.put("departmentPath", department.getPath());

			return mapper.findActors(args);
		} else {
			return new ArrayList<Actor>();
		}
	}

	@Override
	public List<Actor> findActorsByPosition(String positionId) {

		Map<String, Object> args = new HashMap<String, Object>();
		if (StringUtils.isEmpty(positionId)) {
			args.put("positionId", positionId);
		}

		return mapper.findActors(args);
	}

	@Override
	public List<Actor> findActorsByRole(String roleId) {

		Role role = mapper.getRoleById(roleId);
		if (role != null) {
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("rolePath", role.getPath());

			return mapper.findActors(args);
		} else {
			return new ArrayList<Actor>();
		}
	}

	@Override
	public List<Actor> findActorsByEmpIds(List<String> empIds) {
		return mapper.findActorsByEmpIds(empIds);
	}

}
