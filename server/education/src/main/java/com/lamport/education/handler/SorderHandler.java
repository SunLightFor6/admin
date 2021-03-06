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
import com.lamport.education.po.Enterprise;
import com.lamport.education.po.Lesson;
import com.lamport.education.po.Point;
import com.lamport.education.po.Refund;
import com.lamport.education.po.Sorder;
import com.lamport.education.po.User;
import com.lamport.education.service.EnterpriseService;
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
	@Autowired
	EnterpriseService enterpriseService;
	
	/**
	 * 创建一条已付款的Sorder信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveSorderPaid")
	@ResponseBody
	public String saveSorderPaid(Sorder sorder, int recordid, int point, HttpServletRequest request) throws Exception{
		System.out.println("..........SorderHandler..........saveSorderPaid..........");
		String result = null;
		
		HttpSession session = request.getSession();
        int qid = Integer.parseInt((String)session.getAttribute("qid"));
		User user = (User) session.getAttribute("user");
		sorder.setQid(qid);
		sorder.setUid(user.getUid());
		sorder.setStatus(Config.SorderStatusPaid);
		Point pointObject = new Point();
		pointObject.setPoint(point);
		int saveResult = sorderService.saveSorder(sorder, recordid, pointObject, user);
		if(saveResult==1 && pointObject.getPoint()!=0){
			//如果保存成功且用户总积分发生了变化
			user.setTotalpoint(user.getTotalpoint()+pointObject.getPoint());
			session.setAttribute("user", user);
		}
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("state", saveResult);
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
		sorderService.saveSorderUnpaid(sorder);
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
	public String paySorderUnpaid(Sorder sorder, int recordid, int point, HttpServletRequest request) throws Exception{
		System.out.println("..........SorderHandler..........paySorderUnpaid..........");
		String result = null;
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		sorder.setStatus(Config.SorderStatusPaid);
		Point pointObject = new Point();
		pointObject.setPoint(point);
		int payResult = sorderService.updateSorderUnpaid(sorder, recordid, pointObject, user);
		if(payResult==1 && pointObject.getPoint()!=0){
			//如果保存成功且用户总积分发生了变化
			user.setTotalpoint(user.getTotalpoint()+pointObject.getPoint());
			session.setAttribute("user", user);
		}
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
//		List<Sorder> sorders = sorderService.selectSorderBySorderQueryCondition(sorderQueryCondition);
		List<Sorder> refunds = sorderService.selectRefudBySorderQueryCondition(sorderQueryCondition);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		if(refunds!=null && !refunds.isEmpty()){
			for(Sorder sorder : refunds){
				JsonObject object = new JsonObject();
				object.addProperty("rid", sorder.getRefund().getRid());
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
		
		Sorder sorder = sorderService.selectSorderByOID(oid);
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
		Enterprise enterprise = enterpriseService.selectEnterpriseByQid(user.getQid());
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		jsonObject.addProperty("oid", oid);
		jsonObject.addProperty("lid", lesson.getLid());
		jsonObject.addProperty("lname", lesson.getLname());
		jsonObject.addProperty("lprice", lesson.getLprice());
		jsonObject.addProperty("category", lesson.getCategory());
		jsonObject.addProperty("totalpoint", user.getTotalpoint());
		jsonObject.addProperty("username", user.getUsername());
		jsonObject.addProperty("tel", user.getTel());
		jsonObject.addProperty("discountrate", enterprise.getDiscountrate());
		jsonObject.addProperty("perpointtomoney", enterprise.getPerpointtomoney());
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
	
/*--------------------------------------------------2018.08.02 10:50--------------------------------------------------*/
	
	/**
	 * 通过oid查询Sorder信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selectSorderByOID")
	@ResponseBody
	public String selectSorderByOID(int oid) throws Exception{
		System.out.println("..........SorderHandler..........selectSorderByOID..........");
		String result = null;
		
		String nullString = null;
		Sorder sorder = sorderService.selectSorderByOID(oid);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("oid", sorder.getOid());
		if(sorder!=null && sorder.getLesson()!=null){
			jsonObject.addProperty("lname", sorder.getLesson().getLname());
			jsonObject.addProperty("imgurl", sorder.getLesson().getImgurl());
		}else{
			jsonObject.addProperty("lname", nullString);
			jsonObject.addProperty("imgurl", nullString);
		}
		
		jsonObject.addProperty("branch", sorder.getBranch());/**/
		jsonObject.addProperty("total", sorder.getTotal());
		jsonObject.addProperty("actual", sorder.getActual());
		jsonObject.addProperty("status", sorder.getStatus());
		jsonObject.addProperty("ordertime", sorder.getOrdertime());
		jsonObject.addProperty("username", sorder.getUsername());
		jsonObject.addProperty("tel", sorder.getTel());
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 通过rid查询Refud信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selectRefundByRID")
	@ResponseBody
	public String selectRefundByRID(int rid) throws Exception{
		System.out.println("..........SorderHandler..........selectRefundByRID..........");
		String result = null;
		
		String nullString = null;
		Sorder sorder = sorderService.selectRefundByRID(rid);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("oid", sorder.getOid());
		jsonObject.addProperty("branch", sorder.getBranch());
		jsonObject.addProperty("total", sorder.getTotal());
		jsonObject.addProperty("actual", sorder.getActual());
		jsonObject.addProperty("status", sorder.getStatus());
		jsonObject.addProperty("ordertime", sorder.getOrdertime());
		jsonObject.addProperty("username", sorder.getUsername());
		jsonObject.addProperty("tel", sorder.getTel());
		if(sorder!=null && sorder.getRefund()!=null){
			jsonObject.addProperty("rid", sorder.getRefund().getRid());
			jsonObject.addProperty("refundreason", sorder.getRefund().getRefundreason());
			jsonObject.addProperty("refundtime", sorder.getRefund().getRefundtime());
			jsonObject.addProperty("refundstatus", sorder.getRefund().getStatus());
		}else{
			jsonObject.addProperty("rid", nullString);
			jsonObject.addProperty("refundreason", nullString);
			jsonObject.addProperty("refundtime", nullString);
			jsonObject.addProperty("refundstatus", nullString);
		}
		if(sorder!=null && sorder.getLesson()!=null){
			jsonObject.addProperty("lname", sorder.getLesson().getLname());
			jsonObject.addProperty("imgurl", sorder.getLesson().getImgurl());
		}else{
			jsonObject.addProperty("lname", nullString);
			jsonObject.addProperty("imgurl", nullString);
		}
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 通过oid查询Refud信息
	 * @param oid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selectRefundByOID")
	@ResponseBody
	public String selectRefundByOID(int oid) throws Exception{
		System.out.println("..........SorderHandler..........selectRefundByOID..........");
		String result = null;
		
		String nullString = null;
		Sorder sorder = sorderService.selectRefundByOID(oid);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("oid", sorder.getOid());
		jsonObject.addProperty("branch", sorder.getBranch());
		jsonObject.addProperty("total", sorder.getTotal());
		jsonObject.addProperty("actual", sorder.getActual());
		jsonObject.addProperty("status", sorder.getStatus());
		jsonObject.addProperty("ordertime", sorder.getOrdertime());
		jsonObject.addProperty("username", sorder.getUsername());
		jsonObject.addProperty("tel", sorder.getTel());
		if(sorder!=null && sorder.getRefund()!=null){
			jsonObject.addProperty("rid", sorder.getRefund().getRid());
			jsonObject.addProperty("refundreason", sorder.getRefund().getRefundreason());
			jsonObject.addProperty("refundtime", sorder.getRefund().getRefundtime());
			jsonObject.addProperty("refundstatus", sorder.getRefund().getStatus());
		}else{
			jsonObject.addProperty("rid", nullString);
			jsonObject.addProperty("refundreason", nullString);
			jsonObject.addProperty("refundtime", nullString);
			jsonObject.addProperty("refundstatus", nullString);
		}
		if(sorder!=null && sorder.getLesson()!=null){
			jsonObject.addProperty("lname", sorder.getLesson().getLname());
			jsonObject.addProperty("imgurl", sorder.getLesson().getImgurl());
		}else{
			jsonObject.addProperty("lname", nullString);
			jsonObject.addProperty("imgurl", nullString);
		}
		result = jsonObject.toString();
		
		return result;
	}
}
