package com.lamport.admin.mapper;

import java.util.List;

import com.lamport.admin.po.User;

/**
 * Mapper, 提供User信息的删除、查询功能
 * @author Lin Zhao， protector of Sherry
 *
 */
public interface UserMapper {
	/**
	 * 通过qid逻辑删除User信息
	 * @return 1 删除成功 0 删除失败
	 * @throws Exception
	 */
	public int deleteUserLogicallyByQID(int qid) throws Exception;
	/**
	 * 通过uid更新User的totalpoint信息
	 * @param user
	 * @throws Exception
	 */
	public void updateUserTotalpointByUID(User user) throws Exception;
	/**
	 * 通过uid查询User信息
	 * @param uid
	 * @return 
	 * @throws Exception
	 */
	public User selectUserByUID(int uid) throws Exception;
	/**
	 * 通过qid查询User信息
	 * @param qid
	 * @return
	 * @throws Exception
	 */
	public List<User> selectUserByQID(int qid) throws Exception;
	/**
	 * 根据qid查询User的总数
	 * @return int
	 * @throws Exception
	 */
	public int selectCountUserByQID(int qid) throws Exception;
}
