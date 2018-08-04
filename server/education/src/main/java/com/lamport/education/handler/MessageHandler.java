package com.lamport.education.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lamport.education.po.Message;
import com.lamport.education.po.MessageImg;
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
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		if(messages!=null && !messages.isEmpty()){
			for(Message message : messages){
				JsonObject object = new JsonObject();
				object.addProperty("mid", message.getMid());
				object.addProperty("mtitle", message.getMtitle());
				object.addProperty("mtime", message.getMtime());
				JsonArray imgArray = new JsonArray();
				if(message.getImgs()!=null && !message.getImgs().isEmpty()){
					for(MessageImg img : message.getImgs()){
						JsonObject imgObject = new JsonObject();
						imgObject.addProperty("imgurl", img.getImgurl());
						imgArray.add(imgObject);
					}
				}
				object.add("messageimgs", imgArray);
				JsonArray likeArray = new JsonArray();
				if(message.getLikes()!=null && !message.getLikes().isEmpty()){
					for(MessageLike like : message.getLikes()){
						JsonObject likeObject = new JsonObject();
						likeObject.addProperty("nickname", like.getUser().getNickname());
						likeObject.addProperty("uid", like.getUser().getUid());
						likeArray.add(likeObject);
					}
				}
				object.add("messagelikes", likeArray);
				JsonArray replyArray = new JsonArray();
				if(message.getReplies()!=null && !message.getReplies().isEmpty()){
					for(MessageReply reply : message.getReplies()){
						JsonObject replyObject = new JsonObject();
						replyObject.addProperty("nickname", reply.getUser().getNickname());
						replyObject.addProperty("content", reply.getContent());
						replyArray.add(replyObject);
					}
				}
				object.add("messagereply", replyArray);
				jsonArray.add(object);
			}
		}
		jsonObject.add("messages", jsonArray);
		result = jsonObject.toString();
		
//为什么要remove(0)?
//		for(Message message:messages){
//			if(message.getImgs().size()>0&&message.getImgs().get(0).getImgurl()==null)
//				message.getImgs().remove(0);
//			if(message.getLikes().size()>0&&(message.getLikes().get(0).getUser()==null||message.getLikes().get(0).getUser().getNickname()==null))
//				message.getLikes().remove(0);
//			if(message.getReplies().size()>0&&message.getReplies().get(0)==null)
//				message.getReplies().remove(0);
//		}
//		return messages;
		
		return result;
	}
	
	/**
	 * 通过下拉刷新查询Message信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectMessageByQidDownFresh")
	@ResponseBody
	public String selectMessageByQidDownFresh(int startId, HttpServletRequest request) throws Exception{
		System.out.println("..........MessageHandler..........selectMessageByQidDownFresh..........");
		String result = null;
		
		int maxId = Integer.parseInt(request.getParameter("maxId"));
		HttpSession session = request.getSession();	
		int qid = (Integer)session.getAttribute("qid");
		System.out.println("maxId = " + maxId);							/*####################*/
		List<Message> messages =messageService.selectMessageByQidDownFresh(qid, maxId);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		if(messages!=null && !messages.isEmpty()){
			for(Message message : messages){
				JsonObject object = new JsonObject();
				object.addProperty("mid", message.getMid());
				object.addProperty("mtitle", message.getMtitle());
				object.addProperty("mtime", message.getMtime());
				JsonArray imgArray = new JsonArray();
				if(message.getImgs()!=null && !message.getImgs().isEmpty()){
					for(MessageImg img : message.getImgs()){
						JsonObject imgObject = new JsonObject();
						imgObject.addProperty("imgurl", img.getImgurl());
						imgArray.add(imgObject);
					}
				}
				object.add("messageimgs", imgArray);
				JsonArray likeArray = new JsonArray();
				if(message.getLikes()!=null && !message.getLikes().isEmpty()){
					for(MessageLike like : message.getLikes()){
						JsonObject likeObject = new JsonObject();
						likeObject.addProperty("nickname", like.getUser().getNickname());
						likeArray.add(likeObject);
					}
				}
				object.add("messagelikes", likeArray);
				JsonArray replyArray = new JsonArray();
				if(message.getReplies()!=null && !message.getReplies().isEmpty()){
					for(MessageReply reply : message.getReplies()){
						JsonObject replyObject = new JsonObject();
						replyObject.addProperty("nickname", reply.getUser().getNickname());
						replyObject.addProperty("content", reply.getContent());
						replyArray.add(replyObject);
					}
				}
				object.add("messagereply", replyArray);
				jsonArray.add(object);
			}
		}
		jsonObject.add("messages", jsonArray);
		result = jsonObject.toString();
		
//		for(Message message:messages){
//			if(message.getImgs().size()>0&&message.getImgs().get(0).getImgurl()==null)
//				message.getImgs().remove(0);
//			if(message.getLikes().size()>0&&message.getLikes().get(0).getUser()==null)
//				message.getLikes().remove(0);
//			if(message.getReplies().size()>0&&message.getReplies().get(0)==null)
//				message.getReplies().remove(0);
//		}
//		return messages;
		
		return result;
	}
	

	

	

	
}
