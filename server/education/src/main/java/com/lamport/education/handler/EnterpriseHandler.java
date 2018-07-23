package com.lamport.education.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lamport.education.po.Enterprise;
import com.lamport.education.service.EnterpriseService;

@Controller
public class EnterpriseHandler {
	@Autowired
	EnterpriseService enterpriseService;
	/**
	 * @param request 发来的请求，获取qid
	 * @return  轮播图的地址字符串
	 * 调用service，传入qid获取图片字符串数组
	 * @throws Exception 
	 */
	@RequestMapping("/selectHomePageSwiperByQid")
	@ResponseBody
	public  List<String> selectHomePageSwiperByQid(HttpServletRequest request,HttpServletResponse response) throws Exception{
		System.out.println("....EnterpriseHandler......selectHomePageSwiperByQid......");
		int qid = Integer.parseInt(request.getParameter("qid"));
		HttpSession session = request.getSession();	
		//int qid = Integer.parseInt((String)session.getAttribute("qid"));
		session.setAttribute("qid", qid);
		System.out.println("qid="+ qid);
		return enterpriseService.selectEnterpriseSwiperByQid(qid);
	} 
	/**
	 * @param request
	 * @return  返回装有视频地址，机构简介，精彩展示的企业对象
	 * @throws Exception 
	 */
	@RequestMapping("/selectEnterPriseByQid")
	@ResponseBody
	public Enterprise selectEnterPriseByQid(HttpServletRequest request) throws Exception{
		System.out.println("....EnterpriseHandler.......getEnterPrise.......");
		HttpSession session = request.getSession();	
		int qid = (Integer)session.getAttribute("qid");
		Enterprise enterprise = enterpriseService.selectEnterpriseByQid(qid);
		return enterprise;
	}
}
