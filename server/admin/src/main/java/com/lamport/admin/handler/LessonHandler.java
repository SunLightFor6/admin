package com.lamport.admin.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lamport.admin.service.LessonService;

/**
 * Controller, 进行Lesson(精品课)信息的增加、删除、修改、查询
 * @author Lin Zhao, protector of Sherry
 *
 */
@Controller
public class LessonHandler {
	
	@Autowired
	private LessonService lessonService;
	
	/**
	 * 创建Lesson
	 * @return
	 */
	@RequestMapping(value="/admin/saveLesson")
	@ResponseBody
	public String saveLesson() throws Exception{
		System.out.println("..........LessonHandler..........saveLesson()..........");
		String result = null;
		//TODO
		return result;
	}
	/**
	 * 通过id逻辑删除Lesson
	 * @return
	 */
	@RequestMapping(value="/admin/deleteLessonByID")
	@ResponseBody
	public String deleteLessonLogicallyByID() throws Exception{
		System.out.println("..........LessonHandler..........deleteLessonLogicallyByID()..........");
		String result = null;
		//TODO
		return result;
	}
	/**
	 * 通过id修改Lesson信息
	 * @return
	 */
	@RequestMapping(value="/admin/updateLessonByID")
	@ResponseBody
	public String updateLessonByID() throws Exception{
		System.out.println("..........LessonHandler..........updateLessonByID()..........");
		String result = null;
		//TODO
		return result;
	}
	/**
	 * 通过多条件查询Lesson信息
	 * @return
	 */
	@RequestMapping(value="/admin/selectLessonByLessonQueryCondition")
	@ResponseBody
	public String selectLessonByLessonQueryCondition() throws Exception{
		System.out.println("..........LessonHandler..........selectLessonByLessonQueryCondition()..........");
		String result = null;
		//TODO
		return result;
	}
}
