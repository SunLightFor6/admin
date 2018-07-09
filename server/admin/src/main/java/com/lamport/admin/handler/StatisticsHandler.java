package com.lamport.admin.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller, 进行信息的统计（用户总数、课程总数、教师总数、订单总数、预约总数、订单总额）
 * @author Lin Zhao, protector of Sherry
 *
 */
@Controller
public class StatisticsHandler {
	
	/**
	 * 统计信息（用户总数、课程总数、教师总数、订单总数、预约总数、订单总额）
	 * @return String
	 */
	@RequestMapping(value="/admin/statistics")
	public String Statistics() throws Exception{
		System.out.println("..........StatisticsHandler..........Statistics()..........");
		//TODO
		return"";
	}
}
