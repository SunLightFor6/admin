package com.lamport.admin.mapper;

/**
 * Mapper, 提供MessageReply信息的删除功能
 * @author Lin Zhao， protector of Sherry
 *
 */
public interface MessageReplyMapper {
	/**
	 * 通过id逻辑删除MessageReply信息
	 * @param id
	 * @return 1 删除成功 0 删除失败
	 * @throws Exception
	 */
	public int deleteMessageReplyLogicallyByID(int id) throws Exception;
	/**
	 * 通过mid逻辑删除MessageReply信息
	 * @param mid
	 * @return 1 删除成功 0 删除失败
	 * @throws Exception
	 */
	public int deleteMessageReplyLogicallyByMID(int mid) throws Exception;
}
