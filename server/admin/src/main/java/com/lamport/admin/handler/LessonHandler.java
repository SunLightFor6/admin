package com.lamport.admin.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller, 进行Lesson(精品课)信息的增加、删除、修改、查询
 * @author Lin Zhao, protector of Sherry
 *
 */
@Controller
public class LessonHandler {
	/**
	 * 创建Lesson
	 * @return
	 */
	@RequestMapping(value="/admin/saveLesson")
	public String saveLesson() throws Exception{
		System.out.println("..........LessonHandler..........saveLesson()..........");
		//TODO
		return"";
	}
	/**
	 * 通过id逻辑删除Lesson
	 * @return
	 */
	@RequestMapping(value="/admin/deleteLessonByID")
	public String deleteLessonLogicallyByID() throws Exception{
		System.out.println("..........LessonHandler..........deleteLessonLogicallyByID()..........");
		//TODO
		return"";
	}
	/**
	 * 通过id修改Lesson信息
	 * @return
	 */
	@RequestMapping(value="/admin/updateLessonByID")
	public String updateLessonByID() throws Exception{
		System.out.println("..........LessonHandler..........updateLessonByID()..........");
		//TODO
		return"";
	}
	/**
	 * 通过qid和页码查询Lesson信息
	 * @return
	 */
	@RequestMapping(value="/admin/selectLessonByQIDAndPage")
	public String selectLessonByQIDAndPage() throws Exception{
		System.out.println("..........LessonHandler..........selectLessonByQIDAndPage()..........");
		//TODO
		return"";
	}
}
