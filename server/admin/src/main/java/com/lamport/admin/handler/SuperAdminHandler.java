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
	 * 增加企业
	 * @return String
	 */
	@RequestMapping(value="/superadmin/saveEnterprise")
	public String saveEnterprise(){
		System.out.println("..........SuperAdminHandler..........saveEnterprise()..........");
		//TODO
		return "";
	}
	/**
	 * 通过id逻辑删除企业
	 * @return String
	 */
	@RequestMapping(value="/superadmin/deleteEnterpriseLogicallyByID")
	public String deleteEnterpriseLogicallyByID(){
		System.out.println("..........SuperAdminHandler..........deleteEnterpriseLogicallyByID()..........");
		//TODO
		return "";
	}
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
}
