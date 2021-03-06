package com.lamport.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamport.admin.mapper.MessageReplyMapper;
import com.lamport.admin.service.MessageReplyService;

/**
 * implements MessageReplyService
 * @author Lin Zhao, protector of Sherry
 *
 */
@Service
public class MessageReplyServiceBean implements MessageReplyService {

	@Autowired
	private MessageReplyMapper messageReplyMapper;
	
	@Override
	public int deleteMessageReplyLogicallyByID(int id) throws Exception {
		System.out.println("..........deleteMessageReplyLogicallyByID..........deleteMessageReplyLogicallyByID()..........");

		int deleteResult = 1;

		deleteResult *= messageReplyMapper.deleteMessageReplyLogicallyByID(id);
		
		return deleteResult;
	}

}
