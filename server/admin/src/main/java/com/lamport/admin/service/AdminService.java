package com.lamport.admin.service;

import com.lamport.admin.po.Admin;

/**
 * Service, 提供Admin信息的增加、删除、更新、查询功能
 * @author Lin Zhao， protector of Sherry
 *
 */
public interface AdminService {
	/**
	 * 通过id修改Admin的password
	 * @param admin
	 * @return
	 * @throws Exception
	 */
	public int updatePasswordByID(Admin admin) throws Exception;
	/**
	 * 根据adminname查询Admin(SuperAdmin)的信息
	 * @param adminname
	 * @return Admin
	 * @throws Exception
	 */
	public Admin selectAdminByAdminname(String adminname) throws Exception;
}
