package com.lamport.admin.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lamport.admin.po.FreeListen;
import com.lamport.admin.service.AdminService;
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
	public String Statistics() throws Exception{
		System.out.println("..........StatisticsHandler..........Statistics()..........");
		String result = null;
		//TODO
		return result;
	}
}
