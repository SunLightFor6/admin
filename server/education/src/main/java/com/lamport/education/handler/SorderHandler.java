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
import com.lamport.education.mapper.LessonMapper;
import com.lamport.education.po.Refund;
import com.lamport.education.po.Sorder;
import com.lamport.education.po.User;
import com.lamport.education.service.SorderService;
import com.lamport.education.util.Config;
import com.lamport.education.vo.SorderQueryCondition;

@Controller
public class SorderHandler {
	
	@Autowired
	SorderService sorderService;
	@Autowired
	LessonMapper lessonMapper;
	
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
		sorder.setStatus(Config.SorderStatusUnPaid);
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
	 * 对未付款的订单进行付款
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/paySorderUnpaid")
	@ResponseBody
	public String paySorderUnpaid(Sorder sorder) throws Exception{
		System.out.println("..........SorderHandler..........paySorderUnpaid..........");
		String result = null;
		
		sorder.setStatus(Config.SorderStatusPaid);
		sorderService.updateSorderStatusByOID(sorder);
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
	@RequestMapping("/selectOrdersByUidAndStatus")
	@ResponseBody
	public String selectOrdersByUidAndStatus(SorderQueryCondition sorderQueryCondition, HttpServletRequest request) throws Exception{
		System.out.println("........SorderHandler........selectOrdersByUidAndStatus........");
		String result = null;
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int uid = user.getUid();		
		System.out.println("uid"+uid);						/*####################*/
//		int rowId = Integer.parseInt(request.getParameter("rowId"));
		sorderQueryCondition.initPageBean(Config.SorderPageSize);
		List<Sorder> sorders = sorderService.selectSorderBySorderQueryCondition(sorderQueryCondition);
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
		if(sorders!=null && !sorders.isEmpty()){
			for(Sorder sorder : sorders){
				JsonObject object = new JsonObject();
				object.addProperty("oid", sorder.getOid());
				object.addProperty("lid", sorder.getLid());
				object.addProperty("total", sorder.getActual());
				object.addProperty("ordertime", sorder.getOrdertime());
				object.addProperty("status", sorder.getStatus());
				object.addProperty("lname", sorder.getLesson().getLname());
				object.addProperty("imgurl", sorder.getLesson().getImgurl());
				jsonArray.add(object);
			}
		}
		jsonObject.add("sorders", jsonArray);
		result = jsonObject.toString();
		
		return result;
	}
}
