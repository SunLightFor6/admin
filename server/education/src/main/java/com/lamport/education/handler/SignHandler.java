package com.lamport.education.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.lamport.education.po.Sign;
import com.lamport.education.po.User;
import com.lamport.education.service.SignService;

/**
 * Controller, 进行Sign(签到)信息的增加
 * @author Lin Zhao, protector of Sherry
 *
 */
@Controller
public class SignHandler {
	
	@Autowired
	SignService signService;

	/**
	 * 用户签到
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/signIn")
	@ResponseBody
	public String signIn(HttpServletRequest request) throws Exception{
		System.out.println("..........CouponHanler..........signIn..........");
		String result = null;
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		Sign sign = new Sign();
		sign.setUid(user.getUid());
		int saveResult = signService.saveSign(sign, user);
		if(saveResult == 1){
			//签到成功，更新session中的user对象
			user.setTotalpoint(user.getTotalpoint()+sign.getPoint());
			session.setAttribute("user", user);
		}
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("state", saveResult);
		jsonObject.addProperty("days", sign.getDays());
		jsonObject.addProperty("point", sign.getPoint());
		result = jsonObject.toString();
		//TODO
		
		return result;
	}
}
