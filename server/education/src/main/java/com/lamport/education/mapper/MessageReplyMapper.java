package com.lamport.education.mapper;

import com.lamport.education.po.MessageReply;

public interface MessageReplyMapper {
	/**
	 * 创建MessageReply信息
	 * @param messageReply
	 * @throws Exception
	 */
	public void saveMessageReply(MessageReply messageReply) throws Exception;
}
