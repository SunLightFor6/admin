package com.lamport.admin.mapper;

import java.util.List;

import com.lamport.admin.po.Message;
import com.lamport.admin.po.MessageReply;

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
	/**
	 * 通过mid批量逻辑删除MessageReply信息
	 * @param messages
	 * @throws Exception
	 */
	public void deleteMultiMessageReplyLogicallyByMID(List<Message> messages) throws Exception;
	/**
	 * 通过mid查询MessageReply信息
	 * @param mid
	 * @return List
	 * @throws Exception
	 */
	public List<MessageReply> selectMessageReplyByMID(int mid) throws Exception;
}
