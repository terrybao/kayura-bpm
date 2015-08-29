package org.kayura.bpm.organize.impl.mapper;

import java.util.List;
import java.util.Map;

import org.kayura.bpm.organize.models.Company;
import org.kayura.bpm.organize.models.Department;
import org.kayura.bpm.organize.models.Position;
import org.kayura.bpm.organize.models.Role;
import org.kayura.bpm.types.Actor;
import org.kayura.bpm.types.DataStatus;

/**
 * 组织机构的数据访问映射.
 * @author liangxia@live.com
 */
public interface OrganizeMapper {

	/*  >>>>>>>>>>>>>>>  Company  <<<<<<<<<<<<<<<<< */
	
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
	 * 获取单独公司信息.
	 * 
	 * @param companyId 公司ID
	 * @return 返回公司信息.
	 */
	Company getCompanyById(String companyId);

	/*  >>>>>>>>>>>>>>>  Department  <<<<<<<<<<<<<<<<< */
	
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
	 * 获取单个部门信息.
	 * 
	 * @param departmentId 部门Id.
	 * @return 返回部门信息.
	 */
	Department getDepartmentById(String departmentId);

	/*  >>>>>>>>>>>>>>>  Position  <<<<<<<<<<<<<<<<< */
	
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

	/*  >>>>>>>>>>>>>>>  Actor  <<<<<<<<<<<<<<<<< */

	/**
	 * 查询指定组织下的参与者信息.
	 * 
	 * @param args 支持参数如下：
	 * <p>companyPath: 该公司下的所有人员.
	 * <p>departmentPath: 该部门及子部的的所有人员.
	 * <p>positionId: 该岗位下的所有人员.
	 * <p>status: 组织人员的数据状态 {@link DataStatus }.
	 * @return 返回 {@link Actor } 的 {@link List} 集合.
	 */
	List<Actor> findActorsByOrganize(Map<String, Object> args);

	/**
	 * 查询指定角色下的参与者信息.
	 * 
	 * @param args 支持参数如下：
	 * <p>rolePath: 该角色下的所有人员.
	 * <p>status: 角色及人员的数据状态 {@link DataStatus }.
	 * @return 返回 {@link Actor } 的 {@link List} 集合.
	 */
	List<Actor> findActorsByRole(Map<String, Object> args);
	
	/**
	 * 根据参与者部分信息取得符合条件的所有参与者对象。
	 * 
	 * @param actor 参与者部分信息，中使用到的属性做为条件有:
	 * <p>actorId : (单独条件)可以确定的唯一参与者主键； 
	 * <p>parentId : (单独条件)父级参与者Id,该条件用于查询直属下级参与者； 
	 * <p>employeeId : 与参与者对应的员工Id;
	 * <p>departmentId : 所属部门Id; 
	 * <p>positionId : 所属岗位Id;
	 * @return 返回 {@link Actor } 的 {@link List} 集合.
	 */
	List<Actor> findActorsByActor(Map<String, Object> args);
	
	/**
	 * 根据参与者Id，获取参与者详细信息.
	 * 
	 * @param actorId 可以确定的唯一参与者主键
	 * @return 返回 {@link Actor } 的对象.
	 */
	Actor getActorById(String actorId);
	
	/**
	 * 根据参与者Ids，获取参与者详细信息.
	 * 
	 * @param actorIds 可以确定的唯一参与者主键
	 * @return 返回 {@link Actor } 的 {@link List} 集合.
	 */
	List<Actor> findActorsByIds(List<String> actorIds);

	/*  >>>>>>>>>>>>>>>  Role  <<<<<<<<<<<<<<<<< */
	
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
