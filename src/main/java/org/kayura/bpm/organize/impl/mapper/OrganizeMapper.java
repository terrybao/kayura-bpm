package org.kayura.bpm.organize.impl.mapper;

import java.util.List;
import java.util.Map;

import org.kayura.bpm.organize.models.Company;
import org.kayura.bpm.organize.models.Department;
import org.kayura.bpm.organize.models.Employee;
import org.kayura.bpm.organize.models.Position;
import org.kayura.bpm.organize.models.Role;
import org.kayura.bpm.types.Actor;
import org.kayura.bpm.types.DataStatus;

/**
 * 组织机构的数据访问映射.
 * @author liangxia@live.com
 */
public interface OrganizeMapper {

	/**
	 * 查询符合条件的公司集合.
	 * 
	 * @param args 支持参数如下：
	 * <p>parentId : 指定的父级公司ID,当值为 root 时表示只取根级公司.
	 * <p>keyword : 搜索公司显示名的关键字.
	 * <p>status : 公司的数据状态.{@link DataStatus } 
	 * @return 返回 {@link Company } 的 {@link List} 集合.
	 */
	List<Company> findCompanies(Map<String, Object> args);
	
	/**
	 * 获取公司信息.
	 * 
	 * @param companyId 公司ID
	 * @return 返回公司信息.
	 */
	Company getCompanyById(String companyId);

	/**
	 * 查询符合条件的部门集合.
	 * 
	 * @param args 支持参数如下：
	 * <p>companyId: 部门所属公司Id.
	 * <p>parentId : 指定的父级公司ID.
	 * <p>keyword : 搜索部门显示名的关键字.
	 * <p>status : 数据状态.{@link DataStatus } 
	 * @return 返回 {@link Department } 的 {@link List} 集合.
	 */
	List<Department> findDepartments(Map<String, Object> args);
	
	/**
	 * 获取部门信息.
	 * 
	 * @param departmentId 部门Id.
	 * @return 返回部门信息.
	 */
	Department getDepartmentById(String departmentId);

	/**
	 * 查询符合条件的岗位集合.
	 * 
	 * @param args 支持参数如下：
	 * <p>departmentId : 指定的所属部门ID.
	 * <p>keyword : 搜索岗位显示名的关键字.
	 * <p>status : 数据状态.{@link DataStatus } 
	 * @return 返回 {@link Position } 的 {@link List} 集合.
	 */
	List<Position> findPositions(Map<String, Object> args);

	/**
	 * 查询符合条件的员工信息列表.
	 * 
	 * @param args 支持参数如下：
	 * <p>keyword : 搜索员工姓名的关键字.
	 * <p>status : 数据状态.{@link DataStatus } 
	 * <p>companyId : 员工所属的公司Id.
	 * <p>departmentId : 指定的所属部门ID.
	 * <p>positionId : 指定的所属岗位ID.
	 * <p>roleId : 指定的所属角色ID.
	 * @return 返回 {@link Employee } 的 {@link List} 集合.
	 */
	List<Employee> findEmployees(Map<String, Object> args);

	/**
	 * 查询指定组织或角色下的参与者信息.
	 * 
	 * @param args 支持参数如下：
	 * <p>companyPath: 该公司下的所有人员.
	 * <p>departmentPath: 该部门及子部的的所有人员.
	 * <p>positionId: 该岗位下的所有人员.
	 * <p>rolePath: 该角色下的所有人员.
	 * @return 返回 {@link Actor } 的 {@link List} 集合.
	 */
	List<Actor> findActors(Map<String, Object> args);

	/**
	 * 通过员工ID集查询相应的参与者信息.
	 * 
	 * @param empIds 员工Id集.
	 * @return 返回 {@link Actor } 的 {@link List} 集合.
	 */
	List<Actor> findActorsByEmpIds(List<String> empIds);
	
	/**
	 * 查询符合条件的角色列表.
	 * 
	 * @param args 支持参数如下：
	 * <p>parentId : 指定的父级角色ID.
	 * <p>keyword : 搜索角色显示名的关键字.
	 * <p>status : 数据状态.{@link DataStatus } 
	 * @return 返回 {@link Role } 的 {@link List} 集合.
	 */
	List<Role> findRoles(Map<String, Object> args);
	
	/**
	 * 获取角色信息.
	 * 
	 * @param roleId 角色Id.
	 * @return 返回角色信息.
	 */
	Role getRoleById(String roleId);
}
