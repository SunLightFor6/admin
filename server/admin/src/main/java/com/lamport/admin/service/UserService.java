package com.lamport.admin.service;

/**
 * Service, 提供User信息的查询功能
 * @author Lin Zhao， protector of Sherry
 *
 */
public interface UserService {
	/**
	 * 根据qid查询User的总数
	 * @return int
	 * @throws Exception
	 */
	public int selectCountUserByQID(int qid) throws Exception;
}
