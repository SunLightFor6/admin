package com.lamport.admin.service;

import com.lamport.admin.po.Admin;

/**
 * Service, 提供验证Admin和SuperAdmin登录是否成功的功能
 * @author Lin Zhao, protector of Sherry
 *
 */
public interface LoginService {
	public static final String AdminJurisdiction = "管理员";
	public static final String SuperAdminJurisdiction = "超级管理员";
	/**
	 * 验证Admin登录是否成功
	 * @return 1 登录成功 0 密码错误 -1用户名不存在 -2权限验证失败
	 * @throws Exception
	 */
	public int isAdminLoginSuccessful(Admin admin) throws Exception;
	/**
	 * 验证SuperAdmin登录是否成功
	 * @return 1 登录成功 0 密码错误 -1用户名不存在 -2权限验证失败
	 * @throws Exception
	 */
	public int isSuperAdminLoginSuccessful(Admin superAdmin) throws Exception;
	
}
