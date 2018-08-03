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
import com.lamport.education.po.Point;
import com.lamport.education.po.User;
import com.lamport.education.service.PointService;
import com.lamport.education.service.SignService;
import com.lamport.education.util.Config;
import com.lamport.education.vo.UIDAndPage;

/**
 * Controller, 进行Point(积分)信息的查询
 * @author Lin Zhao, protector of Sherry
 *
 */
@Controller
public class PointHandler {
	
	@Autowired
	PointService pointService;
	@Autowired
	SignService signService;
	
	/**
	 * 通过uid和页码查询积分明细
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selectPointByUIDAndPage")
	@ResponseBody
	public String selectPointByUIDAndPage(UIDAndPage uidAndPage, HttpServletRequest request) throws Exception{
		System.out.println("..........CouponHanler..........selectPointByUIDAndPage..........");
		String result = null;
		
		String nullString = null;
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		uidAndPage.setUid(user.getUid());
		uidAndPage.initPageBean(Config.PointPageSize);
		List<Point> points = pointService.selectPointByUIDAndPage(uidAndPage);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		if(points!=null && !points.isEmpty()){
			for(Point point : points){
				JsonObject object = new JsonObject();
				object.addProperty("pid", point.getPid());
				object.addProperty("point", point.getPoint());
				object.addProperty("category", point.getCategory());
				object.addProperty("oid", point.getOid());
				object.addProperty("date", point.getDate());
				if(point!=null && point.getSorder()!=null && point.getSorder().getLesson()!=null){
					object.addProperty("lname", point.getSorder().getLesson().getLname());
				}else{
					object.addProperty("lname", nullString);
				}
				jsonArray.add(object);
			}
		}
		jsonObject.add("points", jsonArray);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 通过uid查询用户积分和连续签到天数
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selectTotalPointByUID")
	@ResponseBody
	public String selectTotalPointByUID(HttpServletRequest request) throws Exception{
		System.out.println("..........CouponHanler..........selectTotalPointByUID..........");
		String result = null;
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		int totalPoint = user.getTotalpoint();
		int days = signService.selectDaysByUID(user.getUid());
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("totalpoint", totalPoint);
		jsonObject.addProperty("days", days);
		result = jsonObject.toString();
		
		return result;
	}
}
