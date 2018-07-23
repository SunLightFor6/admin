package com.lamport.education.service;

import com.lamport.education.po.User;

public interface UserService {
	
	public void updateUser(User user) throws Exception;
	public User selectLoginInfoByQidAndTel(int qid, String tel) throws Exception;
	public User selectUserByUid(int uid) throws Exception;

}
