package com.lamport.admin.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lamport.admin.service.SorderService;

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
	public String verifySorderByID() throws Exception{
		System.out.println("..........SorderHandler..........verifySorderByID()..........");
		String result = null;
		//TODO
		return result;
	}
	/**
	 * 通过id处理待处理的退款订单
	 * @return
	 */
	@RequestMapping(value="/admin/processRefundByID")
	@ResponseBody
	public String processRefundByID() throws Exception{
		System.out.println("..........SorderHandler..........processRefundByID()..........");
		String result = null;
		//TODO
		return result;
	}
	/**
	 * 通过SorderQueryCondition查询Sorder信息
	 * @return
	 */
	@RequestMapping(value="/admin/selectSorderBySorderQueryCondition")
	@ResponseBody
	public String selectSorderBySorderQueryCondition() throws Exception{
		System.out.println("..........SorderHandler..........selectSorderBySorderQueryCondition()..........");
		String result = null;
		//TODO
		return result;
	}
	/**
	 * 通过SorderQueryCondition查询未处理的Refund信息
	 * @return
	 */
	@RequestMapping(value="/admin/selectRefundBySorderQueryCondition")
	@ResponseBody
	public String selectselectRefundBySorderQueryCondition() throws Exception{
		System.out.println("..........SorderHandler..........selectRefundBySorderQueryCondition()..........");
		String result = null;
		//TODO
		return result;
	}
	/**
	 * 通过SorderQueryCondition查询未核销的Sorder信息
	 * @return
	 */
	@RequestMapping(value="/admin/selectSorderUnverifiedBySorderQueryCondition")
	@ResponseBody
	public String selectSorderUnverifiedBySorderQueryCondition() throws Exception{
		System.out.println("..........SorderHandler..........selectSorderUnverifiedBySorderQueryCondition()..........");
		String result = null;
		//TODO
		return result;
	}
}
