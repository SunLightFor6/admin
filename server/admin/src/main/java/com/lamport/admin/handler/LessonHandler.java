package com.lamport.admin.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lamport.admin.po.Address;
import com.lamport.admin.po.Admin;
import com.lamport.admin.po.Lesson;
import com.lamport.admin.service.LessonService;
import com.lamport.admin.tool.Const;
import com.lamport.admin.vo.LessonQueryCondition;

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
	public String saveLesson(Lesson lesson, MultipartFile imgFile, HttpServletRequest request) throws Exception{
		System.out.println("..........LessonHandler..........saveLesson()..........lesson:" + lesson.getLname() + " imgFile:" + imgFile);
		String result = null;

		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		lesson.setQid(admin.getQid());
		String path = Const.Path;//存储路径
		int saveResult = lessonService.saveLesson(lesson, imgFile, path);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("response", saveResult);
		result = jsonObject.toString();
		
		return result;
	}
	/**
	 * 通过id逻辑删除Lesson
	 * @return
	 */
	@RequestMapping(value="/admin/deleteLessonByID")
	@ResponseBody
	public String deleteLessonLogicallyByID(int id, HttpServletRequest request) throws Exception{
		System.out.println("..........LessonHandler..........deleteLessonLogicallyByID()..........id = " + id);
		String result = null;

		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		int deleteResult = lessonService.deleteLessonLogicallyByID(id, admin.getQid());
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("response", deleteResult);
		result = jsonObject.toString();
		
		return result;
	}
	/**
	 * 通过id修改Lesson信息
	 * @return
	 */
	@RequestMapping(value="/admin/updateLessonByID")
	@ResponseBody
	public String updateLessonByID(Lesson lesson, MultipartFile imgFile, HttpServletRequest request) throws Exception{
		System.out.println("..........LessonHandler..........updateLessonByID()..........lesson:" + lesson.getLname() + " imgFile:" + imgFile);
		String result = null;
		
		String path = Const.Path;
		int updateResult = lessonService.updateLessonByID(lesson, imgFile, path);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("response", updateResult);
		result = jsonObject.toString();
		
		return result;
	}
	/**
	 * 通过多条件查询Lesson信息
	 * @return
	 */
	@RequestMapping(value="/admin/selectLessonByLessonQueryCondition")
	@ResponseBody
	public String selectLessonByLessonQueryCondition(LessonQueryCondition lessonQueryCondition, HttpServletRequest request) throws Exception{
		System.out.println("..........LessonHandler..........selectLessonByLessonQueryCondition()..........lessonQueryCondition:" + lessonQueryCondition.getBranch());
		String result = null;

		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		lessonQueryCondition.setQid(admin.getQid());
		lessonQueryCondition.setPageTool();
		List<Lesson> lessons = lessonService.selectLessonByLessonQueryCondition(lessonQueryCondition);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("code", 0);
		jsonObject.addProperty("msg", "");
		jsonObject.addProperty("count", lessonQueryCondition.getPageTool().getCount());
		JsonArray jsonArray = new JsonArray();
		if(lessons!=null && !lessons.isEmpty()){
			for(Lesson lesson : lessons){
				JsonObject object = new JsonObject();
				object.addProperty("courseid", lesson.getLid());
				object.addProperty("coursename", lesson.getLname());
				object.addProperty("courseimg", lesson.getImgurl());
				object.addProperty("courseprice", lesson.getLprice());
				object.addProperty("coursecategory", lesson.getCategory());
				JsonArray branches = new JsonArray();
				if(lesson!=null && lesson.getBranches()!=null && !lesson.getBranches().isEmpty()){
					for(Address address : lesson.getBranches()){
						branches.add(address.getBranch());
					}
				}
				object.add("branch", branches);			
				object.addProperty("pubtime", lesson.getPubtime());
				object.addProperty("ldesc", lesson.getLdesc());
				object.addProperty("status", lesson.getStatus());
				jsonArray.add(object);
			}
		}
		jsonObject.add("data", jsonArray);
		result = jsonObject.toString();
		
		return result;
	}
}
