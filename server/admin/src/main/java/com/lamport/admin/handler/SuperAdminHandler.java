package com.lamport.admin.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller, 进行Enterprise的增加、删除、查询
 * @author Lin Zhao, protector of Sherry
 *
 */
@Controller
public class SuperAdminHandler {
	
	/**
	 * 通过页码查询企业信息
	 * @return String
	 */
	@RequestMapping(value="/superadmin/selectEnterpriseByPage")
	public String selectEnterpriseByPage(){
		System.out.println("..........SuperAdminHandler..........selectEnterpriseByPage()..........");
		//TODO
		return "";
	}
	/**
	 * 通过企业id删除企业
	 * @return String
	 */
	@RequestMapping(value="/superadmin/deleteEnterpriseByID")
	public String deleteEnterpriseByID(){
		System.out.println("..........SuperAdminHandler..........deleteEnterpriseByID()..........");
		//TODO
		return "";
	}
	/**
	 * 增加企业
	 * @return String
	 */
	@RequestMapping(value="/superadmin/saveEnterprise")
	public String saveEnterprise(){
		System.out.println("..........SuperAdminHandler..........saveEnterprise()..........");
		//TODO
		return "";
	}
}
