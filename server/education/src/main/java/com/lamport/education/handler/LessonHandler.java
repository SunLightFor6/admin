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
import com.lamport.education.po.Address;
import com.lamport.education.po.Enterprise;
import com.lamport.education.po.Lesson;
import com.lamport.education.po.User;
import com.lamport.education.service.EnterpriseService;
import com.lamport.education.service.LessonService;
import com.lamport.education.util.Config;
import com.lamport.education.vo.LessonQueryCondition;

@Controller
public class LessonHandler {
	
	@Autowired
	LessonService lessonService;
	@Autowired
	EnterpriseService enterpriseService;
	
	/**
	 * 通过lid查询Lesson信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectLessonByLid")
	@ResponseBody
	public String selectLessonByLid(int lid, HttpServletRequest request) throws Exception{
		System.out.println("..........LessionHandler..........selectLessonByLid..........");
		String result = null;
		
		Lesson lesson = lessonService.selectLessonByLid(lid);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		jsonObject.addProperty("lid", lesson.getLid());
		jsonObject.addProperty("lname", lesson.getLname());
		jsonObject.addProperty("imgurl", lesson.getImgurl());
		jsonObject.addProperty("lprice", lesson.getLprice());
		jsonObject.addProperty("status", lesson.getStatus());
		jsonObject.addProperty("category", lesson.getCategory());
		jsonObject.addProperty("ldesc", lesson.getLdesc());
		if(lesson.getBranches()!=null && !lesson.getBranches().isEmpty()){
			for(Address address : lesson.getBranches()){
				JsonObject object = new JsonObject();
				object.addProperty("branch", address.getBranch());
				jsonArray.add(object);
			}
		}else{
			System.out.println("lesson..getBranches() is null or lesson..getBranches().size is 0");
		}
		jsonObject.add("branches", jsonArray);
		result = jsonObject.toString();

		return result;
	}
	

	
	/**
	 * 通过多条件查询Lesson信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectLessonByLessonQueryCondition")
	@ResponseBody
	public String selectLessonByLessonQueryCondition(LessonQueryCondition lessonQueryCondition, HttpServletRequest request) throws Exception {
		System.out.println("..........LessionHandler..........selectLessonByLessonQueryCondition..........");
		String result = null;
		
		HttpSession session = request.getSession();
		int qid = (Integer)session.getAttribute("qid");
		lessonQueryCondition.setQid(qid);
		lessonQueryCondition.initPageBean(Config.LessonPageSize);
		List<Lesson> lessons = lessonService.selectLessonByLessonQueryCondition(lessonQueryCondition);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		if(lessons!=null && !lessons.isEmpty()){
			for(Lesson lesson : lessons){
				JsonObject object = new JsonObject();
				object.addProperty("lid", lesson.getLid());
				object.addProperty("imgurl", lesson.getImgurl());
				object.addProperty("lname", lesson.getLname());
				object.addProperty("ldesc", lesson.getLdesc());
				jsonArray.add(object);
			}
		}
		jsonObject.add("lessons", jsonArray);
		result = jsonObject.toString();

		return result;
	}
	
	/**
	 * 通过lid查询支付信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selectPayInfoByLID")
	@ResponseBody
	public String selectPayInfoByLID(int lid, HttpServletRequest request) throws Exception{
		System.out.println("..........LessionHandler..........selectPayInfoByLID..........");
		String result = null;
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		Lesson lesson = lessonService.selectLessonByLid(lid);
		Enterprise enterprise = enterpriseService.selectEnterpriseByQid(user.getQid());
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		jsonObject.addProperty("lid", lesson.getLid());
		jsonObject.addProperty("lname", lesson.getLname());
		jsonObject.addProperty("lprice", lesson.getLprice());
		jsonObject.addProperty("category", lesson.getCategory());
		jsonObject.addProperty("totalpoint", user.getTotalpoint());
		jsonObject.addProperty("username", user.getUsername());
		jsonObject.addProperty("tel", user.getTel());
		jsonObject.addProperty("discountrate", enterprise.getDiscountrate());
		jsonObject.addProperty("perpointtomoney", enterprise.getPerpointtomoney());
		if(lesson!=null && lesson.getBranches()!=null && !lesson.getBranches().isEmpty()){
			for(Address address : lesson.getBranches()){
				JsonObject object = new JsonObject();
				object.addProperty("branch", address.getBranch());
				jsonArray.add(object);
			}
		}
		jsonObject.add("branches", jsonArray);
		result = jsonObject.toString();
		
		return result;
	}

//	/**
//	 * 通过oid查询Lesson信息
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping("/selectLessonByOid")
//	@ResponseBody
//	public String selectLessonByOid(int oid, HttpServletRequest request) throws Exception{
//		System.out.println("..........LessionHandler..........selectLessonByOid..........");
//		String result = null;
//		
//		Lesson lesson = lessonService.selectLessonByOid(oid);
//		//TODO json格式不详，SQL语句有问题
//	
//		return result;
//	}	 
	
//	/**
//	 * @param request 发来的请求，获取lid bid category currentPage 
//	 * @return 课程对象
//	 * 
//	 */
//	@RequestMapping("/selectHomePageLessonByQid")
//	@ResponseBody
//	public  List<Lesson> selectHomePageLessonByQid(HttpServletRequest request) throws Exception {
//		System.out.println("....LessionHandler.....selectHomePageLessonByQid.......");
//		PageBean pageBean = new PageBean(3,0);
//		HttpSession session = request.getSession();	
//		int qid = (Integer)session.getAttribute("qid");
//		List<Lesson> lessons= lessonService.selectHomePageLessonByQid(pageBean, qid);
//		return lessons;
//	}
}
