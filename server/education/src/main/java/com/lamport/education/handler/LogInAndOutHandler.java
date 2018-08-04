package com.lamport.education.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@Controller
public class LogInAndOutHandler {
	
	@Autowired
	UserService userService;
	
	/**
	 * 发送手机验证码
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/sendMobileCode")
	@ResponseBody
	public String sendMobileCode(String tel, HttpServletRequest request) throws Exception{
		System.out.println("..........LogInAndOutHandler..........sendMobileCode()..........");
		String result = null;
		
		String code = "";
		code = IndustrySMS.execute(tel);
		HttpSession session = request.getSession();
		//往session中放入tel和code, user登录成功时应移除
		session.setAttribute("tel", tel);
		session.setAttribute("code", code);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("state", 1);
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
	
	/**
	 * 验证手机验证码
	 * 0 失败， 1 首次登录， 2 非首次登录
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/checkMobileCode")
	@ResponseBody
	public String checkMobileCode(String tel, String code, HttpServletRequest request) throws Exception{
		System.out.println("..........UserHandler..........checkMobileCode..........");
		String result = null;
		
		int state = 0;
		HttpSession session = request.getSession();
		int qid = (Integer)session.getAttribute("qid");
		String trueTel = (String) session.getAttribute("tel");
		String trueCode = (String) session.getAttribute("code");
		System.out.println("trueTel = " + trueTel + "\t trueCode = " + trueCode);/*####################*/
		System.out.println(session);										/*####################*/
		if (tel.equals(trueTel) && code.equals(trueCode)) {
			System.out.println("验证通过");									/*####################*/
			QIDAndTel qidAndTel = new QIDAndTel();
			qidAndTel.setQid(qid);
			qidAndTel.setTel(tel);
			/*
			 * 如果user为null, 则新建一个user对象
			 * 如果user的userName为null或userName="", 则返回 1
			 */
			User user = userService.selectUserByQidAndTel(qidAndTel);//待修改
			session.setAttribute("user", user);
			//user登录成功，应当移除tel, code信息
			session.removeAttribute("tel");
			session.removeAttribute("code");
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
	
	/**
	 * 进行user的退出
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="userLogout")
	@ResponseBody
	public String userLogout(HttpServletRequest request) throws Exception{
		System.out.println("..........UserHandler..........userLogout..........");
		String result = null;
		
		HttpSession session  = request.getSession();
		//user退出登录，应当移除它的user信息
		session.removeAttribute("user");
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("state", 1);
		result = jsonObject.toString();
		
		return result;
	}
}
