package org.kayura.bpm.organize.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kayura.bpm.exceptions.WorkflowException;
import org.kayura.bpm.organize.OrganizeService;
import org.kayura.bpm.organize.impl.mapper.OrganizeMapper;
import org.kayura.bpm.organize.models.Company;
import org.kayura.bpm.organize.models.Department;
import org.kayura.bpm.organize.models.Position;
import org.kayura.bpm.organize.models.Role;
import org.kayura.bpm.types.Actor;
import org.kayura.utils.StringUtils;

public class OrganizeServiceImpl implements OrganizeService {

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
	public List<Actor> findActorsByCompany(List<String> companyIds) {

		List<Actor> actors = new ArrayList<Actor>();
		for (String companyId : companyIds) {

			Company comapny = mapper.getCompanyById(companyId);
			if (comapny != null) {

				Map<String, Object> args = new HashMap<String, Object>();
				args.put("companyPath", comapny.getPath());

				List<Actor> list = mapper.findActorsByOrganize(args);
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

				List<Actor> list = mapper.findActorsByOrganize(args);
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

			List<Actor> list = mapper.findActorsByOrganize(args);
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

				List<Actor> list = mapper.findActorsByRole(args);
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
	public List<Actor> findActorsByIds(List<String> actorIds) {
		List<Actor> actors = mapper.findActorsByIds(actorIds);
		return actors;
	}

	@Override
	public Actor findActorByActor(Actor actor) {

		if (actor == null) {
			return null;
		}

		String id = actor.getId();
		String employeeId = actor.getEmployeeId();
		String departmentId = actor.getDepartmentId();
		String positionId = actor.getPositionId();

		Map<String, Object> args = new HashMap<String, Object>();

		if (!StringUtils.isEmpty(id)) {
			args.put("actorId", id);
		}

		if (!StringUtils.isEmpty(employeeId)) {
			args.put("employeeId", employeeId);
		}

		if (!StringUtils.isEmpty(departmentId)) {
			args.put("departmentId", departmentId);
		}

		if (!StringUtils.isEmpty(positionId)) {
			args.put("positionId", positionId);
		}

		if (args.size() == 0) {
			return null;
		}

		List<Actor> list = mapper.findActorsByActor(args);
		if (list.size() > 1) {

			String name = list.get(0).getDisplayName();
			throw new WorkflowException(String.format("人员 %s 的岗位不明确", name));
		}

		if (list.size() == 1) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Actor> findActorsByActors(List<Actor> actors) {

		List<Actor> resultActors = new ArrayList<Actor>();

		if (actors != null) {
			for (Actor actor : actors) {
				Actor a = findActorByActor(actor);
				if (a != null) {
					resultActors.add(a);
				}
			}
		}

		return resultActors;
	}

	@Override
	public List<Actor> findActorsBySameDepartment(String actorId) {

		Actor actor = mapper.getActorById(actorId);
		if (actor != null && !StringUtils.isEmpty(actor.getDepartmentId())) {

			Map<String, Object> args = new HashMap<String, Object>();
			args.put("departmentId", actor.getDepartmentId());

			List<Actor> list = mapper.findActorsByActor(args);
			return list;
		} else {

			return new ArrayList<Actor>();
		}
	}

	@Override
	public List<Actor> findActorsBySamePosition(String actorId) {

		Actor actor = mapper.getActorById(actorId);
		if (actor != null && !StringUtils.isEmpty(actor.getPositionId())) {

			Map<String, Object> args = new HashMap<String, Object>();
			args.put("positionId", actor.getPositionId());

			List<Actor> list = mapper.findActorsByActor(args);
			return list;
		} else {

			return new ArrayList<Actor>();
		}
	}

	@Override
	public Actor findParentActor(String actorId) {

		Actor actor = mapper.getActorById(actorId);
		if (actor != null && !StringUtils.isEmpty(actor.getParentId())) {

			Actor parent = mapper.getActorById(actor.getParentId());
			return parent;
		} else {

			return null;
		}
	}

	@Override
	public List<Actor> findChildActors(String actorId) {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("parentId", actorId);

		List<Actor> list = mapper.findActorsByActor(args);
		return list;
	}

}
