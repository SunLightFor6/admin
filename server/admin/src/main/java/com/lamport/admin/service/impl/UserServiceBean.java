package com.lamport.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamport.admin.mapper.UserMapper;
import com.lamport.admin.service.UserService;

/**
 * implements UserService
 * @author Lin Zhao, protector of Sherry
 *
 */
@Service
public class UserServiceBean implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public int selectCountUserByQID(int qid) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
