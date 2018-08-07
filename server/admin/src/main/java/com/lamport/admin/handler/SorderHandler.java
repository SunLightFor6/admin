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
import com.lamport.admin.po.Refund;
import com.lamport.admin.po.Sorder;
import com.lamport.admin.service.SorderService;
import com.lamport.admin.tool.Const;
import com.lamport.admin.vo.SorderQueryCondition;

/**
 * Controller, 进行Sorder信息的修改、查询
 * @author Lin Zhao, protector of Sherry
 *
 */
@Controller
public class SorderHandler {
	
	@Autowired
	private SorderService sorderService;
	
	/**
	 * 通过id处理未核销的订单
	 * @return
	 */
	@RequestMapping(value="/admin/verifySorderByID")
	@ResponseBody
	public String verifySorderByID(int oid) throws Exception{
		System.out.println("..........SorderHandler..........verifySorderByID()..........oid = " + oid);
		String result = null;
		
		Sorder sorder = new Sorder();
		sorder.setOid(oid);
		sorder.setStatus(Const.SorderStatusCAV);
		int updateResult = sorderService.updateSorderByID(sorder);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("response", updateResult);
		result = jsonObject.toString();
		
		return result;
	}
	/**
	 * 通过id处理待处理的退款订单
	 * @return
	 */
	@RequestMapping(value="/admin/processRefundByID")
	@ResponseBody
	public String processRefundByID(int oid) throws Exception{
		System.out.println("..........SorderHandler..........processRefundByID()..........oid = " + oid);
		String result = null;
		
		Sorder sorder = new Sorder();
		sorder.setOid(oid);
		sorder.setRefund(new Refund());
		sorder.getRefund().setOid(oid);
		sorder.setStatus(Const.SorderStatusRefunded);
		sorder.getRefund().setStatus(Const.RefundStatusProcessed);
		int updateResult = sorderService.updateRefundByID(sorder);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("response", updateResult);
		result = jsonObject.toString();
		
		return result;
	}
	/**
	 * 通过SorderQueryCondition查询Sorder信息
	 * @return
	 */
	@RequestMapping(value="/admin/selectSorderBySorderQueryCondition")
	@ResponseBody
	public String selectSorderBySorderQueryCondition(SorderQueryCondition sorderQueryCondition, HttpServletRequest request) throws Exception{
		System.out.println("..........SorderHandler..........selectSorderBySorderQueryCondition()..........sorderQueryCondition:" +sorderQueryCondition.getOid()+"\t" + sorderQueryCondition.getStatus() + "\t" + sorderQueryCondition.getBeginTime() + "\t" + sorderQueryCondition.getEndTime());
		String result = null;
		
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		sorderQueryCondition.setQid(admin.getQid());
		sorderQueryCondition.setPageTool();
		
		List<Sorder> sorders = sorderService.selectSorderBySorderQueryCondition(sorderQueryCondition);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("code", 0);
		jsonObject.addProperty("msg", "");
		jsonObject.addProperty("count", sorderQueryCondition.getPageTool().getCount());
		JsonArray jsonArray = new JsonArray();
		for(Sorder sorder : sorders){
			JsonObject object = new JsonObject();
			object.addProperty("courseid", sorder.getLid());
			object.addProperty("orderid", sorder.getOid());
			object.addProperty("usernickname", sorder.getUsername());
			object.addProperty("usertel", sorder.getTel());
			object.addProperty("total", sorder.getTotal());
			object.addProperty("actual", sorder.getActual());
			object.addProperty("orderstatus", sorder.getStatus());
			object.addProperty("ordertime", sorder.getOrdertime());
			object.addProperty("transactionid", sorder.getTransactionid());
			jsonArray.add(object);
		}
		jsonObject.add("data", jsonArray);
		result = jsonObject.toString();
		
		return result;
	}
	/**
	 * 通过SorderQueryCondition查询未处理的Refund信息
	 * @return
	 */
	@RequestMapping(value="/admin/selectRefundBySorderQueryCondition")
	@ResponseBody
	public String selectRefundBySorderQueryCondition(SorderQueryCondition sorderQueryCondition, HttpServletRequest request) throws Exception{
		System.out.println("..........SorderHandler..........selectRefundBySorderQueryCondition()..........sorderQueryCondition:" + sorderQueryCondition.getStatus() + " " + sorderQueryCondition.getBeginTime() + " " + sorderQueryCondition.getEndTime());
		String result = null;

		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		sorderQueryCondition.setQid(admin.getQid());
		sorderQueryCondition.setStatus(Const.SorderStatusRefunding);
		sorderQueryCondition.setPageTool();
		List<Sorder> sorders = sorderService.selectSorderBySorderQueryCondition(sorderQueryCondition);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("code", 0);
		jsonObject.addProperty("msg", "");
		jsonObject.addProperty("count", sorderQueryCondition.getPageTool().getCount());
		JsonArray jsonArray = new JsonArray();
		for(Sorder sorder : sorders){
			JsonObject object = new JsonObject();
			object.addProperty("courseid", sorder.getOid());
			object.addProperty("orderid", sorder.getOid());
			object.addProperty("usernickname", sorder.getUsername());
			object.addProperty("usertel", sorder.getTel());
			object.addProperty("total", sorder.getTotal());
			object.addProperty("actual", sorder.getActual());
			object.addProperty("orderstatus", sorder.getStatus());
			object.addProperty("ordertime", sorder.getOrdertime());
			object.addProperty("transactionid", sorder.getTransactionid());
			jsonArray.add(object);
		}
		jsonObject.add("data", jsonArray);
		result = jsonObject.toString();
		
		return result;
	}
	/**
	 * 通过SorderQueryCondition查询未核销的Sorder信息
	 * @return
	 */
	@RequestMapping(value="/admin/selectSorderUnverifiedBySorderQueryCondition")
	@ResponseBody
	public String selectSorderUnverifiedBySorderQueryCondition(SorderQueryCondition sorderQueryCondition, HttpServletRequest request) throws Exception{
		System.out.println("..........SorderHandler..........selectSorderUnverifiedBySorderQueryCondition()..........sorderQueryCondition:" + sorderQueryCondition.getStatus() + " " + sorderQueryCondition.getBeginTime() + " " + sorderQueryCondition.getEndTime());
		String result = null;
		
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		sorderQueryCondition.setQid(admin.getQid());
		sorderQueryCondition.setStatus(Const.SorderStatusPayed);
		sorderQueryCondition.setPageTool();
		List<Sorder> sorders = sorderService.selectSorderBySorderQueryCondition(sorderQueryCondition);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("code", 0);
		jsonObject.addProperty("msg", "");
		jsonObject.addProperty("count", sorderQueryCondition.getPageTool().getCount());
		JsonArray jsonArray = new JsonArray();
		for(Sorder sorder : sorders){
			JsonObject object = new JsonObject();
			object.addProperty("courseid", sorder.getOid());
			object.addProperty("orderid", sorder.getOid());
			object.addProperty("usernickname", sorder.getUsername());
			object.addProperty("usertel", sorder.getTel());
			object.addProperty("total", sorder.getTotal());
			object.addProperty("actual", sorder.getActual());
			object.addProperty("orderstatus", sorder.getStatus());
			object.addProperty("ordertime", sorder.getOrdertime());
			object.addProperty("transactionid", sorder.getTransactionid());
			jsonArray.add(object);
		}
		jsonObject.add("data", jsonArray);
		result = jsonObject.toString();
		
		return result;
	}
}
