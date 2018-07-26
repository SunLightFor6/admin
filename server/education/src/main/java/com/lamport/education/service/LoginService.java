package com.lamport.education.service;

import com.lamport.education.po.User;
import com.lamport.education.vo.QIDAndTel;

/**
 * Service, 提供验证User登录是否成功的功能
 * @author Lin Zhao, protector of Sherry
 *
 */
public interface LoginService {
	/**
	 * 通过qid和tel查询User信息
	 * @param qidAndTel
	 * @return User user
	 * @throws Exception
	 */
	public User selectUserByQidAndTel(QIDAndTel qidAndTel) throws Exception;
}
