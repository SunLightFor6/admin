package com.lamport.education.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lamport.education.po.User;
import com.lamport.education.service.UserService;
import com.lamport.education.util.IndustrySMS;

@Controller
public class UserHandler {
	
	@Autowired
	UserService userService;	
	
	@RequestMapping("/selectUserByUserIdAndTel")
	@ResponseBody
	public User selectUserByUserIdAndTel(HttpServletRequest request) throws Exception{
		System.out.println("....UserHandler.....selectUserByUserIdAndTel.......");
		HttpSession session = request.getSession();
		int qid = (Integer)session.getAttribute("qid");
		String tel = request.getParameter("tel");
		return userService.selectLoginInfoByQidAndTel(qid, tel);
	}
	
 
	@RequestMapping("/selectUserByUid")
	@ResponseBody
	public User selectUserByUid(HttpServletRequest request) throws Exception{
		System.out.println("....EnterpriseHandler.....selectUserNameById.......");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int uid = user.getUid();		
		return userService.selectUserByUid(uid);
	}	
	
	
	@RequestMapping("/sendMobileCode")
	@ResponseBody
	public String sendMobileCode(HttpServletRequest request, String tel) throws Exception{
		System.out.println("....UserHandler.....sendMobileCode.......");
		int state = 0;
		String code = "";
		try {
			//code = IndustrySMS.execute(tel);
			state = 1;
		}
		catch (Exception e) {
			state = 0;
		}
		HttpSession session = request.getSession();
		session.setAttribute("tel", tel);
		session.setAttribute("code", "123456");
		return "{\"state\": " + state + "}";
	}
	
	
//   发送验证码到手机，由于次数有限所以用上面的模拟替代方法，验证码为123456
//	@RequestMapping("/sendMobileCode")
//	@ResponseBody
//	public String sendMobileCode(HttpServletRequest request, String tel) throws Exception{
//		System.out.println("....UserHandler.....sendMobileCode.......");
//		int state = 0;
//		String code = "";
//		try {
//			code = IndustrySMS.execute(tel);
//			state = 1;
//		}
//		catch (Exception e) {
//			state = 0;
//		}
//		HttpSession session = request.getSession();
//		session.setAttribute("tel", tel);
//		session.setAttribute("code", code);
//		return "{\"state\": " + state + "}";
//	}
	
	@RequestMapping("/checkMobileCode")
	@ResponseBody
	public String checkMobileCode(HttpServletRequest request, int qid, String tel, String code) throws Exception{
		System.out.println("....UserHandler.....checkMobileCode.......");
		int state = 0;
		HttpSession session = request.getSession();
		String trueTel = (String) session.getAttribute("tel");
		String trueCode = (String) session.getAttribute("code");
		System.out.println("trueTel=" + trueTel + "; trueCode=" + trueCode);
		System.out.println(session);
		if (tel.equals(trueTel) && code.equals(trueCode)) {
			System.out.println("验证通过");
			User user = userService.selectLoginInfoByQidAndTel(qid, tel);
			session.setAttribute("user", user);
			if (user.getUsername() == null || user.getUsername().equals("")) {
				state = 1;
			}
			else {
				state = 2;
			}
		}
		return "{\"state\": " + state + "}";
	}
	
	@RequestMapping("/getUser")
	@ResponseBody
	public User getUser(HttpServletRequest request) throws Exception{
		System.out.println("....UserHandler.....getUser.......");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			user = new User();
			user.setUid(-1);
		}
		return user;
	}
	
	@RequestMapping("/updateUser")
	@ResponseBody
	public String updateUser(HttpServletRequest request, String tel, String nickname, String username) throws Exception{
		System.out.println("....UserHandler.....updateUser.......");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		user.setTel(tel);
		user.setNickname(nickname);
		user.setUsername(username);
		userService.updateUser(user);
		return "{\"state\": 1}";
 
	}
	
}
