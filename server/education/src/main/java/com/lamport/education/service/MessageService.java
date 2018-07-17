package com.lamport.education.service;

import java.util.List;

import com.lamport.education.po.Message;
import com.lamport.education.po.MessageLike;
import com.lamport.education.po.MessageReply;
import com.lamport.education.util.PageBean;

public interface MessageService {
	
	public void saveMessageLike(MessageLike messageLike) throws Exception;
	public void saveMessageReply(MessageReply messageReply) throws Exception;
	public void deleteMessageLikeByMidAndUid(int mid, int uid) throws Exception;
	public List<Message> selectMessagePageByQid(PageBean page, int qid) throws Exception;
	public List<Message> selectMessageByQidDownFresh(int qid,int maxId) throws Exception;

}
