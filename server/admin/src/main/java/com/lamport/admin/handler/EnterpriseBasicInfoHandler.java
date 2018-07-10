package com.lamport.admin.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller, 进行Enterprise基本信息的修改、查询
 * @author Lin Zhao, protector of Sherry
 *	
 */
@Controller
public class EnterpriseBasicInfoHandler {
	/**
	 * 根据id修改企业基本信息 
	 * @return String
	 */
	@RequestMapping(value="/admin/updateEnterpriseBasicInfoByID")
	@ResponseBody
	public String updateEnterpriseBasicInfoByID() throws Exception{
		System.out.println("..........EnterpriseBasicInfoHandler..........updateEnterpriseBasicInfoByID()..........");
		String result = null;
		//TODO
		return result;
	}
	/**
	 * 根据id查询企业基本信息 
	 * @return String
	 */
	@RequestMapping(value="/admin/selectEnterpriseBasicInfoByQID")
	@ResponseBody
	public String selectEnterpriseBasicInfoByQID() throws Exception{
		System.out.println("..........EnterpriseBasicInfoHandler..........selectEnterpriseBasicInfoByQID()..........");
		String result = null;
		//TODO
		return result;
	}
}
