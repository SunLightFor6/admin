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
		int updateResult = 1;

		updateResult *= adminMapper.updatePasswordByID(admin);
		
		return updateResult;
	}

	@Override
	public Admin selectAdminByAdminname(String adminname) throws Exception {
		Admin admin = null;
				
		admin = adminMapper.selectAdminByAdminname(adminname);
		
		return admin;
	}

}
