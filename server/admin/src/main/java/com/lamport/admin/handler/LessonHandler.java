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
	public String saveLesson(){
		System.out.println("..........LessonHandler..........saveLesson()..........");
		//TODO
		return"";
	}
	/**
	 * 通过id删除Lesson
	 * @return
	 */
	@RequestMapping(value="/admin/deleteLessonByID")
	public String deleteLessonByID(){
		System.out.println("..........LessonHandler..........saveLesson()..........");
		//TODO
		return"";
	}
	/**
	 * 通过id更新Lesson信息
	 * @return
	 */
	@RequestMapping(value="/admin/updateLessonByID")
	public String updateLessonByID(){
		System.out.println("..........LessonHandler..........updateLessonByID()..........");
		//TODO
		return"";
	}
	/**
	 * 通过qid和页码查询Lesson信息
	 * @return
	 */
	@RequestMapping(value="/admin/selectLessonByQIDAndPage")
	public String selectLessonByQIDAndPage(){
		System.out.println("..........LessonHandler..........selectLessonByQIDAndPage()..........");
		//TODO
		return"";
	}
}
