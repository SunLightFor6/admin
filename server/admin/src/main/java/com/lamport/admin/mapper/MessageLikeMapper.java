package com.lamport.admin.mapper;

/**
 * Mapper, 提供MessageLike信息的删除功能
 * @author Lin Zhao， protector of Sherry
 *
 */
public interface MessageLikeMapper {
	/**
	 * 通过mid逻辑删除MessageLike信息
	 * @param mid
	 * @return 1 删除成功 0 删除失败
	 * @throws Exception
	 */
	public int deleteMessageLikeLogicallyByMID(int mid) throws Exception;
}
