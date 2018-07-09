package com.lamport.admin.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller, 进行Sorder信息的修改、查询
 * @author Lin Zhao, protector of Sherry
 *
 */
@Controller
public class SorderHandler {
	/**
	 * 通过id处理未核销的订单
	 * @return
	 */
	@RequestMapping(value="/admin/verifySorderByID")
	public String verifySorderByID(){
		System.out.println("..........SorderHandler..........verifySorderByID()..........");
		//TODO
		return"";
	}
	/**
	 * 通过id处理待处理的退款订单
	 * @return
	 */
	@RequestMapping(value="/admin/processRefundByID")
	public String processRefundByID(){
		System.out.println("..........SorderHandler..........processRefundByID()..........");
		//TODO
		return"";
	}
	/**
	 * 通过SorderQueryCondition查询Sorder信息
	 * @return
	 */
	@RequestMapping(value="/admin/selectSorderBySorderQueryCondition")
	public String selectSorderBySorderQueryCondition(){
		System.out.println("..........SorderHandler..........selectSorderBySorderQueryCondition()..........");
		//TODO
		return"";
	}
	/**
	 * 通过SorderQueryCondition查询未处理的Refund信息
	 * @return
	 */
	@RequestMapping(value="/admin/selectRefundBySorderQueryCondition")
	public String selectselectRefundBySorderQueryCondition(){
		System.out.println("..........SorderHandler..........selectRefundBySorderQueryCondition()..........");
		//TODO
		return"";
	}
	/**
	 * 通过SorderQueryCondition查询未核销的Sorder信息
	 * @return
	 */
	@RequestMapping(value="/admin/selectSorderUnverifiedBySorderQueryCondition")
	public String selectSorderUnverifiedBySorderQueryCondition(){
		System.out.println("..........SorderHandler..........selectSorderUnverifiedBySorderQueryCondition()..........");
		//TODO
		return"";
	}
}
