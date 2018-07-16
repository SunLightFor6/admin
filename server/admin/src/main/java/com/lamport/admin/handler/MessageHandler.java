package com.lamport.admin.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lamport.admin.po.Admin;
import com.lamport.admin.po.Message;
import com.lamport.admin.po.MessageImg;
import com.lamport.admin.po.MessageReply;
import com.lamport.admin.service.MessageReplyService;
import com.lamport.admin.service.MessageService;
import com.lamport.admin.tool.Const;
import com.lamport.admin.vo.QIDAndPage;

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
	public String saveMessage(Message new_message, @RequestParam("message_pics") MultipartFile[] message_pics, HttpServletRequest request) throws Exception{
		System.out.println("..........MessageHandler..........saveMessage()..........new_message: " + new_message.getMtitle() + " message_pics = " + message_pics.length);
		String result = null;
		
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		new_message.setQid(admin.getQid());
		String path = Const.Path;
		int saveResult = messageService.saveMessage(new_message, message_pics, path);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("response", saveResult);
		result = jsonObject.toString();
		
		return result;
	}
	/**
	 * 通过id逻辑删除Message信息
	 * @return
	 */
	@RequestMapping(value="/admin/deleteMessageLogicallyByID")
	@ResponseBody
	public String deleteMessageLogicallyByID(int id) throws Exception{
		System.out.println("..........MessageHandler..........deleteMessageLogicallyByID()..........id = " + id);
		String result = null;

		int deleteResult = messageService.deleteMessageLogicallyByID(id);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("response", deleteResult);
		result = jsonObject.toString();
		
		return result;
	}
	/**
	 * 通过id逻辑删除MessageReply信息
	 * @return
	 */
	@RequestMapping(value="/admin/deleteMessageReplyLogicallyByID")
	@ResponseBody
	public String deleteMessageReplyLogicallyByID(int id_comment, int id_message) throws Exception{
		System.out.println("..........MessageHandler..........deleteMessageReplyLogicallyByID()..........id_comment = " + id_comment + " id_message" + id_message);
		String result = null;

		int deleteResult = messageReplyService.deleteMessageReplyLogicallyByID(id_comment);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("response", deleteResult);
		result = jsonObject.toString();
		
		return result;
	}
	/**
	 * 通过id和页码查询Message信息
	 * @return
	 */
	@RequestMapping(value="/admin/selectMessageByQIDAndPage")
	@ResponseBody
	public String selectMessageByQIDAndPage(QIDAndPage qidAndPage, HttpServletRequest request) throws Exception{
		System.out.println("..........MessageHandler..........selectMessageByQIDAndPage()..........qidAndPage:" + qidAndPage.getPageTool().getPage() + " " + qidAndPage.getPageTool().getLimit());
		String result = null;

		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		qidAndPage.setQid(admin.getQid());
		qidAndPage.setPageTool();
		List<Message> messages = messageService.selectMessageByQIDAndPage(qidAndPage);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("pages", qidAndPage.getPageTool().getPages());
		jsonObject.addProperty("page", qidAndPage.getPageTool().getPage());
		JsonArray jsonArray = new JsonArray();
		for(Message message : messages){
			JsonObject object = new JsonObject();
			object.addProperty("id", message.getMid());
			object.addProperty("name", message.getEnterprise().getName());
			object.addProperty("logo", message.getSwiper().getImgurl());
			object.addProperty("content", message.getMtitle());
			JsonArray imgurls = new JsonArray();
			for(MessageImg img: message.getImgs()){
				imgurls.add(img.getImgurl());
			}
			object.add("imgurls", imgurls);
			object.addProperty("stime", message.getMtime());
			JsonArray comments = new JsonArray();
			for(MessageReply reply: message.getReplies()){
				JsonObject comment = new JsonObject();
				comment.addProperty("id", reply.getId());
				comment.addProperty("name", reply.getUser().getNickname());
				comment.addProperty("comment", reply.getContent());
				comments.add(comment);
			}
			object.add("comments", comments);
			jsonArray.add(object);
		}
		jsonObject.add("messages", jsonArray);
		result = jsonObject.toString();		
		
		return result;
	}
}
