package com.lamport.education.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamport.education.mapper.MessageLikeMapper;
import com.lamport.education.mapper.MessageMapper;
import com.lamport.education.mapper.MessageReplyMapper;
import com.lamport.education.po.Message;
import com.lamport.education.po.MessageLike;
import com.lamport.education.po.MessageReply;
import com.lamport.education.vo.QIDAndPage;

@Service
public class MessageServiceBean implements com.lamport.education.service.MessageService {

	@Autowired
	MessageMapper messageMapper;
	@Autowired
	MessageLikeMapper messageLikeMapper;
	@Autowired
	MessageReplyMapper messageReplyMapper;	
	
	@Override
	public void saveMessageReply(MessageReply messageReply) throws Exception {
		messageReply.setDeletekey(0);
		messageReplyMapper.saveMessageReply(messageReply);
	}

	@Override
	public void saveMessageLike(MessageLike messageLike) throws Exception {
		messageLike.setDeletekey(0);
		messageLikeMapper.saveMessageLike(messageLike);
	}
	
	@Override
	public void deleteMessageLikeLogicallyByMidAndUid(MessageLike messageLike) throws Exception {
		messageLike.setDeletekey(1);
		messageLikeMapper.deleteMessageLikeLogicallyByMidAndUid(messageLike);
	}
	
	@Override
	public List<Message> selectMessageByQidAndPage(QIDAndPage qidAndPage) throws Exception {
		List<Message> messages = null;
		
		messages = messageMapper.selectMessageByQidAndPage(qidAndPage);
		
		return messages;
	}

	@Override
	public List<Message> selectMessageByQidDownFresh(int qid, int maxId) throws Exception {
		// TODO Auto-generated method stub
		return messageMapper.selectMessageByQidDownFresh(qid, maxId);
	}

}
