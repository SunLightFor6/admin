package com.lamport.admin.mapper;

import com.lamport.admin.po.Admin;

/**
 * Mapper, 提供Admin(SuperAdmin)信息的增加、删除、修改、查询功能
 * @author Lin Zhao, protector of Sherry
 *
 */
public interface AdminMapper {
	/**
	 * 创建Admin
	 * @param admin
	 * @return 1 保存成功 0 保存失败
	 * @throws Exception
	 */
	public int saveAdmin(Admin admin) throws Exception;
	/**
	 * 通过qid逻辑删除Admin信息
	 * @return 1 删除成功 0 删除失败
	 * @throws Exception
	 */
	public int deleteAdminLogicallyByQID(int qid) throws Exception;
	/**
	 * 通过id修改Admin的password
	 * @param admin
	 * @return
	 * @throws Exception
	 */
	public int updatePasswordByID(Admin admin) throws Exception;
	/**
	 * 根据adminname查询Admin(SuperAdmin)信息
	 * @param adminname
	 * @return Admin
	 * @throws Exception
	 */
	public Admin selectAdminByAdminname(String adminname) throws Exception;
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
