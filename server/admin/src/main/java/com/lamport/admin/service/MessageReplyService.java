package com.lamport.admin.service;

/**
 * Service, 提供MessageReply信息的删除功能
 * @author Lin Zhao， protector of Sherry
 *
 */
public interface MessageReplyService {
	/**
	 * 通过id逻辑删除MessageReply信息
	 * @param id
	 * @return 1 删除成功 0 删除失败
	 * @throws Exception
	 */
	public int deleteMessageReplyLogicallyByID(int id) throws Exception;
}
