package com.lamport.education.handler;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lamport.education.po.Enterprise;
import com.lamport.education.po.FreeListen;
import com.lamport.education.po.Lesson;
import com.lamport.education.service.EnterpriseService;
import com.lamport.education.vo.BranchCategoryVo;
import com.lamport.education.vo.EnterpriseCategoryVo;

@Controller
public class EnterpriseHandler {
	
	@Autowired
	EnterpriseService enterpriseService;

	/**
	 * 通过qid查询企业信息
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/selectEnterPriseByQid")
	@ResponseBody
	public String selectEnterpriseByQid(HttpServletRequest request) throws Exception{
		System.out.println("..........EnterpriseHandler..........selectEnterPriseByQid..........");
		String result = null;
		
		HttpSession session = request.getSession();	
		int qid = (Integer)session.getAttribute("qid");
		Enterprise enterprise = enterpriseService.selectEnterpriseByQid(qid);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("name", enterprise.getName());
		jsonObject.addProperty("videopath", enterprise.getVideopath());
		jsonObject.addProperty("introduction", enterprise.getIntroduction());
		jsonObject.addProperty("jczs", enterprise.getJczs());
		result = jsonObject.toString();
		
		return result;
	}
	
	@RequestMapping("/selectAllBranchCategoryByQid")
	@ResponseBody
	public String selectAllBranchCategoryByQid(HttpServletRequest request) throws Exception{
		System.out.println("..........EnterpriseHandler..........selectAllBranchCategoryByQid..........");
		String result = null;
		HttpSession session = request.getSession();	
		int qid = (Integer)session.getAttribute("qid");
		EnterpriseCategoryVo enterpriseCategoryVo = enterpriseService.selectAllBranchCategoryByQid(qid);
		List<BranchCategoryVo> branchCategoryVos = enterpriseCategoryVo.getBranchCategoryVo();
		JsonArray jsonArray = new JsonArray(); //	
		for(BranchCategoryVo branchCategoryVo:branchCategoryVos){
			JsonObject branchObj = new JsonObject(); // 添加分支
			branchObj.addProperty("text", branchCategoryVo.getBranch());	
			JsonArray freeOrLessonArray = new JsonArray(); // 试听 付费 数组		
			JsonObject lessonObj = new JsonObject(); // 精品课对象
			lessonObj.addProperty("text", "精品课");
			JsonArray lessonArray = new JsonArray(); // 精品课数组
			JsonObject freeListenObj = new JsonObject(); // 免费课对象
			freeListenObj.addProperty("text", "试听课");
			JsonArray freeListenArray = new JsonArray(); // 免费课数组
			for(FreeListen freeListen:branchCategoryVo.getFreeListenCategorys()){
				JsonObject freeListenCategoryObj = new JsonObject(); // 添加方向
				freeListenCategoryObj.addProperty("text", freeListen.getCategory());
				freeListenArray.add(freeListenCategoryObj);
			}
			for(Lesson lesson:branchCategoryVo.getLessonCategorys()){
				JsonObject lessonCategoryObj = new JsonObject(); // 添加方向
				lessonCategoryObj.addProperty("text", lesson.getCategory());
				lessonArray.add(lessonCategoryObj);
			}
			lessonObj.add("children", lessonArray);
			freeListenObj.add("children", freeListenArray);
			freeOrLessonArray.add(lessonObj);
			freeOrLessonArray.add(freeListenObj);
			branchObj.add("children", freeOrLessonArray);
			jsonArray.add(branchObj);
		}	
		result = jsonArray.toString();
		return result;
	}
	
	
	
	
	
//	/**
//	 * @param qid 企业Id
//	 * @return  轮播图的地址字符串
//	 * 将企业的Id放入到session中去，然后调用service层获轮播图字符串
//	 * @throws Exception 
//	 */
//	@RequestMapping("/selectHomePageSwiperByQid")
//	@ResponseBody
//	public  List<String> selectHomePageSwiperByQid(HttpServletRequest request,HttpServletResponse response) throws Exception{
//		System.out.println("....EnterpriseHandler......selectHomePageSwiperByQid......");
//		int qid = Integer.parseInt(request.getParameter("qid"));
//		
//		HttpSession session = request.getSession();	
//		session.setAttribute("qid", qid);
//		System.out.println("qid="+ qid);
//		
//		return enterpriseService.selectEnterpriseSwipersByQid(qid);
//	} 
}
