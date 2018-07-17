package com.lamport.education.mapper;

import com.lamport.education.po.MessageLike;

public interface MessageLikeMapper {
	
	public void saveMessageLike(MessageLike messageLike) throws Exception;
	public void deleteMessageLikeByMidAndUid(int mid, int uid) throws Exception;


}
