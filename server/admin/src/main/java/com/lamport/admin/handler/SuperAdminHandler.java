package com.lamport.admin.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lamport.admin.po.Enterprise;
import com.lamport.admin.service.EnterpriseService;

/**
 * Controller, 进行Enterprise的增加、删除、查询
 * @author Lin Zhao, protector of Sherry
 *
 */
@Controller
public class SuperAdminHandler {
	
	@Autowired
	private EnterpriseService enterpriseService;
	
	/**
	 * 创建企业
	 * @return String
	 */
	@RequestMapping(value="/superadmin/saveEnterprise")
	@ResponseBody
	public String saveEnterprise() throws Exception{
		System.out.println("..........SuperAdminHandler..........saveEnterprise()..........");
		String result = null;
		//TODO
		return result;
	}
	/**
	 * 通过id逻辑删除企业
	 * @return String
	 */
	@RequestMapping(value="/superadmin/deleteEnterpriseLogicallyByID")
	@ResponseBody
	public String deleteEnterpriseLogicallyByID() throws Exception{
		System.out.println("..........SuperAdminHandler..........deleteEnterpriseLogicallyByID()..........");
		String result = null;
		//TODO
		return result;
	}
	/**
	 * 通过页码查询企业信息
	 * @return String
	 */
	@RequestMapping(value="/superadmin/selectEnterpriseByPage")
	@ResponseBody
	public String selectEnterpriseByPage() throws Exception{
		System.out.println("..........SuperAdminHandler..........selectEnterpriseByPage()..........");
		String result = null;
		//TODO
		return result;
	}
}
