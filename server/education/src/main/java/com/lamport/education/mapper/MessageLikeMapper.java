package com.lamport.education.mapper;

import com.lamport.education.po.MessageLike;

public interface MessageLikeMapper {
	/**
	 * 创建MessageLike信息
	 * @param messageLike
	 * @throws Exception
	 */
	public void saveMessageLike(MessageLike messageLike) throws Exception;
	
	/**
	 * 通过mid和uid逻辑删除MessageLike信息
	 * @param messageLike
	 * @throws Exception
	 */
	public void deleteMessageLikeLogicallyByMidAndUid(MessageLike messageLike) throws Exception;
}
