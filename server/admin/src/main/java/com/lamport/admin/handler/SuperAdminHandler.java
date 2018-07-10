package com.lamport.admin.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lamport.admin.po.Enterprise;
import com.lamport.admin.service.EnterpriseService;
import com.lamport.admin.tool.PageTool;

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
		
		Enterprise enterprise = new Enterprise();
		int saveResult = enterpriseService.saveEnterprise(enterprise);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("response", saveResult);
		result = jsonObject.toString();
		
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
	public String selectEnterpriseByPage(PageTool pageTool) throws Exception{
		System.out.println("..........SuperAdminHandler..........selectEnterpriseByPage()..........");
		String result = null;
		
		List<Enterprise> enterprises = enterpriseService.selectEnterpriseByPage(pageTool);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("code", 0);
		jsonObject.addProperty("msg", "");
		jsonObject.addProperty("count", pageTool.getCount());
		JsonArray jsonArray = new JsonArray();
		for(Enterprise enterprise : enterprises){
			JsonObject object = new JsonObject();
			object.addProperty("id", enterprise.getQid());
			object.addProperty("admin", enterprise.getAdminister().getAdminname());
			object.addProperty("enterprise", enterprise.getName());
			jsonArray.add(object);
		}
		jsonObject.add("data", jsonArray);
		result = jsonObject.toString();
		
		return result;
	}
}
