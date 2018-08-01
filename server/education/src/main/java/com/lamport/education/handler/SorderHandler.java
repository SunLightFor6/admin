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
import com.lamport.education.po.Address;
import com.lamport.education.po.Lesson;
import com.lamport.education.po.Refund;
import com.lamport.education.po.Sorder;
import com.lamport.education.po.User;
import com.lamport.education.service.LessonService;
import com.lamport.education.service.SorderService;
import com.lamport.education.util.Config;
import com.lamport.education.vo.SorderQueryCondition;

@Controller
public class SorderHandler {
	
	@Autowired
	SorderService sorderService;
	@Autowired
	LessonService lessonService;
	
	/**
	 * 创建一条已付款的Sorder信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveSorderPaid")
	@ResponseBody
	public String saveSorderPaid(Sorder sorder, HttpServletRequest request) throws Exception{
		System.out.println("..........SorderHandler..........saveSorderPaid..........");
		String result = null;
		
		HttpSession session = request.getSession();
        int qid = Integer.parseInt((String)session.getAttribute("qid"));
		User user = (User) session.getAttribute("user");
		sorder.setQid(qid);
		sorder.setUid(user.getUid());
		sorder.setStatus(Config.SorderStatusPaid);
		//TODO 加优惠券/transactionId/		
		sorderService.saveSorder(sorder);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("state", 1);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 创建一条待付款的Sorder信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveSorderUnpaid")
	@ResponseBody
	public String saveSorderUnpaid(Sorder sorder, HttpServletRequest request) throws Exception{
		System.out.println("..........SorderHandler..........saveSorderUnpaid..........");
		String result = null;
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int qid = (Integer)session.getAttribute("qid");
		sorder.setQid(qid);
		sorder.setUid(user.getUid());
		sorder.setStatus(Config.SorderStatusUnpaid);
		//TODO actual等如何设置
		sorderService.saveSorder(sorder);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("state", 1);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 创建Refund信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveRefund")
	@ResponseBody
	public String saveRefund(Refund refund) throws Exception{
		System.out.println("..........SorderHandler..........saveRefund..........");
		String result = null;
		
		Sorder sorder = new Sorder();
		sorder.setOid(refund.getOid());
		sorder.setStatus(Config.SorderStatusRefunding);
		refund.setStatus(Config.RefundStatusUnprocessed);
		sorderService.saveRefund(sorder, refund);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("state", 1);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 取消未付款的订单
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cancelUnpaidSorder")
	@ResponseBody
	public String cancelUnpaidSorder(Sorder sorder, HttpServletRequest request) throws Exception{
		System.out.println("..........SorderHandler..........cancelUnpaidSorder..........");
		String result = null;

		sorderService.deleteSorderPowerfullyByOID(sorder);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("state", 1);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 取消未处理的退款
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cancelRefundUnprocessed")
	@ResponseBody
	public String cancelRefundUnprocessed(Refund refund, HttpServletRequest request) throws Exception{
		System.out.println("..........SorderHandler..........cancelRefundUnprocessed..........");
		String result = null;
		
		Sorder sorder = new Sorder();
		sorder.setOid(refund.getOid());
		sorder.setStatus(Config.SorderStatusPaid);
		sorderService.deleteRefundPowerfullyByOID(refund, sorder);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("state", 1);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 删除已核销的Sorder信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteSorderCAV")
	@ResponseBody
	public String deleteSorderCAV(Sorder sorder) throws Exception{
		System.out.println("..........SorderHandler..........deleteSorderCAV..........");
		String result = null;
		
		sorderService.deleteSorderLogicallyByOID(sorder);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("state", 1);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 删除已退款的Sorder信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteSorderRefunded")
	@ResponseBody
	public String deleteSorderRefunded(Sorder sorder) throws Exception{
		System.out.println("..........SorderHandler..........deleteSorderRefunded..........");
		String result = null;
		
		Refund refund = new Refund();
		refund.setOid(sorder.getOid());
		sorderService.deleteRefundLogicallyByOID(refund, sorder);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("state", 1);
		result = jsonObject.toString();
		
		return result;
	}

	/**
	 * 对未付款的订单进行付款
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/paySorderUnpaid")
	@ResponseBody
	public String paySorderUnpaid(Sorder sorder, HttpServletRequest request) throws Exception{
		System.out.println("..........SorderHandler..........paySorderUnpaid..........");
		String result = null;
		
		HttpSession session = request.getSession();
		int qid = (Integer)session.getAttribute("qid");
		sorder.setQid(qid);
		sorder.setStatus(Config.SorderStatusPaid);
		sorderService.updateSorderByOID(sorder);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("state", 1);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 通过多条件查询Sorder信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectSorderBySorderQueryCondition")
	@ResponseBody
	public String selectSorderBySorderQueryCondition(SorderQueryCondition sorderQueryCondition, HttpServletRequest request) throws Exception{
		System.out.println("..........SorderHandler..........selectSorderBySorderQueryCondition..........");
		String result = null;
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		sorderQueryCondition.setUid(user.getUid());
		sorderQueryCondition.initPageBean(Config.SorderPageSize);
		List<Sorder> sorders = sorderService.selectSorderBySorderQueryCondition(sorderQueryCondition);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		if(sorders!=null && !sorders.isEmpty()){
			for(Sorder sorder : sorders){
				JsonObject object = new JsonObject();
				object.addProperty("oid", sorder.getOid());
				object.addProperty("status", sorder.getStatus());
				object.addProperty("total", sorder.getActual());
				object.addProperty("actual", sorder.getActual());
				object.addProperty("ordertime", sorder.getOrdertime());
				object.addProperty("lname", sorder.getLesson().getLname());
				object.addProperty("imgurl", sorder.getLesson().getImgurl());
				jsonArray.add(object);
			}
		}
		jsonObject.add("sorders", jsonArray);
		result = jsonObject.toString();

		return result;
	}

	/**
	 * 通过多条件查询待付款的Sorder信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectSorderUnpaidBySorderQueryCondition")
	@ResponseBody
	public String selectSorderUnpaidBySorderQueryCondition(SorderQueryCondition sorderQueryCondition, HttpServletRequest request) throws Exception{
		System.out.println("..........SorderHandler..........selectSorderUnpaidBySorderQueryCondition..........");
		String result = null;
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		sorderQueryCondition.setUid(user.getUid());
		sorderQueryCondition.setStatus(Config.SorderStatusUnpaid);
		sorderQueryCondition.initPageBean(Config.SorderPageSize);
		List<Sorder> sorders = sorderService.selectSorderBySorderQueryCondition(sorderQueryCondition);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		if(sorders!=null && !sorders.isEmpty()){
			for(Sorder sorder : sorders){
				JsonObject object = new JsonObject();
				object.addProperty("oid", sorder.getOid());
				object.addProperty("status", sorder.getStatus());
				object.addProperty("total", sorder.getActual());
				object.addProperty("actual", sorder.getActual());
				object.addProperty("ordertime", sorder.getOrdertime());
				object.addProperty("lname", sorder.getLesson().getLname());
				object.addProperty("imgurl", sorder.getLesson().getImgurl());
				jsonArray.add(object);
			}
		}
		jsonObject.add("sorders", jsonArray);
		result = jsonObject.toString();

		return result;
	}
	
	/**
	 * 通过多条件查询已付款的Sorder信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectSorderPaidBySorderQueryCondition")
	@ResponseBody
	public String selectSorderPaidBySorderQueryCondition(SorderQueryCondition sorderQueryCondition, HttpServletRequest request) throws Exception{
		System.out.println("..........SorderHandler..........selectSorderPaidBySorderQueryCondition..........");
		String result = null;
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		sorderQueryCondition.setUid(user.getUid());
		sorderQueryCondition.setStatus(Config.SorderStatusPaid);
		sorderQueryCondition.initPageBean(Config.SorderPageSize);
		List<Sorder> sorders = sorderService.selectSorderBySorderQueryCondition(sorderQueryCondition);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		if(sorders!=null && !sorders.isEmpty()){
			for(Sorder sorder : sorders){
				JsonObject object = new JsonObject();
				object.addProperty("oid", sorder.getOid());
				object.addProperty("status", sorder.getStatus());
				object.addProperty("total", sorder.getActual());
				object.addProperty("actual", sorder.getActual());
				object.addProperty("ordertime", sorder.getOrdertime());
				object.addProperty("lname", sorder.getLesson().getLname());
				object.addProperty("imgurl", sorder.getLesson().getImgurl());
				jsonArray.add(object);
			}
		}
		jsonObject.add("sorders", jsonArray);
		result = jsonObject.toString();

		return result;
	}
	
	/**
	 * 通过多条件查询已核销的Sorder信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectSorderCAVBySorderQueryCondition")
	@ResponseBody
	public String selectSorderCAVBySorderQueryCondition(SorderQueryCondition sorderQueryCondition, HttpServletRequest request) throws Exception{
		System.out.println("..........SorderHandler..........selectSorderCAVBySorderQueryCondition..........");
		String result = null;
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		sorderQueryCondition.setUid(user.getUid());
		sorderQueryCondition.setStatus(Config.SorderStatusCAV);
		sorderQueryCondition.initPageBean(Config.SorderPageSize);
		List<Sorder> sorders = sorderService.selectSorderBySorderQueryCondition(sorderQueryCondition);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		if(sorders!=null && !sorders.isEmpty()){
			for(Sorder sorder : sorders){
				JsonObject object = new JsonObject();
				object.addProperty("oid", sorder.getOid());
				object.addProperty("status", sorder.getStatus());
				object.addProperty("total", sorder.getActual());
				object.addProperty("actual", sorder.getActual());
				object.addProperty("ordertime", sorder.getOrdertime());
				object.addProperty("lname", sorder.getLesson().getLname());
				object.addProperty("imgurl", sorder.getLesson().getImgurl());
				jsonArray.add(object);
			}
		}
		jsonObject.add("sorders", jsonArray);
		result = jsonObject.toString();

		return result;
	}
	
	/**
	 * 通过多条件查询退款中/已退款的Sorder信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectRefundBySorderQueryCondition")
	@ResponseBody
	public String selectRefundBySorderQueryCondition(SorderQueryCondition sorderQueryCondition, HttpServletRequest request) throws Exception{
		System.out.println("..........SorderHandler..........selectRefundBySorderQueryCondition..........");
		String result = null;
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		sorderQueryCondition.setUid(user.getUid());
		sorderQueryCondition.setStatus("退款");						/*####################*/
		sorderQueryCondition.initPageBean(Config.SorderPageSize);
		List<Sorder> sorders = sorderService.selectSorderBySorderQueryCondition(sorderQueryCondition);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		if(sorders!=null && !sorders.isEmpty()){
			for(Sorder sorder : sorders){
				JsonObject object = new JsonObject();
				object.addProperty("oid", sorder.getOid());
				object.addProperty("status", sorder.getStatus());
				object.addProperty("total", sorder.getActual());
				object.addProperty("actual", sorder.getActual());
				object.addProperty("ordertime", sorder.getOrdertime());
				object.addProperty("lname", sorder.getLesson().getLname());
				object.addProperty("imgurl", sorder.getLesson().getImgurl());
				jsonArray.add(object);
			}
		}
		jsonObject.add("sorders", jsonArray);
		result = jsonObject.toString();

