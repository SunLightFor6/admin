package com.lamport.admin.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller, 进行Enterprise基本信息的查询、修改
 * @author Lin Zhao, protector of Sherry
 *
 */
@Controller
public class EnterpriseBasicInfoHandler {
	
	/**
	 * 根据qid查询企业基本信息 
	 * @return String
	 */
	@RequestMapping(value="/admin/selectEnterpriseBasicInfoByQID")
	public String selectEnterpriseBasicInfoByQID(){
		System.out.println("..........EnterpriseBasicInfoHandler..........selectEnterpriseBasicInfoByQID()..........");
		//TODO
		return"";
	}
	/**
	 * 根据qid更新企业基本信息 
	 * @return String
	 */
	@RequestMapping(value="/admin/updateEnterpriseBasicInfoByQID")
	public String updateEnterpriseBasicInfoByQID(){
		System.out.println("..........EnterpriseBasicInfoHandler..........updateEnterpriseBasicInfoByQID()..........");
		//TODO
		return"";
	}
}
