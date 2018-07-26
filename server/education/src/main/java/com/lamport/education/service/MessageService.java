package com.lamport.education.service;

import java.util.List;

import com.lamport.education.po.Message;
import com.lamport.education.po.MessageLike;
import com.lamport.education.po.MessageReply;
import com.lamport.education.vo.QIDAndPage;

public interface MessageService {
	/**
	 * 创建MessageReply信息
	 * @param messageReply
	 * @throws Exception
	 */
	public void saveMessageReply(MessageReply messageReply) throws Exception;
	
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
	
	/**
	 * 通过qid和页码查询Message信息
	 * @param qidAndPage
	 * @return List<Message>
	 * @throws Exception
	 */
	public List<Message> selectMessageByQidAndPage(QIDAndPage qidAndPage) throws Exception;
	
	/**
	 * 通过qid查询Message信息
	 * @param qid
	 * @param maxId
	 * @return
	 * @throws Exception
	 */
	public List<Message> selectMessageByQidDownFresh(int qid,int maxId) throws Exception;

}
