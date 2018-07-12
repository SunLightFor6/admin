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
import com.lamport.admin.po.Admin;
import com.lamport.admin.po.Teacher;
import com.lamport.admin.service.SwiperService;
import com.lamport.admin.service.TeacherService;
import com.lamport.admin.tool.Const;
import com.lamport.admin.vo.QIDAndCategory;
import com.lamport.admin.vo.QIDAndPage;

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
	private SwiperService swiperService;
	
	/**
	 * 创建Teacher信息
	 * @return
	 */
	@RequestMapping(value="/admin/saveTeacher")
	@ResponseBody
	public String saveTeacher(Teacher teacher, MultipartFile teacher_img, HttpServletRequest request) throws Exception{
		System.out.println("..........TeacherHandler..........saveTeacher()..........");
		String result = null;
		
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		teacher.setQid(admin.getQid());
		String path = request.getServletContext().getRealPath("/");//得到当前工程的根路径
		int saveResult = teacherService.saveTeacher(teacher, teacher_img, path);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("reponse", saveResult);
		result = jsonObject.toString();
		
		return result;
	}
	/**
	 * 通过id逻辑删除Teacher信息
	 * @return
	 */
	@RequestMapping(value="/admin/deleteTeacherLogicallyByID")
	@ResponseBody
	public String deleteTeacherLogicallyByID(int id) throws Exception{
		System.out.println("..........TeacherHandler..........deleteTeacherLogicallyByID()..........");
		String result = null;

		int deleteResult = teacherService.deleteTeacherLogicallyByID(id);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("response", deleteResult);
		result = jsonObject.toString();
		
		return result;
	}
	/**
	 * 通过id修改Teacher信息
	 * @return
	 */
	@RequestMapping(value="/admin/updateTeacherByID")
	@ResponseBody
	public String updateTeacherByID(Teacher teacher, MultipartFile teacher_img, HttpServletRequest request) throws Exception{
		System.out.println("..........TeacherHandler..........updateTeacherByID()..........");
		String result = null;
		
		String path = request.getServletContext().getRealPath("/");//得到当前工程的根路径
		int updateResult = teacherService.updateTeacherByID(teacher, teacher_img, path);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("reponse", updateResult);
		result = jsonObject.toString();
		
		return result;
	}
	/**
	 * 通过qid修改TeacherSwiper(教师轮播图)信息
	 * @return
	 */
	@RequestMapping(value="/admin/updateTeacherSwiperByQID")
	@ResponseBody
	public String updateTeacherSwiperByQID(MultipartFile[] imgs, HttpServletRequest request) throws Exception{
		System.out.println("..........TeacherHandler..........updateTeacherSwiperByQID()..........");
		String result = null;
		
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		QIDAndCategory qidAndCategory = new QIDAndCategory();
		qidAndCategory.setCategory(Const.SwiperCategoryT);
		qidAndCategory.setQid(admin.getQid());
		String path = request.getServletContext().getRealPath("/");//得到当前工程的根路径
		int updateResult = swiperService.updateMultipleSwipersByQIDAndCategory(qidAndCategory, imgs, path);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("reponse", updateResult);
		result = jsonObject.toString();
		
		return result;
	}
	/**
	 * 通过qid和页码查询Teacher信息
	 * @return
	 */
	@RequestMapping(value="/admin/selectTeacherByQIDAndPage")
	@ResponseBody
	public String selectTeacherByQIDAndPage(QIDAndPage qidAndPage, HttpServletRequest request) throws Exception{
		System.out.println("..........TeacherHandler..........selectTeacherByQIDAndPage()..........");
		String result = null;

		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		qidAndPage.setQid(admin.getQid());
		List<Teacher> teachers = teacherService.selectTeacherByQIDAndPage(qidAndPage);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("code", 0);
		jsonObject.addProperty("msg", "");
		jsonObject.addProperty("count", qidAndPage.getPageTool().getCount());
		JsonArray jsonArray = new JsonArray();
		for(Teacher teacher : teachers){
			JsonObject object = new JsonObject();
			object.addProperty("id", teacher.getTid());
			object.addProperty("teachername", teacher.getTname());
			object.addProperty("teacherimg", teacher.getTphoto());
			object.addProperty("teacherintro", teacher.getIntroduction());
			jsonArray.add(object);
		}
		jsonObject.add("data", jsonArray);
		result = jsonObject.toString();
		
		return result;
	}
	/**
	 * 通过qid查询TeacherSwiper(教师轮播图)信息
	 * @return
	 */
	@RequestMapping(value="/admin/selectTeacherSwiperByQID")
	@ResponseBody
	public String selectTeacherSwiperByQID(HttpServletRequest request) throws Exception{
		System.out.println("..........TeacherHandler..........selectTeacherSwiperByQID()..........");
		String result = null;

		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		QIDAndCategory qidAndCategory = new QIDAndCategory();
		qidAndCategory.setQid(admin.getQid());
		qidAndCategory.setCategory(Const.SwiperCategoryT);
		List<String> swiperImgruls = swiperService.selectSwiperImgurlByQIDAndCategory(qidAndCategory);
		
		JsonObject jsonObject = new JsonObject();
		for(String swiperImgrul : swiperImgruls){
			jsonObject.addProperty(swiperImgrul, swiperImgrul);
		}
		result = jsonObject.toString();
		
		return result;
	}
}
