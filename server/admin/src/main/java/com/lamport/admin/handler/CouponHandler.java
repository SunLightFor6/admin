package com.lamport.admin.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lamport.admin.po.Admin;
import com.lamport.admin.po.Coupon;
import com.lamport.admin.service.CouponService;
import com.lamport.admin.vo.CouponQueryCondition;
import com.lamport.admin.vo.MeetingCondition;

/**
 * Controller, 进行Coupon(优惠券)及其相关页面信息的增加、删除、修改、查询
 * @author Lin Zhao, protector of Sherry
 *
 */
@Controller
public class CouponHandler {
	
	@Autowired
	CouponService couponService;
	
	/**
	 * 创建Coupon信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/saveCoupon")
	@ResponseBody
	public String saveCoupon(Coupon coupon, HttpServletRequest request) throws Exception{
		System.out.println("..........CouponHandler..........saveCoupon()..........");
		String result = null;
		
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		coupon.setQid(admin.getQid());
		couponService.saveCoupon(coupon);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("response", 1);
		jsonObject.addProperty("cid", coupon.getCid());
		result = jsonObject.toString();
		
		return result;
	}
	/**
	 * 通过cid删除Coupon信息
	 * 同时还要删除该coupon关联的couponrecord信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/deleteCouponByCid")
	@ResponseBody
	public String deleteCouponByCid(Coupon coupon) throws Exception{
		System.out.println("..........CouponHandler..........selectCouponsByCouponQueryCondition()..........");
		String result = null;
		
		couponService.deleteCouponLogicallyByCID(coupon);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("response", 1);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 通过cid修改coupon的deadline信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/updateCouponDeadLineByCidAndDeadline")
	@ResponseBody
	public String updateCouponDeadLineByCidAndDeadline(Coupon coupon) throws Exception{
		System.out.println("..........CouponHandler..........updateCouponDeadLineByCidAndDeadline()..........");
		String result = null;
		
		couponService.updateCouponDeadLineByCID(coupon);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("response", 1);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 发放优惠券
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/handoutCoupon")
	@ResponseBody
	public String handoutCoupon(MeetingCondition meetingCondition, HttpServletRequest request) throws Exception{
		System.out.println("..........CouponHandler..........handoutCoupon()..........");
		String result = null;
		
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		meetingCondition.setQid(admin.getQid());
		couponService.updateCouponByMeetingCondition(meetingCondition);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("response", 1);
		result = jsonObject.toString();
		
		return result;
	}
	/**
	 * 通过多条件查询Coupon信息
	 * @return
	 */
	@RequestMapping(value="/admin/selectCouponsByCouponQueryCondition")
	@ResponseBody
	public String selectCouponsByCouponQueryCondition(CouponQueryCondition couponQueryCondition, HttpServletRequest request) throws Exception{
		System.out.println("..........CouponHandler..........selectCouponsByCouponQueryCondition()..........");
		System.out.println("cid = " + couponQueryCondition.getCid());
		System.out.println("category = " + couponQueryCondition.getCategory());
		System.out.println("queryBasis = " + couponQueryCondition.getQueryBasis());
		System.out.println("queryOrderBasis = " + couponQueryCondition.getQueryOrderBasis());
		System.out.println("showDisabled = " + couponQueryCondition.getShowDisabled());

		String result = null;
		
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		couponQueryCondition.setQid(admin.getQid());
		couponQueryCondition.initPageTool();
		List<Coupon> coupons = couponService.selectCouponsByCouponQueryCondition(couponQueryCondition);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();		
		jsonObject.addProperty("code", 0);
		jsonObject.addProperty("msg", "");
		jsonObject.addProperty("count", couponQueryCondition.getPageTool().getCount());
		if(coupons!=null && !coupons.isEmpty()){
			for(Coupon coupon : coupons){
				JsonObject object = new JsonObject();
				object.addProperty("cid", coupon.getCid());
				object.addProperty("category", coupon.getCategory());
				object.addProperty("money", "￥"+coupon.getMoney());
				object.addProperty("needmoney", "￥"+coupon.getNeedmoney());
				object.addProperty("amount", coupon.getGet());
				object.addProperty("deadline", coupon.getDeadline());
				jsonArray.add(object);
			}
		}
		jsonObject.add("data", jsonArray);
		result = jsonObject.toString();
		
		return result;
	}
	/**
	 * 查询符合条件的User的总数
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/selectAmountMeetingCondition")
	@ResponseBody
	public String selectAmountMeetingCondition(MeetingCondition meetingCondition, HttpServletRequest request) throws Exception{
		System.out.println("..........CouponHandler..........selectAmountMeetingCondition()..........");
		String result = null;
		
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		meetingCondition.setQid(admin.getQid());
		int count = couponService.selectCountUserByMeetingCondition(meetingCondition);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("amount", count);
		result = jsonObject.toString();
		
		return result;
	}
	/**
	 * 通过qid查询现在的课程类别有几个，分别是哪些
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/getCategories")
	@ResponseBody
	public String getCategories(HttpServletRequest request) throws Exception{
		System.out.println("..........CouponHandler..........getCategories()..........");
		String result = null;
		
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		List<String> categories = couponService.selectCategoryByQID(admin.getQid());
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		jsonObject.addProperty("count", categories.size());
		if(categories!=null && !categories.isEmpty()){
			for(String category : categories){
				jsonArray.add(category);
			}
		}
		jsonObject.add("values", jsonArray);
		result = jsonObject.toString();
		
		return result;
	}
}
