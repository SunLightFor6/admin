package com.lamport.admin.mapper;

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
	 * 根据qid查询User的总数
	 * @return int
	 * @throws Exception
	 */
	public int selectCountUserByQID(int qid) throws Exception;
}
