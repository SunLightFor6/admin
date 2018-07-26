package com.lamport.education.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.lamport.education.po.Message;
import com.lamport.education.po.MessageLike;
import com.lamport.education.po.MessageReply;
import com.lamport.education.po.User;
import com.lamport.education.service.MessageService;
import com.lamport.education.service.UserService;
import com.lamport.education.util.Config;
import com.lamport.education.vo.QIDAndPage;

@Controller
public class MessageHandler {
	
	@Autowired
	MessageService messageService;
	@Autowired
	UserService userService;
	
	/**
	 * 创建MessageReply信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addMessageReply")
	@ResponseBody
	public String addMessageReply(MessageReply messageReply, HttpServletRequest request) throws Exception{
		System.out.println("..........MessageHandler..........addMessageReply..........");
		String result = null;
				
		HttpSession session = request.getSession();	
		User user = (User) session.getAttribute("user");
		messageReply.setUid(user.getUid());
		messageService.saveMessageReply(messageReply);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("state", 1);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 创建MessageLike信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addLike")
	@ResponseBody
	public String addLike(MessageLike messageLike, HttpServletRequest request) throws Exception{
		System.out.println("..........MessageHandler..........addLike..........");
		String result = null;
		
		HttpSession session = request.getSession();	
		User user = (User) session.getAttribute("user");
		messageLike.setUid(user.getUid());
		messageService.saveMessageLike(messageLike);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("nickname", user.getNickname());
		result = jsonObject.toString();		
		
		return result;
	}
	
	/**
	 * 取消点赞
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteLike")
	@ResponseBody
	public String deleteLike(MessageLike messageLike, HttpServletRequest request) throws Exception{
		System.out.println("..........MessageHandler..........deleteLike..........");
		String result;
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		messageLike.setUid(user.getUid());
		messageService.deleteMessageLikeLogicallyByMidAndUid(messageLike);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("state", 1);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 通过qid和页码查询Message信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectMessageByQidAndPage")
	@ResponseBody
	public String selectMessageByQidAndPage(QIDAndPage qidAndPage, HttpServletRequest request) throws Exception{
		System.out.println("..........MessageHandler..........selectMessageByQidAndPage..........");
		String result = null;
		
		HttpSession session = request.getSession();	
		int qid = (Integer)session.getAttribute("qid");
		qidAndPage.setQid(qid);
		qidAndPage.initPageBean(Config.MessagePageSize);
		List<Message> messages =  messageService.selectMessageByQidAndPage(qidAndPage);
		
		//TODO 为什么要remove(0)?
		//TODO json返回格式不详
		
		
		for(Message message:messages){
			if(message.getImgs().size()>0&&message.getImgs().get(0).getImgurl()==null)
				message.getImgs().remove(0);
			if(message.getLikes().size()>0&&(message.getLikes().get(0).getUser()==null||message.getLikes().get(0).getUser().getNickname()==null))
				message.getLikes().remove(0);
			if(message.getReplies().size()>0&&message.getReplies().get(0)==null)
				message.getReplies().remove(0);
		}
//		return messages;
		
		return result;
	}
	
	@RequestMapping("/selectAllMessageByQidDownFresh")
	@ResponseBody
	public String selectAllMessageByQidDownFresh(HttpServletRequest request) throws Exception{
		System.out.println("..........MessageHandler..........selectAllMessageByQidDownFresh..........");
		String result = null;
		
		int maxId = Integer.parseInt(request.getParameter("maxId"));
		HttpSession session = request.getSession();	
		int qid = (Integer)session.getAttribute("qid");
		System.out.println("maxId = " + maxId);							/*####################*/
		
		List<Message> messages =messageService.selectMessageByQidDownFresh(qid, maxId);
		
		//TODO 为什么要remove(0)?
		//TODO json返回格式不详
		
		for(Message message:messages){
			if(message.getImgs().size()>0&&message.getImgs().get(0).getImgurl()==null)
				message.getImgs().remove(0);
			if(message.getLikes().size()>0&&message.getLikes().get(0).getUser()==null)
				message.getLikes().remove(0);
			if(message.getReplies().size()>0&&message.getReplies().get(0)==null)
				message.getReplies().remove(0);
		}
//		return messages;
		
		return result;
	}
	

	

	

	
}
