package com.lamport.education.mapper;

import com.lamport.education.po.User;

public interface UserMapper {
	
	public void saveUser(User user) throws Exception;
	public void updateUser(User user) throws Exception;
	public User selectUserByQidAndTel(User user) throws Exception;
	public User selectUserByUId(int uid);
	

}
