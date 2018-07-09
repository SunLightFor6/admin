package com.lamport.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lamport.admin.mapper.MessageImgMapper;
import com.lamport.admin.mapper.MessageLikeMapper;
import com.lamport.admin.mapper.MessageMapper;
import com.lamport.admin.mapper.MessageReplyMapper;
import com.lamport.admin.po.Message;
import com.lamport.admin.service.MessageService;
import com.lamport.admin.vo.QIDAndPage;

/**
 * implements MessageService
 * @author Lin Zhao, protector of Sherry
 *
 */
@Service
public class MessageServiceBean implements MessageService {

	@Autowired
	private MessageMapper messageMapper;
	@Autowired
	private MessageImgMapper messageImgMapper;
	@Autowired
	private MessageLikeMapper messageLikeMapper;
	@Autowired
	private MessageReplyMapper messageReplyMapper;
	
	@Override
	public int saveMessage(Message message, MultipartFile[] imgs) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMessageLogicallyByID(int id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Message> selectMessageByQIDAndPage(QIDAndPage qidAndPage) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
