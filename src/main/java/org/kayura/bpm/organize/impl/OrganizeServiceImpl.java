package org.kayura.bpm.organize.impl;

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

	private OrganizeMapper organizeMapper;

	public void setOrganizeMapper(OrganizeMapper organizeMapper) {
		this.organizeMapper = organizeMapper;
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

		return organizeMapper.findCompanies(args);
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

		return organizeMapper.findDepartments(args);
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

		return organizeMapper.findPositions(args);
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

		return organizeMapper.findRoles(args);
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

		return organizeMapper.findEmployees(args);
	}

	@Override
	public List<Actor> findActorsByCompany(String companyId) {

		Map<String, Object> args = new HashMap<String, Object>();

		if (StringUtils.isEmpty(companyId)) {
			args.put("companyId", companyId);
		}

		return organizeMapper.findActors(args);
	}

	@Override
	public List<Actor> findActorsByDepartment(String departmentId) {

		Map<String, Object> args = new HashMap<String, Object>();

		if (StringUtils.isEmpty(departmentId)) {
			args.put("departmentId", departmentId);
		}

		return organizeMapper.findActors(args);
	}

	@Override
	public List<Actor> findActorsByPosition(String positionId) {

		Map<String, Object> args = new HashMap<String, Object>();

		if (StringUtils.isEmpty(positionId)) {
			args.put("positionId", positionId);
		}

		return organizeMapper.findActors(args);
	}

	@Override
	public List<Actor> findActorsByRole(String roleId) {

		Map<String, Object> args = new HashMap<String, Object>();

		if (StringUtils.isEmpty(roleId)) {
			args.put("roleId", roleId);
		}

		return organizeMapper.findActors(args);
	}

	@Override
	public List<Actor> findActorsByEmpIds(List<String> empIds) {
		return organizeMapper.findActorsByEmpIds(empIds);
	}

}
