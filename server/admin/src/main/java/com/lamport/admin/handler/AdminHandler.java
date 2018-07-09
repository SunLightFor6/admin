package com.lamport.admin.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lamport.admin.service.AdminService;
import com.lamport.admin.service.EnterpriseService;

/**
 * Controller, 进行Admin的修改、查询
 * @author Lin Zhao, protector of Sherry
 *
 */
@Controller
public class AdminHandler {
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private EnterpriseService enterpriseService;
	
	/**
	 * 通过id修改Admin的password
	 * @return
	 */
	@RequestMapping(value="/admin/updatePasswordByID")
	@ResponseBody
	public String updatePasswordByID() throws Exception{
		System.out.println("..........AdminHandler..........updatePasswordByID()..........");
		String result = null;
		//TODO
		return result;
	}
	/**
	 * 通过qid查询Enterprise的企业名称和企业编号
	 * @return
	 */
	@RequestMapping(value="/admin/selectEnterpriseBasicDataByQID")
	@ResponseBody
	public String selectEnterpriseBasicDataByQID() throws Exception{
		System.out.println("..........AdminHandler..........selectEnterpriseBasicDataByQID()..........");
		String result = null;
		//TODO
		return result;
	}
}
