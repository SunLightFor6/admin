package com.lamport.education.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lamport.education.po.Enterprise;
import com.lamport.education.service.EnterpriseService;

@Controller
public class EnterpriseHandler {
	
	@Autowired
	EnterpriseService enterpriseService;
	/**
	 * @param qid 企业Id
	 * @return  轮播图的地址字符串
	 * 将企业的Id放入到session中去，然后调用service层获轮播图字符串
	 * @throws Exception 
	 */
	@RequestMapping("/selectHomePageSwiperByQid")
	@ResponseBody
	public  List<String> selectHomePageSwiperByQid(HttpServletRequest request,HttpServletResponse response) throws Exception{
		System.out.println("....EnterpriseHandler......selectHomePageSwiperByQid......");
		int qid = Integer.parseInt(request.getParameter("qid"));
		HttpSession session = request.getSession();	
		session.setAttribute("qid", qid);
		System.out.println("qid="+ qid);
		return enterpriseService.selectEnterpriseSwipersByQid(qid);
	} 
	/**
	 * @param request
	 * @return  返回装有视频地址，机构简介，精彩展示的企业对象
	 * @throws Exception 
	 */
	@RequestMapping("/selectEnterPriseByQid")
	@ResponseBody
	public Enterprise selectEnterpriseByQid(HttpServletRequest request) throws Exception{
		System.out.println("....EnterpriseHandler.......getEnterPrise.......");
		HttpSession session = request.getSession();	
		int qid = (Integer)session.getAttribute("qid");
		Enterprise enterprise = enterpriseService.selectEnterpriseByQid(qid);
		return enterprise;
	}

}
