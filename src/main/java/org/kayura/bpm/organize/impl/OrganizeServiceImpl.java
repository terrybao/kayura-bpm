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

	public OrganizeMapper getMapper() {
		return mapper;
	}

	public void setMapper(OrganizeMapper mapper) {
		this.mapper = mapper;
	}

	public OrganizeServiceImpl() {

	}

	public OrganizeServiceImpl(OrganizeMapper mapper) {
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
	public List<Department> findDepartments(String companyId, String parentId, String keyword,
			Integer status) {

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
	public List<Position> findPositions(String departmentId, String keyword, Integer status) {

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
	public List<Employee> findEmployees(String companyId, String departmentId, String positionId,
			String roleId, String keyword, Integer status) {

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
	public List<Actor> findActorsByCompany(List<String> companyIds) {

		List<Actor> actors = new ArrayList<Actor>();
		for (String companyId : companyIds) {

			Company comapny = mapper.getCompanyById(companyId);
			if (comapny != null) {

				Map<String, Object> args = new HashMap<String, Object>();
				args.put("companyPath", comapny.getPath());

				List<Actor> list = mapper.findActors(args);
				for (Actor actor : list) {
					if (!actors.contains(actor)) {
						actors.add(actor);
					}
				}
			}
		}

		return actors;
	}

	@Override
	public List<Actor> findActorsByDepartment(List<String> departmentIds) {

		List<Actor> actors = new ArrayList<Actor>();
		for (String departmentId : departmentIds) {

			Department department = mapper.getDepartmentById(departmentId);
			if (department != null) {

				Map<String, Object> args = new HashMap<String, Object>();
				args.put("departmentPath", department.getPath());

				List<Actor> list = mapper.findActors(args);
				for (Actor actor : list) {
					if (!actors.contains(actor)) {
						actors.add(actor);
					}
				}
			}
		}
		return actors;
	}

	@Override
	public List<Actor> findActorsByPosition(List<String> positionIds) {

		List<Actor> actors = new ArrayList<Actor>();
		for (String positionId : positionIds) {

			Map<String, Object> args = new HashMap<String, Object>();
			args.put("positionId", positionId);

			List<Actor> list = mapper.findActors(args);
			for (Actor actor : list) {
				if (!actors.contains(actor)) {
					actors.add(actor);
				}
			}
		}
		return actors;
	}

	@Override
	public List<Actor> findActorsByRole(List<String> roleIds) {

		List<Actor> actors = new ArrayList<Actor>();
		for (String roleId : roleIds) {

			Role role = mapper.getRoleById(roleId);
			if (role != null) {

				Map<String, Object> args = new HashMap<String, Object>();
				args.put("rolePath", role.getPath());

				List<Actor> list = mapper.findActors(args);
				for (Actor actor : list) {
					if (!actors.contains(actor)) {
						actors.add(actor);
					}
				}
			}
		}
		return actors;
	}

	@Override
	public List<Actor> findActorsByEmpIds(List<String> empIds) {
		return mapper.findActorsByEmpIds(empIds);
	}

}
