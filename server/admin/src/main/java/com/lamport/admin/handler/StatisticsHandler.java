package com.lamport.admin.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.lamport.admin.po.Admin;
import com.lamport.admin.service.FreeListenBookService;
import com.lamport.admin.service.FreeListenService;
import com.lamport.admin.service.LessonService;
import com.lamport.admin.service.SorderService;
import com.lamport.admin.service.TeacherService;
import com.lamport.admin.service.UserService;

/**
 * Controller, 进行信息的统计（用户总数、课程总数、教师总数、订单总数、预约总数、订单总额）
 * @author Lin Zhao, protector of Sherry
 *
 */
@Controller
public class StatisticsHandler {
	
	@Autowired
	private FreeListenService freeListenService;
	@Autowired
	private FreeListenBookService freeListenBookService;
	@Autowired
	private LessonService lessonService;
	@Autowired
	private SorderService sorderService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private UserService userService;

	/**
	 * 统计信息（用户总数、课程总数、教师总数、订单总数、预约总数、订单总额）
	 * @return String
	 */
	@RequestMapping(value="/admin/statistics")
	@ResponseBody
	public String Statistics(HttpServletRequest request) throws Exception{
		System.out.println("..........StatisticsHandler..........Statistics()..........");
		String result = null;
		
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		int qid = admin.getQid();
		int countUser = userService.selectCountUserByQID(qid);
		int countCourse = lessonService.selectCountLessonByQID(qid)+freeListenService.selectCountFreeListenByQID(qid);
		int countTeacher = teacherService.selectCountTeacherByQID(qid);
		int countSorder = sorderService.selectCountSorderByQID(qid);
		int countBook = freeListenBookService.selectCountFreeListenBookByQID(qid);
		double countIncome = sorderService.selectCountSorderActualByQID(qid);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("user_total", countUser);
		jsonObject.addProperty("course_total", countCourse);
		jsonObject.addProperty("teacher_total", countTeacher);
		jsonObject.addProperty("order_total", countSorder);
		jsonObject.addProperty("book_total", countBook);
		jsonObject.addProperty("profit_total", countIncome);
		result = jsonObject.toString();
		
		return result;
	}
}
