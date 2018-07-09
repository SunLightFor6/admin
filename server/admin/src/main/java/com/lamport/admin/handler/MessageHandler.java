package com.lamport.admin.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller, 进行Messsage信息的增加、删除、查询
 * @author Lin Zhao, protector of Sherry
 *
 */
@Controller
public class MessageHandler {
	/**
	 * 创建Message信息
	 * @return
	 */
	@RequestMapping(value="/admin/saveMessage")
	public String saveMessage(){
		System.out.println("..........MessageHandler..........saveMessage()..........");
		//TODO
		return"";
	}
	/**
	 * 通过id逻辑删除Message信息
	 * @return
	 */
	@RequestMapping(value="/admin/deleteMessageLogicallyByID")
	public String deleteMessageLogicallyByID(){
		System.out.println("..........MessageHandler..........deleteMessageLogicallyByID()..........");
		//TODO
		return"";
	}
	/**
	 * 通过id逻辑删除MessageReply信息
	 * @return
	 */
	@RequestMapping(value="/admin/deleteMessageReplyLogicallyByID")
	public String deleteMessageReplyLogicallyByID(){
		System.out.println("..........MessageHandler..........deleteMessageReplyLogicallyByID()..........");
		//TODO
		return"";
	}
	/**
	 * 通过id和页码查询Message信息
	 * @return
	 */
	@RequestMapping(value="/admin/selectMessageByQIDAndPage")
	public String selectMessageByQIDAndPage(){
		System.out.println("..........MessageHandler..........selectMessageByQIDAndPage()..........");
		//TODO
		return"";
	}
}
