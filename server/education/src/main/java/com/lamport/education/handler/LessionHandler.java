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
import com.lamport.education.util.PageBean;
import com.lamport.education.vo.LessonInfoVo;

@Controller
public class LessionHandler {
	@Autowired
	LessonService lessonService;
	
	/**
	 * @param request 发来的请求，获取lid bid category currentPage 
	 * @return 课程对象
	 * 
	 */
	@RequestMapping("/selectLessonByBranchNameCategoryAndPageAndQid")
	@ResponseBody
	public  List<Lesson> selectLessonByBranchNameCategoryAndPage(HttpServletRequest request){
		System.out.println("....LessionHandler.....selectLessonByBranchNameCategoryAndPageAndQid......");
		int rowId = Integer.parseInt(request.getParameter("rowId"));
		PageBean pageBean = new PageBean(3, rowId);
		HttpSession session = request.getSession();	
		int qid = (Integer)session.getAttribute("qid");
		String branchName =request.getParameter("branchName");
		String category =request.getParameter("category");
		return lessonService.selectLessonPageByBranchNameCategoryAndPage(qid,branchName,category,pageBean);
	}
	@RequestMapping("/selectAllLessonByQidAndPage")
	@ResponseBody
	public  List<Lesson> selectAllLessonByQidAndPage(HttpServletRequest request) throws Exception{
		System.out.println("....LessionHandler.....selectAllLessonByQidAndPage.......");
		int lid = Integer.parseInt(request.getParameter("lid"));
		PageBean pageBean = new PageBean(3, lid );
		HttpSession session = request.getSession();	
		int qid = (Integer)session.getAttribute("qid");
		
		List<Lesson> lessons= lessonService.selectLessonsByQid(pageBean, qid);
		System.out.println("size"+lessons.size());
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
