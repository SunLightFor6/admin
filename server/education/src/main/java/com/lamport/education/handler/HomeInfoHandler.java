package com.lamport.education.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lamport.education.po.FreeListen;
import com.lamport.education.po.Lesson;
import com.lamport.education.service.HomeInfoService;
import com.lamport.education.service.SwiperService;
import com.lamport.education.util.Config;
import com.lamport.education.vo.QIDAndCategory;
import com.lamport.education.vo.QIDAndPage;

/**
 * Controller, 进行首页信息的查询
 * @author Lin Zhao, protector of Sherry
 *
 */
public class HomeInfoHandler {

	@Autowired
	SwiperService swiperService;
	@Autowired
	HomeInfoService homeInfoService;
	
	/**
	 * 通过qid查询首页需要展示的信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selectHomeInfoByQID")
	@ResponseBody
	public String selectHomeInfoByQID(int qid, HttpServletRequest request) throws Exception{
		System.out.println("..........HomeHandler..........selectHomeInfoByQID..........");
		String result = null;
		
		HttpSession session = request.getSession();	
		session.setAttribute("qid", qid);
		System.out.println("qid = "+ qid);				/*####################*/
		//查询企业轮播图
		QIDAndCategory qidAndCategory = new QIDAndCategory();
		qidAndCategory.setQid(qid);
		qidAndCategory.setCategory(Config.EnterpriseSwiper);
		List<String> swiperImgurls = swiperService.selectSwiperImgurlByQIDAndCategory(qidAndCategory);
		//查询首页展示的试听课程信息
		QIDAndPage qidAndPage = new QIDAndPage();
		qidAndPage.setQid(qid);
		qidAndPage.setStartId(0);
		qidAndPage.initPageBean(Config.HomeFreeListenPageSize);
		List<FreeListen> freeListens = homeInfoService.selectHomePageFreeListenByQid(qidAndPage);
		//查询首页展示的精品课程信息
		qidAndPage.initPageBean(Config.HomeLessonPageSize);
		List<Lesson> lessons = homeInfoService.selectHomePageLessonByQid(qidAndPage);
		JsonObject jsonObject = new JsonObject();
		JsonArray swiperArray = new JsonArray();
		if(swiperImgurls!=null && !swiperImgurls.isEmpty()){
			for(String swiperImgurl : swiperImgurls){
				JsonObject object = new JsonObject();
				object.addProperty("imgurl", swiperImgurl);
				swiperArray.add(object);
			}
		}
		jsonObject.add("swipers", swiperArray);
		JsonArray freeListenArray = new JsonArray();
		if(freeListens!=null && !freeListens.isEmpty()){
			for(FreeListen freeListen : freeListens){
				JsonObject object = new JsonObject();
				object.addProperty("fid", freeListen.getId());
				object.addProperty("imgurl", freeListen.getImgurl());
				object.addProperty("title", freeListen.getTitle());
				object.addProperty("fdesc", freeListen.getFdesc());
				freeListenArray.add(object);
			}
		}
		jsonObject.add("freelistens", freeListenArray);
		JsonArray lessonArray = new JsonArray();
		if(lessons!=null && !lessons.isEmpty()){
			for(Lesson lesson : lessons){
				JsonObject object = new JsonObject();
				object.addProperty("lid", lesson.getLid());
				object.addProperty("imgurl", lesson.getImgurl());
				object.addProperty("lname", lesson.getLname());
				object.addProperty("ldesc", lesson.getLdesc());
				lessonArray.add(object);
			}
		}
		jsonObject.add("lessons", lessonArray);
		result = jsonObject.toString();
		
		return result;
	}
}
