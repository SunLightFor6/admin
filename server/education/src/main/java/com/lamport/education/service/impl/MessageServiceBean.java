package com.lamport.education.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamport.education.mapper.MessageMapper;
import com.lamport.education.mapper.MessageLikeMapper;
import com.lamport.education.mapper.MessageReplyMapper;
import com.lamport.education.po.Message;
import com.lamport.education.po.MessageLike;
import com.lamport.education.po.MessageReply;
import com.lamport.education.util.PageBean;
import com.lamport.education.vo.EnterpriseInfoVo;

@Service
public class MessageServiceBean implements com.lamport.education.service.MessageService {
	@Autowired
	MessageMapper messageMapper;
	
	
	@Override
	public void deleteMessageLikeByMidAndUid(int mid, int uid) throws Exception {
		messageMapper.deleteMessageLikeByMidAndUid(mid, uid);
	}

	@Override
	public void saveMessageLike(MessageLike messageLike) throws Exception {
		messageMapper.saveMessageLike(messageLike);
	}


	@Override
	public void saveMessageReply(MessageReply messageReply) throws Exception {
		messageMapper.saveMessageReply(messageReply);
	}


	@Override
	public List<Message> selectMessagePageByQid(PageBean page, int qid) throws Exception {
		EnterpriseInfoVo enterpriseInfoVo = new EnterpriseInfoVo();
		enterpriseInfoVo.setPage(page);
		enterpriseInfoVo.setQid(qid);
		return messageMapper.selectMessagePageByQid(enterpriseInfoVo);
		
	}

	@Override
	public List<Message> selectMessageByQidDownFresh(int qid, int maxId) throws Exception {
		// TODO Auto-generated method stub
		return messageMapper.selectMessageByQidDownFresh(qid, maxId);
	}

}
