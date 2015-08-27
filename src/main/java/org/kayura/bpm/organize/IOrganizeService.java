package org.kayura.bpm.organize;

import java.util.List;

import org.kayura.bpm.organize.models.Company;
import org.kayura.bpm.organize.models.Department;
import org.kayura.bpm.organize.models.Employee;
import org.kayura.bpm.organize.models.Position;
import org.kayura.bpm.organize.models.Role;
import org.kayura.bpm.types.Actor;
import org.kayura.bpm.types.DataStatus;

/**
 * 组织机构数据库访问服务接口.
 * 
 * @author liangxia@live.com
 */
public interface IOrganizeService {

	/**
	 * 查询符合条件的公司集合.
	 * <p>
	 * 所有参数均可选。
	 * 
	 * @param parentId : 指定的父级公司Id,当值为 root 时表示只取根级公司.
	 * @param keyword : 搜索公司显示名的关键字.
	 * @param status : 公司的数据状态.{@link DataStatus }
	 * @return 返回 {@link Company } 的 {@link List} 集合.
	 */
	List<Company> findCompanies(String parentId, String keyword, Integer status);

	/**
	 * 查询符合条件的部门集合.
	 * <p>
	 * companyId 与 parentId 必需定义其中一个值,其它参数可选.
	 * 
	 * @param companyId : 部门所属公司Id.
	 * @param parentId : 指定的父级公司Id,当 companyId 有值且该值为 root 时表示只取根级部门.
	 * @param keyword : 搜索部门显示名的关键字.
	 * @param status : 数据状态.{@link DataStatus }
	 * @return 返回 {@link Department } 的 {@link List} 集合.
	 */
	List<Department> findDepartments(String companyId, String parentId, String keyword,
			Integer status);

	/**
	 * 查询符合条件的岗位集合.
	 * <p>
	 * departmentId 参数必需，其它参数可选.
	 * 
	 * @param departmentId : 指定的所属部门ID.
	 * @param keyword : 搜索岗位显示名的关键字.
	 * @param status : 数据状态.{@link DataStatus }
	 * @return 返回 {@link Position } 的 {@link List} 集合.
	 */
	List<Position> findPositions(String departmentId, String keyword, Integer status);

	/**
	 * 查询符合条件的角色列表.
	 * <p>
	 * 所有参数均可选。
	 * 
	 * @param parentId : 指定的父级角色Id,为 null 时取所有角色,当值为 root 时表示只取根级角色.
	 * @param keyword : 搜索角色显示名的关键字.
	 * @param status : 数据状态.{@link DataStatus}
	 * @return 返回 {@link Role} 的 {@link List} 集合.
	 */
	List<Role> findRoles(String parentId, String keyword, Integer status);

	/**
	 * 查询符合条件的员工信息列表.
	 * <p>
	 * 所有参数均可选。
	 * 
	 * @param companyId : 员工所属的公司Id.
	 * @param departmentId : 指定的所属部门Id.
	 * @param positionId : 指定的所属岗位Id.
	 * @param roleId : 指定的所属角色Id.
	 * @param keyword : 搜索员工姓名的关键字.
	 * @param status : 数据状态.{@link DataStatus }
	 * @return 返回 {@link Employee } 的 {@link List} 集合.
	 */
	List<Employee> findEmployees(String companyId, String departmentId, String positionId,
			String roleId, String keyword, Integer status);

	/**
	 * 查询符合条件的员工信息列表。
	 * <p>
	 * 结果包含组织及角色子级所有员工.
	 * 
	 * @param companyId : 员工所属的公司Id.
	 * @return 返回 {@link Actor } 的 {@link List} 集合.
	 */
	List<Actor> findActorsByCompany(List<String> companyIds);

	/**
	 * 查询符合条件的员工信息列表。
	 * <p>
	 * 结果包含组织及角色子级所有员工.
	 * 
	 * @param departmentId : 指定的所属部门ID.
	 * @return 返回 {@link Actor } 的 {@link List} 集合.
	 */
	List<Actor> findActorsByDepartment(List<String> departmentIds);

	/**
	 * 查询符合条件的员工信息列表。
	 * <p>
	 * 结果包含组织及角色子级所有员工.
	 * 
	 * @param positionId : 指定的所属岗位ID.
	 * @return 返回 {@link Actor } 的 {@link List} 集合.
	 */
	List<Actor> findActorsByPosition(List<String> positionIds);

	/**
	 * 查询符合条件的员工信息列表。
	 * <p>
	 * 结果包含组织及角色子级所有员工.
	 * 
	 * @param roleId : 指定的所属角色ID.
	 * @return 返回 {@link Actor } 的 {@link List} 集合.
	 */
	List<Actor> findActorsByRole(List<String> roleIds);

	/**
	 * 通过员工ID集查询相应的参与者信息.
	 * 
	 * @param empIds 员工Id集.
	 * @return 返回 {@link Actor } 的 {@link List} 集合.
	 */
	List<Actor> findActorsByEmpIds(List<String> empIds);
}
