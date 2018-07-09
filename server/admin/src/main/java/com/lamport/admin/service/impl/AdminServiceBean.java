package com.lamport.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamport.admin.mapper.AdminMapper;
import com.lamport.admin.po.Admin;
import com.lamport.admin.service.AdminService;

/**
 * implements AdminService
 * @author Lin Zhao, protector of Sherry
 *
 */
@Service
public class AdminServiceBean implements AdminService {

	@Autowired
	private AdminMapper adminMapper;
	
	@Override
	public int updatePasswordByID(Admin admin) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Admin selectAdminByAdminname(String adminname) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
