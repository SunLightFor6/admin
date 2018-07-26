package com.lamport.education.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.lamport.education.po.User;
import com.lamport.education.service.UserService;
import com.lamport.education.util.IndustrySMS;
import com.lamport.education.vo.QIDAndTel;

/**
 * Controller, 进行user的登录和退出
 * @author Lin Zhao, protector of Sherry
 *
 */
public class LogInAndOutHandler {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/sendMobileCode")
	@ResponseBody
	public String sendMobileCode(HttpServletRequest request, String tel) throws Exception{
		System.out.println("..........LogInAndOutHandler..........sendMobileCode()..........");
		String result = null;
		
		int state = 0;
		String code = "";
		try {
			code = IndustrySMS.execute(tel);
			state = 1;
		}
		catch (Exception e) {
			state = 0;
		}
		HttpSession session = request.getSession();
		session.setAttribute("tel", tel);
		session.setAttribute("code", code);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("state", state);
		result = jsonObject.toString();
		
		return result;
	}
//  发送验证码到手机，由于次数有限所以用上面的模拟替代方法，验证码为123456
//	@RequestMapping("/sendMobileCode")
//	@ResponseBody
//	public String sendMobileCode(HttpServletRequest request, String tel) throws Exception{
//		System.out.println("..........UserHandler..........sendMobileCode..........");
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
		System.out.println("..........UserHandler..........checkMobileCode..........");
		String result = null;
		
		int state = 0;
		HttpSession session = request.getSession();
		String trueTel = (String) session.getAttribute("tel");
		String trueCode = (String) session.getAttribute("code");
		System.out.println("trueTel=" + trueTel + "; trueCode=" + trueCode);/*####################*/
		System.out.println(session);										/*####################*/
		if (tel.equals(trueTel) && code.equals(trueCode)) {
			System.out.println("验证通过");									/*####################*/
			QIDAndTel qidAndTel = new QIDAndTel();
			qidAndTel.setQid(qid);
			qidAndTel.setTel(tel);
			User user = userService.selectUserByQidAndTel(qidAndTel);
			session.setAttribute("user", user);
			if (user.getUsername() == null || user.getUsername().equals("")) {
				state = 1;
			}
			else {
				state = 2;
			}
		}
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("state", state);
		result = jsonObject.toString();
		
		return result;
	}
}
