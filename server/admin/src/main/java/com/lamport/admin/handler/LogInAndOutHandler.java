package com.lamport.admin.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller, 进行admin和superAdmin的登录
 * @author Lin Zhao, protector of Sherry
 *
 */
@Controller
public class LogInAndOutHandler {
	/**
	 * 进行admin的登录
	 * @return String
	 */
	@RequestMapping(value="/login/adminLogin")
	public String adminLogin(){
		System.out.println("..........LoginHandler..........adminLogin()..........");
		//TODO
		return"";
	}
	/**
	 * 进行admin的退出
	 * @return String
	 */
	@RequestMapping(value="/login/adminLogout")
	public String adminLogout(){
		System.out.println("..........LoginHandler..........adminLogout()..........");
		//TODO
		return"";
	}
	/**
	 * 进行superAdmin的登录
	 * @return String
	 */
	@RequestMapping(value="/login/superAdminLogin")
	public String superAdminLogin(){
		System.out.println("..........LoginHandler..........superAdminLogin()..........");
		//TODO
		return"";
	}
	/**
	 * 进行superAdmin的退出
	 * @return String
	 */
	@RequestMapping(value="/login/superAdminLogout")
	public String superAdminLogout(){
		System.out.println("..........LoginHandler..........superAdminLogout()..........");
		//TODO
		return"";
	}
}
