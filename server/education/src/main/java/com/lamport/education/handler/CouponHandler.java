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
import com.lamport.education.po.CouponRecord;
import com.lamport.education.po.User;
import com.lamport.education.service.CouponService;
import com.lamport.education.util.Config;
import com.lamport.education.vo.UIDAndPage;

/**
 * Controller, 进行Coupon(优惠券)信息的查询
 * @author Lin Zhao, protector of Sherry
 *
 */
@Controller
public class CouponHandler {
	
	@Autowired
	CouponService couponService;
	
	/**
	 * 通过uid和页码查询Coupon信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selectCouponByUIDAndPage")
	@ResponseBody
	public String selectCouponByUIDAndPage(UIDAndPage uidAndPage, HttpServletRequest request) throws Exception{
		System.out.println("..........CouponHanler..........selectCouponByUIDAndPage..........");
		String result = null;
		
		String nullString = null;
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		uidAndPage.setUid(user.getUid());
		uidAndPage.initPageBean(Config.CouponPageSize);
		List<CouponRecord> couponRecords = couponService.selectCouponRecordByUIDAndPage(uidAndPage);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		if(couponRecords!=null && !couponRecords.isEmpty()){
			for(CouponRecord couponRecord : couponRecords){
				JsonObject object = new JsonObject();
				object.addProperty("recordid", couponRecord.getId());
				if(couponRecord!=null && couponRecord.getCoupon()!=null){
					object.addProperty("category", couponRecord.getCoupon().getCategory());
					object.addProperty("needmoney", couponRecord.getCoupon().getNeedmoney());
					object.addProperty("money", couponRecord.getCoupon().getMoney());
					object.addProperty("deadline", couponRecord.getCoupon().getDeadline());
				}else{
					object.addProperty("category", nullString);
					object.addProperty("needmoney", nullString);
					object.addProperty("money", nullString);
					object.addProperty("deadline", nullString);
				}
				jsonArray.add(object);
			}
		}
		jsonObject.add("coupons", jsonArray);
		result = jsonObject.toString();
		
		return result;
	}
}
