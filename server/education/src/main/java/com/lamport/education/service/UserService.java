package com.lamport.education.service;

import org.springframework.web.multipart.MultipartFile;

import com.lamport.education.po.User;
import com.lamport.education.vo.QIDAndTel;

public interface UserService {
	
	public void updateUser(User user) throws Exception;
	public void updateUserimg(User user, MultipartFile multipartFile) throws Exception;
	public User selectUserByUid(int uid) throws Exception;
	/**
	 * 通过qid和tel查询User信息
	 * @param qidAndTel
	 * @return User user
	 * @throws Exception
	 */
	public User selectUserByQidAndTel(QIDAndTel qidAndTel) throws Exception;
}
