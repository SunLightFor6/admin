package com.lamport.admin.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lamport.admin.po.Admin;
import com.lamport.admin.po.Enterprise;
import com.lamport.admin.po.Swiper;
import com.lamport.admin.service.EnterpriseService;

/**
 * Controller, 进行Enterprise基本信息的修改、查询
 * @author Lin Zhao, protector of Sherry
 *
 */
@Controller
public class EnterpriseBasicInfoHandler {
	
	@Autowired
	private EnterpriseService enterpriseService;
	
	/**
	 * 根据id修改企业基本信息 
	 * @return String
	 */
	@RequestMapping(value="/admin/updateEnterpriseBasicInfoByID")
	@ResponseBody
	public String updateEnterpriseBasicInfoByID(Enterprise enterprise, MultipartFile[] message_pics, MultipartFile video, HttpServletRequest request) throws Exception{
		System.out.println("..........EnterpriseBasicInfoHandler..........updateEnterpriseBasicInfoByID()..........");
		String result = null;

		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		enterprise.setQid(admin.getQid());
		String path = request.getServletContext().getRealPath("/");//得到当前工程的根路径
		int updateResult = enterpriseService.updateEnterpriseByID(enterprise, message_pics, video, path);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("reponse", updateResult);
		result = jsonObject.toString();
		
		return result;
	}
	/**
	 * 根据id查询企业基本信息 
	 * @return String
	 */
	@RequestMapping(value="/admin/selectEnterpriseBasicInfoByQID")
	@ResponseBody
	public String selectEnterpriseBasicInfoByQID(HttpServletRequest request) throws Exception{
		System.out.println("..........EnterpriseBasicInfoHandler..........selectEnterpriseBasicInfoByQID()..........");
		String result = null;

		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		Enterprise enterprie = enterpriseService.selectEnterpriseByQID(admin.getQid());
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("name", enterprie.getName());
		jsonObject.addProperty("videopath", enterprie.getVideopath());
		jsonObject.addProperty("introduction", enterprie.getIntroduction());
		jsonObject.addProperty("jczs", enterprie.getJczs());
		JsonArray jsonArray = new JsonArray();
		for(Swiper swiper : enterprie.getSwipers()){
			jsonArray.add(swiper.getImgurl());
		}
		jsonObject.add("imgs", jsonArray);
		result = jsonObject.toString();
		
		return result;
	}
}
