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
	 * 判断是否有用户登陆(admin/superAdmin)
	 * @return
	 */
	@RequestMapping(value="/login/isAnyoneLogin")
	@ResponseBody
	public String isAnyoneLogin() throws Exception{
		System.out.println("..........LoginHandler..........isAnyoneLogin()..........");
		String result = null;
		
		//既然interceptor放行，说明没有用户登录
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("response", 0);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 进行admin的登录
	 * @return String
	 */
	@RequestMapping(value="/login/adminLogin")
	@ResponseBody
	public String adminLogin(Admin admin, HttpServletRequest request) throws Exception{
		System.out.println("..........LoginHandler..........adminLogin()..........admin:" + admin.getAdminname());
		String result = null;
		
		JsonObject jsonObject = new JsonObject();
		int loginResult = loginService.isAdminLoginSuccessful(admin);
		switch(loginResult){
		case -2:
			jsonObject.addProperty("status", "fail");
			jsonObject.addProperty("message", "您没有此权限");
			break;
		case -1:
			jsonObject.addProperty("status", "fail");
			jsonObject.addProperty("message", "该账号不存在");
			break;
		case 0:
			jsonObject.addProperty("status", "fail");
			jsonObject.addProperty("message", "密码错误");
			break;
		case 1:
			jsonObject.addProperty("status", "success");
			jsonObject.addProperty("message", "登录成功");
			admin = adminService.selectAdminByAdminname(admin.getAdminname());
			HttpSession session = request.getSession();
			session.setAttribute("admin", admin);
			break;
		default:
			jsonObject.addProperty("status", "fail");
			jsonObject.addProperty("message", "登录失败，请稍后重试");
			break;
		}
		result=jsonObject.toString();
		System.out.println("---------------------------------------------\n" + result);
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
		System.out.println("..........LoginHandler..........superAdminLogin()..........superAdmin = " + superAdmin.getAdminname());
		String result = null;
		
		JsonObject jsonObject = new JsonObject();
		int loginResult = loginService.isSuperAdminLoginSuccessful(superAdmin);
		switch(loginResult){
		case -2:
			jsonObject.addProperty("status", "fail");
			jsonObject.addProperty("message", "您没有此权限");
			break;
		case -1:
			jsonObject.addProperty("status", "fail");
			jsonObject.addProperty("message", "该账号不存在");
			break;
		case 0:
			jsonObject.addProperty("status", "fail");
			jsonObject.addProperty("message", "密码错误");
			break;
		case 1:
			jsonObject.addProperty("status", "success");
			jsonObject.addProperty("message", "登陆成功");
			superAdmin = adminService.selectAdminByAdminname(superAdmin.getAdminname());
			HttpSession session = request.getSession();
			session.setAttribute("superAdmin", superAdmin);
			break;
		default:
			jsonObject.addProperty("status", "fail");
			jsonObject.addProperty("message", "登陆失败，请稍后重试");
			break;
		}
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
