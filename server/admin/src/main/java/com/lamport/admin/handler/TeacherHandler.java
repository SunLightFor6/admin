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
	 * 通过id删除Teacher信息
	 * @return
	 */
	@RequestMapping(value="/admin/deleteTeacherByID")
	public String deleteTeacherByID(){
		System.out.println("..........TeacherHandler..........deleteTeacherByID()..........");
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
}
