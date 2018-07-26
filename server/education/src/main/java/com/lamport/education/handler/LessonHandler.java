package com.lamport.education.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lamport.education.po.Lesson;
import com.lamport.education.service.LessonService;
import com.lamport.education.util.Config;
import com.lamport.education.util.PageBean;
import com.lamport.education.vo.LessonInfoVo;
import com.lamport.education.vo.LessonQueryCondition;

@Controller
public class LessonHandler {
	@Autowired
	LessonService lessonService;
	
	
	
	/**
	 * @param request 发来的请求，获取lid bid category currentPage 
	 * @return 课程对象
	 * 
	 */
	@RequestMapping("/selectLessonByLessonQueryCondition")
	@ResponseBody
	public String selectLessonByLessonQueryConditions(LessonQueryCondition lessonQueryCondition, HttpServletRequest request) throws Exception {
		System.out.println("..........LessionHandler..........selectLessonByLessonQueryCondition..........");
		String result = null;
		
		HttpSession session = request.getSession();	
		int qid = (Integer)session.getAttribute("qid");
		lessonQueryCondition.setQid(qid);
		lessonQueryCondition.initPageBean(Config.LessonPageSize);
		List<Lesson> lessons = lessonService.selectLessonByLessonQueryCondition(lessonQueryCondition);
		//TODO json返回格式不详
		//TODO SQL语句待修改
		
//		return lessonService.selectLessonByBranchNameCategoryAndPage(qid,branchName,category,pageBean);
		return result;
	}
	
	/**
	 * @param request 发来的请求，获取lid bid category currentPage 
	 * @return 课程对象
	 * 
	 */
	@RequestMapping("/selectHomePageLessonByQid")
	@ResponseBody
	public  List<Lesson> selectHomePageLessonByQid(HttpServletRequest request) throws Exception {
		System.out.println("....LessionHandler.....selectHomePageLessonByQid.......");
		PageBean pageBean = new PageBean(3,0);
		HttpSession session = request.getSession();	
		int qid = (Integer)session.getAttribute("qid");
		List<Lesson> lessons= lessonService.selectHomePageLessonByQid(pageBean, qid);
		return lessons;
	}
	
	@RequestMapping("/selectLessonByLid")
	@ResponseBody
	public  LessonInfoVo selectLessonByLid(HttpServletRequest request) throws Exception{
		System.out.println("....LessionHandler.....selectLessonByLid.......");
		int lid = Integer.parseInt(request.getParameter("lessonId"));
		return lessonService.selectLessonByLid(lid);
	}
	
	@RequestMapping("/selectLessonByOid")
	@ResponseBody
	public  LessonInfoVo selectLessonByOid(HttpServletRequest request) throws Exception{
		System.out.println("....LessionHandler.....selectLessonByOid.......");
		int oid = Integer.parseInt(request.getParameter("oid"));
		return lessonService.selectLessonByOid(oid);
	}
	 
}
