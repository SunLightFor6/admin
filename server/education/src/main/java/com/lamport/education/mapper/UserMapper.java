package com.lamport.education.mapper;

import com.lamport.education.po.User;
import com.lamport.education.vo.QIDAndTel;

public interface UserMapper {
	
	public void saveUser(User user) throws Exception;
	public void updateUser(User user) throws Exception;
	public void updateUserimg(User user) throws Exception;
	public User selectUserByUId(int uid);
	/**
	 * 通过qid和tel查询User信息
	 * @param qidAndTel
	 * @return User user
	 * @throws Exception
	 */
	public User selectUserByQidAndTel(QIDAndTel qidAndTel) throws Exception;
}
