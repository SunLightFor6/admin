package com.lamport.admin.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
		
		int isAdminLoginSuccessful = loginService.isAdminLoginSuccessful(admin);
		if(isAdminLoginSuccessful == 1){
			admin = adminService.selectAdminByAdminname(admin.getAdminname());
			HttpSession session = request.getSession();
			session.setAttribute("admin", admin);
			result = //TODO;
		}else{
			//TODO
		}
		
		return result;
	}
	/**
	 * 进行admin的退出
	 * @return String
	 */
	@RequestMapping(value="/login/adminLogout")
	@ResponseBody
	public String adminLogout() throws Exception{
		System.out.println("..........LoginHandler..........adminLogout()..........");
		String result = null;
		//TODO
		return result;
	}
	/**
	 * 进行superAdmin的登录
	 * @return String
	 */
	@RequestMapping(value="/login/superAdminLogin")
	@ResponseBody
	public String superAdminLogin() throws Exception{
		System.out.println("..........LoginHandler..........superAdminLogin()..........");
		String result = null;
		//TODO
		return result;
	}
	/**
	 * 进行superAdmin的退出
	 * @return String
	 */
	@RequestMapping(value="/login/superAdminLogout")
	@ResponseBody
	public String superAdminLogout() throws Exception{
		System.out.println("..........LoginHandler..........superAdminLogout()..........");
		String result = null;
		//TODO
		return result;
	}
}
