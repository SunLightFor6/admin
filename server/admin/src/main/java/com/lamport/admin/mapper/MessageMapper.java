package com.lamport.admin.mapper;

/**
 * Mapper, 提供Message信息的增加、删除、查询功能
 * @author Lin Zhao， protector of Sherry
 *
 */
public interface MessageMapper {
	/**
	 * 通过qid逻辑删除Message的信息
	 * @return 1 删除成功 0 删除失败
	 * @throws Exception
	 */
	public int deleteMessageLogicallyByQID(int qid) throws Exception;
}
