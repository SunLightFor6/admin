package com.lamport.admin.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lamport.admin.po.Admin;
import com.lamport.admin.service.FreeListenBookService;
import com.lamport.admin.service.FreeListenService;
import com.lamport.admin.service.LessonService;
import com.lamport.admin.service.SorderService;
import com.lamport.admin.service.StatisticsService;
import com.lamport.admin.service.TeacherService;
import com.lamport.admin.service.UserService;
import com.lamport.admin.vo.QIDAndPage;
import com.lamport.admin.vo.StatisticsQueryResult;

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
	@Autowired
	private StatisticsService statisticsService;
	
	/**
	 * 统计最近六个月（包含本月）， 每个月的订单数量和预约数量
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/selectAmountOfLastHalfYearByQid")
	@ResponseBody
	public String selectAmountOfLastHalfYearByQid(HttpServletRequest request) throws Exception{
		System.out.println("..........StatisticsHandler..........selectAmountOfLastHalfYearByQid()..........");
		String result = null;
		
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		int qid = admin.getQid();
		QIDAndPage qidAndPage = new QIDAndPage();
		qidAndPage.setQid(qid);
		qidAndPage.setLimit(6);
		List<StatisticsQueryResult> statisticsQueryResults = statisticsService.selectMonthCountSorderAndBookByQIDAndPage(qidAndPage);
		JsonObject jsonObject = new JsonObject();
		JsonArray sorderArray = new JsonArray();
		JsonArray bookArray = new JsonArray();
		for(StatisticsQueryResult queryResult : statisticsQueryResults){
			sorderArray.add(queryResult.getCountsorder());
			bookArray.add(queryResult.getCountbook());
		}
		jsonObject.add("order", sorderArray);
		jsonObject.add("book", bookArray);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 统计最近30天，每天的订单交易额
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/selectGainOfLastMonthByQid")
	@ResponseBody
	public String selectGainOfLastMonthByQid(HttpServletRequest request) throws Exception{
		System.out.println("..........StatisticsHandler..........selectGainOfLastMonthByQid()..........");
		String result = null;
		
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		int qid = admin.getQid();
		QIDAndPage qidAndPage = new QIDAndPage();
		qidAndPage.setQid(qid);
		qidAndPage.setLimit(30);
		List<StatisticsQueryResult> statisticsQueryResults = statisticsService.selectDayCountSorderActualByQIDAndPage(qidAndPage);
		JsonArray jsonArray = new JsonArray();
		for(StatisticsQueryResult queryResult : statisticsQueryResults){
			jsonArray.add(queryResult.getCountActual());
		}
		result = jsonArray.toString();

		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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
