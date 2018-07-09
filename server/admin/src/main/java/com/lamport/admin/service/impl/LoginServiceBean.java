package com.lamport.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamport.admin.mapper.AdminMapper;
import com.lamport.admin.po.Admin;
import com.lamport.admin.service.LoginService;

/**
 * implements LoginService
 * @author Lin Zhao, protector of Sherry
 *
 */
@Service
public class LoginServiceBean implements LoginService {
	
	@Autowired
	private AdminMapper adminMapper;
	
	@Override
	public int isAdminLoginSuccessful(Admin admin) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int isSuperAdminLoginSuccessful(Admin admin) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
