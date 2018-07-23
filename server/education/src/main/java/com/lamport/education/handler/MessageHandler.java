package com.lamport.education.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lamport.education.po.Message;
import com.lamport.education.po.MessageLike;
import com.lamport.education.po.MessageReply;
import com.lamport.education.po.Teacher;
import com.lamport.education.po.User;
import com.lamport.education.service.MessageService;
import com.lamport.education.service.UserService;
import com.lamport.education.util.PageBean;

@Controller
public class MessageHandler {
	@Autowired
	MessageService messageService;
	@Autowired
	UserService userService;
	
	/**
	 * 
	 * @param request  获取企业qid,以及currentPage然后查询
	 * @return 返回Message对象列表
	 * @throws Exception 
	 */
	@RequestMapping("/selectAllMessageByPageAndQid")
	@ResponseBody
	public List<Message> selectAllMessageByPage(HttpServletRequest request) throws Exception{
		System.out.println(".....MessageHandler.....selectAllMessageByPageAndQid........");
		int rowId = Integer.parseInt(request.getParameter("rowId"));
		PageBean page = new PageBean(2, rowId );
		HttpSession session = request.getSession();	
		int qid = (Integer)session.getAttribute("qid");
		List<Message> messages =  messageService.selectMessagePageByQid(page, qid);
		for(Message message:messages){
			if(message.getImgs().size()>0&&message.getImgs().get(0).getImgurl()==null)
				message.getImgs().remove(0);
			if(message.getLikes().size()>0&&(message.getLikes().get(0).getUser()==null||message.getLikes().get(0).getUser().getNickname()==null))
				message.getLikes().remove(0);
			if(message.getReplies().size()>0&&message.getReplies().get(0)==null)
				message.getReplies().remove(0);
		}
		return messages;
		
	}
	

	
	@RequestMapping("/selectAllMessageByQidDownFresh")
	@ResponseBody
	public List<Message> selectAllMessageByQidDownFresh(HttpServletRequest request) throws Exception{
		System.out.println(".....MessageHandler.....selectAllMessageByQidDownFresh........");
		int maxId = Integer.parseInt(request.getParameter("maxId"));
		HttpSession session = request.getSession();	
		int qid = (Integer)session.getAttribute("qid");
		System.out.println("maxId"+maxId);
		List<Message> messages =messageService.selectMessageByQidDownFresh(qid, maxId);
		for(Message message:messages){
			if(message.getImgs().size()>0&&message.getImgs().get(0).getImgurl()==null)
				message.getImgs().remove(0);
			if(message.getLikes().size()>0&&message.getLikes().get(0).getUser()==null)
				message.getLikes().remove(0);
			if(message.getReplies().size()>0&&message.getReplies().get(0)==null)
				message.getReplies().remove(0);
		}
		return messages;
		
	}
	
	
	
	
	/**
	 * 
	 * @param request  获取信息mid,uid 然后添加点赞
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping("/addLike")
	@ResponseBody
	public User addLike(HttpServletRequest request) throws Exception{
		System.out.println(".....MessageHandler......addLike..........");
		HttpSession session = request.getSession();	
		int qid = (Integer)session.getAttribute("qid");
		User user = (User) session.getAttribute("user");
		int uid = user.getUid();
		int mid = Integer.parseInt(request.getParameter("mid"));
		MessageLike messageLike = new MessageLike();
		messageLike.setDeletekey(0);
		messageLike.setMid(mid);
		messageLike.setUid(uid);
		messageService.saveMessageLike(messageLike);
		return userService.selectUserByUid(uid);
	}
	/**
	 * 
	 * @param request  获取信息mid,uid 然后删除点赞
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping("/deleteLike")
	@ResponseBody
	public void deleteLike(HttpServletRequest request) throws Exception{
		System.out.println(".....MessageHandler......deleteLike........");
		HttpSession session = request.getSession();	
		User user = (User) session.getAttribute("user");
		int uid = user.getUid();
		int mid = Integer.parseInt(request.getParameter("mid"));
		messageService.deleteMessageLikeByMidAndUid(mid, uid);
	}
	/**
	 * @param request  获取信息mid,uid,nickname,content
	 * @return string  成功 "{\"result\":true}";   失败 "{\"result\":false}";
	 * @throws Exception 
	 */
	@RequestMapping("/addMessageReply")
	@ResponseBody
	public String addMessageReply(HttpServletRequest request) throws Exception{
		System.out.println(".....MessageHandler......addMessageReply........");
		HttpSession session = request.getSession();	
		User user = (User) session.getAttribute("user");
		int uid = user.getUid();
		int mid = Integer.parseInt(request.getParameter("mid"));
		String content = request.getParameter("comment");
		MessageReply messageReply =new MessageReply();
		messageReply.setDeletekey(0);
		messageReply.setMid(mid);
		messageReply.setUid(uid);
		messageReply.setContent(content);
		messageService.saveMessageReply(messageReply);
		return null;
	}
	
}
