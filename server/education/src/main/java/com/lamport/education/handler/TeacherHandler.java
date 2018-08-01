package com.lamport.education.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lamport.education.po.Teacher;
import com.lamport.education.service.SwiperService;
import com.lamport.education.service.TeacherService;
import com.lamport.education.util.Config;
import com.lamport.education.vo.QIDAndCategory;
import com.lamport.education.vo.QIDAndPage;

@Controller
public class TeacherHandler {
	
	@Autowired
	TeacherService teacherService;
	@Autowired
	SwiperService swiperService;
	
	/**
	 * 通过qid和页码查询Teacher信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectTeacherByQIDAndPage")
	@ResponseBody
	public String selectTeacherByQIDAndPage(QIDAndPage qidAndPage, HttpServletRequest request) throws Exception{
		System.out.println("..........EnterpriseHandler..........selectTeacherByQIDAndPage..........");
		String result = null;
		
		HttpSession session = request.getSession();
		int qid = (Integer)session.getAttribute("qid");
		qidAndPage.setQid(qid);
		qidAndPage.initPageBean(Config.TeacherPageSize);
		List<Teacher> teachers = teacherService.selectTeacherByQIDAndPage(qidAndPage);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		if(teachers!=null && !teachers.isEmpty()){
			for(Teacher teacher : teachers){
				JsonObject object = new JsonObject();
				object.addProperty("tid", teacher.getTid());
				object.addProperty("tname", teacher.getTname());
				object.addProperty("tphoto", teacher.getTphoto());
				object.addProperty("introduction", teacher.getIntroduction());
				jsonArray.add(object);
			}
		}
		jsonObject.add("teachers", jsonArray);
		result = jsonObject.toString();

		return result;
	}

	/**
	 * 通过qid查询企业轮播图信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selectTeacherSwiperByQID")
	@ResponseBody
	public String selectTeacherSwiperByQID(HttpServletRequest request) throws Exception{
		System.out.println("..........EnterpriseHandler....................");
		String result = null;
		
		HttpSession session = request.getSession();
		int qid = (Integer)session.getAttribute("qid");
		QIDAndCategory qidAndCategory = new QIDAndCategory();
		qidAndCategory.setQid(qid);
		qidAndCategory.setCategory(Config.TeacherSwiper);
		List<String> swiperImgurls = swiperService.selectSwiperImgurlByQIDAndCategory(qidAndCategory);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		if(swiperImgurls!=null && !swiperImgurls.isEmpty()){
			for(String imgurl : swiperImgurls){
				JsonObject object = new JsonObject();
				object.addProperty("imgurl", imgurl);
				jsonArray.add(object);
			}
		}
		jsonObject.add("swipers", jsonArray);
		result = jsonObject.toString();

		return result;
	}
}
