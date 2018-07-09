package com.lamport.admin.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lamport.admin.mapper.SwiperMapper;
import com.lamport.admin.service.TeacherService;

/**
 * Controller, 进行Teacher信息的增加、删除、修改、查询
 * @author Lin Zhao, protector of Sherry
 *
 */
@Controller
public class TeacherHandler {
	
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private SwiperMapper swiperMapper;
	
	/**
	 * 创建Teacher信息
	 * @return
	 */
	@RequestMapping(value="/admin/saveTeacher")
	@ResponseBody
	public String saveTeacher() throws Exception{
		System.out.println("..........TeacherHandler..........saveTeacher()..........");
		String result = null;
		//TODO
		return result;
	}
	/**
	 * 通过id逻辑删除Teacher信息
	 * @return
	 */
	@RequestMapping(value="/admin/deleteTeacherLogicallyByID")
	@ResponseBody
	public String deleteTeacherLogicallyByID() throws Exception{
		System.out.println("..........TeacherHandler..........deleteTeacherLogicallyByID()..........");
		String result = null;
		//TODO
		return result;
	}
	/**
	 * 通过id修改Teacher信息
	 * @return
	 */
	@RequestMapping(value="/admin/updateTeacherByID")
	@ResponseBody
	public String updateTeacherByID() throws Exception{
		System.out.println("..........TeacherHandler..........updateTeacherByID()..........");
		String result = null;
		//TODO
		return result;
	}
	/**
	 * 通过qid修改TeacherSwiper(教师轮播图)信息
	 * @return
	 */
	@RequestMapping(value="/admin/updateTeacherSwiperByQID")
	@ResponseBody
	public String updateTeacherSwiperByQID() throws Exception{
		System.out.println("..........TeacherHandler..........updateTeacherSwiperByQID()..........");
		String result = null;
		//TODO
		return result;
	}
	/**
	 * 通过qid和页码查询Teacher信息
	 * @return
	 */
	@RequestMapping(value="/admin/selectTeacherByQIDAndPage")
	@ResponseBody
	public String selectTeacherByQIDAndPage() throws Exception{
		System.out.println("..........TeacherHandler..........selectTeacherByQIDAndPage()..........");
		String result = null;
		//TODO
		return result;
	}
	/**
	 * 通过qid查询TeacherSwiper(教师轮播图)信息
	 * @return
	 */
	@RequestMapping(value="/admin/selectTeacherSwiperByQID")
	@ResponseBody
	public String selectTeacherSwiperByQID() throws Exception{
		System.out.println("..........TeacherHandler..........selectTeacherSwiperByQID()..........");
		String result = null;
		//TODO
		return result;
	}
}
