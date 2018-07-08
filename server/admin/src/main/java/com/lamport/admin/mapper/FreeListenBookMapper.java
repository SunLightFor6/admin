package com.lamport.admin.mapper;

/**
 * Mapper, 提供FreeListenBook信息的增加、删除、更新、查询功能
 * @author Lin Zhao， protector of Sherry
 *
 */
public interface FreeListenBookMapper {
	/**
	 * 通过fid逻辑删除FreeListenBook信息
	 * @param id
	 * @return 1 删除成功 0 删除失败
	 * @throws Exception
	 */
	public int deleteFreeListenBookLogicallyByFID(int fid) throws Exception;
	/**
	 * 根据qid查询FreeListenBook的总数
	 * @return int
	 * @throws Exception
	 */
	public int selectCountFreeListenBookByQID(int qid) throws Exception;
}
