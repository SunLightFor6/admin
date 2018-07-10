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
		int result = 0;
		String jurisdiction = adminMapper.selectJurisdictionByAdminname(admin.getAdminname());
		if(jurisdiction != null){
			if(AdminJurisdiction.equals(jurisdiction)){
				String password = adminMapper.selectPasswordByAdminname(admin.getAdminname());
				if(password.equals(admin.getPassword())){
					result = 1;
				}else{
					result = 0;
				}
			}else{
				result = -2;
			}
		}else{
			result = -1;
		}
		return result;
	}

	@Override
	public int isSuperAdminLoginSuccessful(Admin superAdmin) throws Exception {
		int result = 0;
		String jurisdiction = adminMapper.selectJurisdictionByAdminname(superAdmin.getAdminname());
		if(jurisdiction != null){
			if(SuperAdminJurisdiction.equals(jurisdiction)){
				String password = adminMapper.selectPasswordByAdminname(superAdmin.getAdminname());
				if(password.equals(superAdmin.getPassword())){
					result = 1;
				}else{
					result = 0;
				}
			}else{
				result = -2;
			}
		}else{
			result = -1;
		}
		return result;
	}

}
