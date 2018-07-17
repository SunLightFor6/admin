package com.lamport.education.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lamport.education.po.Teacher;
import com.lamport.education.service.TeacherService;
import com.lamport.education.util.PageBean;

@Controller
public class TeacherHandler {
	@Autowired
	TeacherService teacherService;
	/**
	 * 
	 * @param request  获取企业qid,然后查询
	 * @return 返回老师对象列表
	 * @throws Exception 
	 */
	@RequestMapping("/selectAllTeachersByPageAndQid")
	@ResponseBody
	public List<Teacher> getAllTeachers(HttpServletRequest request) throws Exception{
		System.out.println("....EnterpriseHandler......selectAllTeachersByPageAndQid.......");
		HttpSession session = request.getSession();
		int qid = (Integer)session.getAttribute("qid");
		PageBean page = new PageBean(5, -1);
		List<Teacher> teachers = teacherService.selectTeacherPageByQid(page, qid);
		return teachers;
	}
	
 
}
