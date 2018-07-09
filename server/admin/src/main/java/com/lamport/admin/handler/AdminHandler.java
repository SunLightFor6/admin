package com.lamport.admin.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller, 进行Admin的修改、查询查询
 * @author Lin Zhao, protector of Sherry
 *
 */
@Controller
public class AdminHandler {
	/**
	 * 通过id修改Admin的password
	 * @return
	 */
	@RequestMapping(value="/admin/updatePasswordByID")
	public String updatePasswordByID(){
		System.out.println("..........AdminHandler..........updatePasswordByID()..........");
		//TODO
		return "";
	}
	/**
	 * 通过qid查询Enterprise的企业名称和企业编号
	 * @return
	 */
	@RequestMapping(value="/admin/selectEnterpriseBasicDataByQID")
	public String selectEnterpriseBasicDataByQID(){
		System.out.println("..........AdminHandler..........selectEnterpriseBasicDataByQID()..........");
		//TODO
		return "";
	}
}
