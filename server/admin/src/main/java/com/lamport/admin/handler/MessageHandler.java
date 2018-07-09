package com.lamport.admin.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lamport.admin.service.MessageReplyService;
import com.lamport.admin.service.MessageService;

/**
 * Controller, 进行Messsage信息的增加、删除、查询
 * @author Lin Zhao, protector of Sherry
 *
 */
@Controller
public class MessageHandler {
	
	@Autowired
	private MessageService messageService;
	@Autowired
	private MessageReplyService messageReplyService;
	
	/**
	 * 创建Message信息
	 * @return
	 */
	@RequestMapping(value="/admin/saveMessage")
	@ResponseBody
	public String saveMessage() throws Exception{
		System.out.println("..........MessageHandler..........saveMessage()..........");
		String result = null;
		//TODO
		return result;
	}
	/**
	 * 通过id逻辑删除Message信息
	 * @return
	 */
	@RequestMapping(value="/admin/deleteMessageLogicallyByID")
	@ResponseBody
	public String deleteMessageLogicallyByID() throws Exception{
		System.out.println("..........MessageHandler..........deleteMessageLogicallyByID()..........");
		String result = null;
		//TODO
		return result;
	}
	/**
	 * 通过id逻辑删除MessageReply信息
	 * @return
	 */
	@RequestMapping(value="/admin/deleteMessageReplyLogicallyByID")
	@ResponseBody
	public String deleteMessageReplyLogicallyByID() throws Exception{
		System.out.println("..........MessageHandler..........deleteMessageReplyLogicallyByID()..........");
		String result = null;
		//TODO
		return result;
	}
	/**
	 * 通过id和页码查询Message信息
	 * @return
	 */
	@RequestMapping(value="/admin/selectMessageByQIDAndPage")
	@ResponseBody
	public String selectMessageByQIDAndPage() throws Exception{
		System.out.println("..........MessageHandler..........selectMessageByQIDAndPage()..........");
		String result = null;
		//TODO
		return result;
	}
}
