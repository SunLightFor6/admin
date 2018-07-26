package com.lamport.education.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.lamport.education.po.User;
import com.lamport.education.service.UserService;

@Controller
public class UserHandler {
	
	@Autowired
	UserService userService;	
	
//	@RequestMapping("/selectUserByUserIdAndTel")
//	@ResponseBody
//	public User selectUserByUserIdAndTel(HttpServletRequest request) throws Exception{
//		System.out.println("....UserHandler.....selectUserByUserIdAndTel.......");
//		HttpSession session = request.getSession();
//		int qid = (Integer)session.getAttribute("qid");
//		String tel = request.getParameter("tel");
//		return userService.selectLoginInfoByQidAndTel(qid, tel);
//	}
 
//	@RequestMapping("/selectUserByUid")
//	@ResponseBody
//	public User selectUserByUid(HttpServletRequest request) throws Exception{
//		System.out.println("..........EnterpriseHandler..........selectUserNameById..........");
//		
//		HttpSession session = request.getSession();
//		User user = (User) session.getAttribute("user");
//		int uid = user.getUid();		
//		
//		return userService.selectUserByUid(uid);
//	}
	
	@RequestMapping("/getUser")
	@ResponseBody
	public String getUser(HttpServletRequest request) throws Exception{
		System.out.println("..........UserHandler..........getUser..........");
		String result = null;
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			user = new User();
			user.setUid(-1);
		}
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("nickname", user.getNickname());
		jsonObject.addProperty("username", user.getUsername());
		jsonObject.addProperty("tel", user.getTel());
		result = jsonObject.toString();
		System.out.println(result);								/*####################*/
		
		return result;
	}
	
	
	
	
	@RequestMapping("/updateUser")
	@ResponseBody
	public String updateUser(User u, HttpServletRequest request) throws Exception{
		System.out.println("..........UserHandler..........updateUser..........");
		String result = null;
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		user.setTel(u.getTel());
		user.setNickname(u.getNickname());
		user.setUsername(u.getUsername());
		userService.updateUser(user);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("state", 1);
		result = jsonObject.toString();
		
		return result;
	}
	
	@RequestMapping("/updateUserimg")
	@ResponseBody
	public String updateUserimg(HttpServletRequest request, @RequestParam MultipartFile multipartFile) throws Exception{
		System.out.println("..........UserHandler..........updateUserimg..........");
		String result = null;
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		userService.updateUserimg(user, multipartFile);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("userimg", user.getUserimg());
		result = jsonObject.toString();
		
		return result;
	}
	
}
