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
import com.lamport.education.util.Config;
import com.lamport.education.util.PageBean;
import com.lamport.education.vo.QIDAndPage;

@Controller
public class TeacherHandler {
	
	@Autowired
	TeacherService teacherService;
	
	/**
	 * 通过qid和页码查询Teacher信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectTeacherByQIDAndPage")
	@ResponseBody
	public String selectTeacherByQIDAndPage(HttpServletRequest request, QIDAndPage qidAndPage) throws Exception{
		System.out.println("..........EnterpriseHandler..........selectTeacherByQIDAndPage..........");
		String result = null;
		
		HttpSession session = request.getSession();
		int qid = (Integer)session.getAttribute("qid");
		qidAndPage.setQid(qid);
		qidAndPage.initPageBean(Config.TeacherPageSize);
		List<Teacher> teachers = teacherService.selectTeacherByQIDAndPage(qidAndPage);
		//TODO
		//不清楚返回格式
		
		
		
		
		return result;
	}
	
}
