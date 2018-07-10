package com.lamport.admin.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.lamport.admin.po.Admin;
import com.lamport.admin.service.AdminService;
import com.lamport.admin.service.LoginService;

/**
 * Controller, 进行admin和superAdmin的登录和退出
 * @author Lin Zhao, protector of Sherry
 *
 */
@Controller
public class LogInAndOutHandler {
	
	@Autowired
	private LoginService loginService;
	@Autowired
	private AdminService adminService;
	
	/**
	 * 进行admin的登录
	 * @return String
	 */
	@RequestMapping(value="/login/adminLogin")
	@ResponseBody
	public String adminLogin(Admin admin, HttpServletRequest request) throws Exception{
		System.out.println("..........LoginHandler..........adminLogin()..........");
		String result = null;
		
		JsonObject jsonObject = new JsonObject();
		int isAdminLoginSuccessful = loginService.isAdminLoginSuccessful(admin);
		if(isAdminLoginSuccessful == 1){
			admin = adminService.selectAdminByAdminname(admin.getAdminname());
			HttpSession session = request.getSession();
			session.setAttribute("admin", admin);
		}
		jsonObject.addProperty("response", isAdminLoginSuccessful);
		result=jsonObject.toString();
		
		return result;
	}
	/**
	 * 进行admin的退出
	 * @return String
	 */
	@RequestMapping(value="/login/adminLogout")
	@ResponseBody
	public String adminLogout(HttpServletRequest request) throws Exception{
		System.out.println("..........LoginHandler..........adminLogout()..........");
		String result = null;
		
		JsonObject jsonObject = new JsonObject();
		HttpSession session = request.getSession();
		session.removeAttribute("admin");
		jsonObject.addProperty("response", 1);
		result=jsonObject.toString();
		
		return result;
	}
	/**
	 * 进行superAdmin的登录
	 * @return String
	 */
	@RequestMapping(value="/login/superAdminLogin")
	@ResponseBody
	public String superAdminLogin(Admin superAdmin, HttpServletRequest request) throws Exception{
		System.out.println("..........LoginHandler..........superAdminLogin()..........");
		String result = null;

		JsonObject jsonObject = new JsonObject();
		int isAdminLoginSuccessful = loginService.isAdminLoginSuccessful(superAdmin);
		if(isAdminLoginSuccessful == 1){
			superAdmin = adminService.selectAdminByAdminname(superAdmin.getAdminname());
			HttpSession session = request.getSession();
			session.setAttribute("superAdmin", superAdmin);
		}
		jsonObject.addProperty("response", isAdminLoginSuccessful);
		result=jsonObject.toString();
		
		return result;
	}
	/**
	 * 进行superAdmin的退出
	 * @return String
	 */
	@RequestMapping(value="/login/superAdminLogout")
	@ResponseBody
	public String superAdminLogout(HttpServletRequest request) throws Exception{
		System.out.println("..........LoginHandler..........superAdminLogout()..........");
		String result = null;
		
		JsonObject jsonObject = new JsonObject();
		HttpSession session = request.getSession();
		session.removeAttribute("superAdmin");
		jsonObject.addProperty("response", 1);
		result=jsonObject.toString();
		
		return result;
	}
}
