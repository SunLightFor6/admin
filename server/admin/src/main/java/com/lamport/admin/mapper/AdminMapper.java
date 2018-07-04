package com.lamport.admin.mapper;

/**
 * Mapper, 提供Admin(SuperAdmin)信息的增加、删除、更新、查询功能
 * @author Lin Zhao, protector of Sherry
 *
 */
public interface AdminMapper {
	/**
	 * 通过adminname查询Admin的password
	 * @return String password
	 * @throws Exception
	 */
	public String selectPasswordByAdminname(String adminname) throws Exception;
	/**
	 * 通过adminname查询admin的jurisdiction
	 * @return String jurisdiction
	 * @throws Exception
	 */
	public String selectJurisdictionByAdminname(String adminname) throws Exception;
}
