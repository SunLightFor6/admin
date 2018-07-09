package com.lamport.admin.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller, 进行Teacher信息的增加、删除、修改、查询
 * @author Lin Zhao, protector of Sherry
 *
 */
@Controller
public class TeacherHandler {
	/**
	 * 创建Teacher信息
	 * @return
	 */
	@RequestMapping(value="/admin/saveTeacher")
	public String saveTeacher(){
		System.out.println("..........TeacherHandler..........saveTeacher()..........");
		//TODO
		return"";
	}
	/**
	 * 通过id逻辑删除Teacher信息
	 * @return
	 */
	@RequestMapping(value="/admin/deleteTeacherLogicallyByID")
	public String deleteTeacherLogicallyByID(){
		System.out.println("..........TeacherHandler..........deleteTeacherLogicallyByID()..........");
		//TODO
		return"";
	}
	/**
	 * 通过id修改Teacher信息
	 * @return
	 */
	@RequestMapping(value="/admin/updateTeacherByID")
	public String updateTeacherByID(){
		System.out.println("..........TeacherHandler..........updateTeacherByID()..........");
		//TODO
		return"";
	}
	/**
	 * 通过qid修改TeacherSwiper(教师轮播图)信息
	 * @return
	 */
	@RequestMapping(value="/admin/updateTeacherSwiperByQID")
	public String updateTeacherSwiperByQID(){
		System.out.println("..........TeacherHandler..........updateTeacherSwiperByQID()..........");
		//TODO
		return"";
	}
	/**
	 * 通过qid和页码查询Teacher信息
	 * @return
	 */
	@RequestMapping(value="/admin/selectTeacherByQIDAndPage")
	public String selectTeacherByQIDAndPage(){
		System.out.println("..........TeacherHandler..........selectTeacherByQIDAndPage()..........");
		//TODO
		return"";
	}
	/**
	 * 通过qid查询TeacherSwiper(教师轮播图)信息
	 * @return
	 */
	@RequestMapping(value="/admin/selectTeacherSwiperByQID")
	public String selectTeacherSwiperByQID(){
		System.out.println("..........TeacherHandler..........selectTeacherSwiperByQID()..........");
		//TODO
		return"";
	}
}
