package com.lamport.education.mapper;

import java.util.List;
import java.util.Map;

import com.lamport.education.po.Message;
import com.lamport.education.po.MessageLike;
import com.lamport.education.po.MessageReply;
import com.lamport.education.util.PageBean;
import com.lamport.education.vo.EnterpriseInfoVo;

public interface MessageMapper {

	public List<Message> selectMessagePageByQid(EnterpriseInfoVo enterpriseInfoVo) throws Exception;
	public List<Message> selectMessageByQidDownFresh(int qid,int maxId) throws Exception;
	public void saveMessageLike(MessageLike messageLike) throws Exception;
	public void saveMessageReply(MessageReply messageReply) throws Exception;
	public void deleteMessageLikeByMidAndUid(int mid, int uid) throws Exception;
}
