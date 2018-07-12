package com.lamport.admin.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.lamport.admin.po.Admin;
import com.lamport.admin.po.Enterprise;
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
	public String updatePasswordByID(String sourse_password, String new_password, HttpServletRequest request) throws Exception{
		System.out.println("..........AdminHandler..........updatePasswordByID()..........");
		String result = null;

		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		JsonObject jsonObject = new JsonObject();
		if(admin.getPassword().equals(sourse_password)){
			admin.setPassword(new_password);
			int updateResult = adminService.updatePasswordByID(admin);
			if(updateResult == 1){
				session.setAttribute("admin", admin);
				jsonObject.addProperty("status", "success");
				jsonObject.addProperty("message", "密码修改成功");
			}else{
				jsonObject.addProperty("status", "fail");
				jsonObject.addProperty("message", "密码修改失败");
			}
		}else{
			jsonObject.addProperty("status", "fail");
			jsonObject.addProperty("message", "原密码输入错误");
		}
		result = jsonObject.toString();
		
		return result;
	}
	/**
	 * 通过qid查询Enterprise的企业名称和企业编号
	 * @return
	 */
	@RequestMapping(value="/admin/selectEnterpriseBasicDataByQID")
	@ResponseBody
	public String selectEnterpriseBasicDataByQID(HttpServletRequest request) throws Exception{
		System.out.println("..........AdminHandler..........selectEnterpriseBasicDataByQID()..........");
		String result = null;

		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		Enterprise enterprise = enterpriseService.selectEnterpriseByQID(admin.getQid());
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("enterprise_name", enterprise.getName());
		jsonObject.addProperty("enterprise_id", enterprise.getQid());
		result = jsonObject.toString();
		
		return result;
	}
}
