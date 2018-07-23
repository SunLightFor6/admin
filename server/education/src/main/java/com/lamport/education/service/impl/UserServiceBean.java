package com.lamport.education.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamport.education.mapper.UserMapper;
import com.lamport.education.po.User;

@Service
public class UserServiceBean implements com.lamport.education.service.UserService {
	
	@Autowired
	UserMapper userMapper;
	
	@Override
	public void updateUser(User user) throws Exception { 
		userMapper.updateUser(user);
	}
	
	@Override
	public User selectLoginInfoByQidAndTel(int qid, String tel) throws Exception {
		//创建用户对象
		User user = new User();
		user.setQid(qid);
		user.setTel(tel);
		user.setNickname(tel.substring(0, 3) + "****" + tel.substring(7, 11));
		user.setUserimg("portrait.jpg");
		user.setDeletekey(0);
		//数据库查询用户信息
		User selectUser = userMapper.selectUserByQidAndTel(user);
		if (selectUser == null || selectUser.getTel() == null || selectUser.getTel().equals("")) {
			userMapper.saveUser(user);
		}
		else {
			user = selectUser;
		}
		return user;
	}
	
	@Override
	public User selectUserByUid(int uid) throws Exception {		 
		return userMapper.selectUserByUId(uid);
	}

}
