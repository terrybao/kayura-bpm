package org.kayura.bpm.organize;

import java.util.List;

import org.kayura.bpm.organize.models.Company;
import org.kayura.bpm.organize.models.Department;
import org.kayura.bpm.organize.models.Position;
import org.kayura.bpm.organize.models.Role;
import org.kayura.bpm.types.Actor;
import org.kayura.bpm.types.DataStatus;

/**
 * 组织机构数据库访问服务接口.
 * 
 * @author liangxia@live.com
 */
public interface OrganizeService {

	/**
	 * 查询符合条件的公司集合.
	 * <p>
	 * 所有参数均可选.
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
	List<Department> findDepartments(String companyId, String parentId, String keyword, Integer status);

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
	 * 所有参数均可选.
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
	 * 结果包含组织及角色子级所有员工.
	 * 
	 * @param companyId : 员工所属的公司Id.
	 * @return 返回 {@link Actor } 的 {@link List} 集合.
	 */
	List<Actor> findActorsByCompany(List<String> companyIds);

	/**
	 * 查询符合条件的员工信息列表.
	 * <p>
	 * 结果包含组织及角色子级所有员工.
	 * 
	 * @param departmentId : 指定的所属部门ID.
	 * @return 返回 {@link Actor } 的 {@link List} 集合.
	 */
	List<Actor> findActorsByDepartment(List<String> departmentIds);

	/**
	 * 查找与指定参与者相同部门的所有参与者.
	 * 
	 * @param actorId 指定参与者Id.
	 * @return 返回 {@link Acotr} 类型的 {@link List} 集合．
	 */
	List<Actor> findActorsBySameDepartment(String actorId);

	/**
	 * 查询符合条件的员工信息列表.
	 * <p>
	 * 结果包含组织及角色子级所有员工.
	 * 
	 * @param positionId : 指定的所属岗位ID.
	 * @return 返回 {@link Actor } 的 {@link List} 集合.
	 */
	List<Actor> findActorsByPosition(List<String> positionIds);

	/**
	 * 查找与指定参与者相同岗位的所有参与者.
	 * 
	 * @param actorId 指定参与者Id.
	 * @return 返回 {@link Acotr} 类型的 {@link List} 集合．
	 */
	List<Actor> findActorsBySamePosition(String actorId);

	/**
	 * 查询符合条件的员工信息列表.
	 * <p>
	 * 结果包含组织及角色子级所有员工.
	 * 
	 * @param roleId : 指定的所属角色ID.
	 * @return 返回 {@link Actor } 的 {@link List} 集合.
	 */
	List<Actor> findActorsByRole(List<String> roleIds);
	
	/**
	 * 查询多个参与者的详细信息.
	 * 
	 * @param actorIds 参与者Id集.
	 * @return 返回 {@link Actor } 的 {@link List} 集合.
	 */
	List<Actor> findActorsByIds(List<String> actorIds);
	
	/**
	 * 通过包含部分的参与者信息，返回一个完整的参与者对象.
	 * 
	 * @param actor 参与者 {@link Actor} 对象.
	 * @return 返回完整的{@link Actor }对象.
	 */
	Actor findActorByActor(Actor actor);
	
	/**
	 * 通过包含部分的参与者信息，返回一个完整的参与者对象.
	 * 
	 * @param actor 参与者 {@link Actor} 对象.
	 * @return 返回完整的{@link Actor }对象.
	 */
	List<Actor> findActorsByActors(List<Actor> actors);

	/**
	 * 查询该参与者的上级领导.
	 * 
	 * @param actorId 指定的参与者Id.
	 * @return 返回上级参与者.
	 */
	Actor findParentActor(String actorId);

	/**
	 * 查询该参与者的所有直系下属参与者.
	 * 
	 * @param actorId 指定的参与者Id.
	 * @return 返回直系下属参与者．
	 */
	List<Actor> findChildActors(String actorId);
}