		return result;
	}
	
	/**
	 * 通过oid查询退款所需信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="selectRefundInfoByOID")
	@ResponseBody
	public String selectRefundInfoByOID(int oid) throws Exception{
		System.out.println("..........SorderHandler..........selectRefundBySorderQueryCondition..........");
		String result = null;
		
		Sorder sorder = sorderService.selectSorderByOid(oid);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("oid", oid);
		if(sorder!=null && sorder.getLesson()!=null){
			jsonObject.addProperty("lname", sorder.getLesson().getLname());
		}else{
			jsonObject.addProperty("lname", "");
		}
		
		jsonObject.addProperty("actual", sorder.getActual());
		result = jsonObject.toString();
		
		return result;
	}
	
	@RequestMapping(value="/selectUnpaidSorderInfoByOID")
	@ResponseBody
	public String selectUnpaidSorderInfoByOID(int oid, HttpServletRequest request) throws Exception{
		System.out.println("..........SorderHandler..........selectUnpaidSorderInfoByOID..........");
		String result = null;
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		Lesson lesson = lessonService.selectLessonByOid(oid);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		jsonObject.addProperty("oid", oid);
		jsonObject.addProperty("lname", lesson.getLname());
		jsonObject.addProperty("lprice", lesson.getLprice());
		jsonObject.addProperty("username", user.getUsername());
		jsonObject.addProperty("tel", user.getTel());
		/*优惠券、积分……*/
		if(lesson.getBranches()!=null && !lesson.getBranches().isEmpty()){
			for(Address address : lesson.getBranches()){
				JsonObject object = new JsonObject();
				object.addProperty("branch", address.getBranch());
				jsonArray.add(object);
			}
		}
		jsonObject.add("branches", jsonArray);
		result = jsonObject.toString();

		return result;
	}
}
