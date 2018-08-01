package com.lamport.education.service;

import java.util.List;

import com.lamport.education.po.Refund;
import com.lamport.education.po.Sorder;
import com.lamport.education.vo.SorderQueryCondition;
import com.lamport.education.vo.UIDAndStatus;

public interface SorderService {
	/**
	 * 创建Sorder信息
	 * @param sorder
	 * @throws Exception
	 */
	//如果之前有待付款记录（参数sorder的id不为0），则更新status字段为“已付款”；
	//如果之前没有待付款记录（参数sorder的id为0），则增添新的订单
	public void saveSorder(Sorder sorder) throws Exception; 
	
	/**
	 * 创建Refund信息，同时修改Sorder的status
	 * @param oid
	 * @throws Exception
	 */
	//（在订单处于“已付款”状态时，）申请退款，将订单状态置为“退款中”，并且添加一条退款记录（refund表）
	public void saveRefund(Sorder sorder, Refund refund) throws Exception; 
	
	/**
	 * 通过oid逻辑删除sorder（只更改userdeletekey）
	 * @param oid
	 * @throws Exception
	 */
	public void deleteSorderLogicallyByOID(Sorder sorder) throws Exception;
	
	/**
	 * 通过oid逻辑删除sorder（同时更改deletekey和userdeletekey）
	 * @param sorder
	 * @throws Exception
	 */
	public void deleteSorderPowerfullyByOID(Sorder sorder) throws Exception;
	
	/**
	 * 通过oid逻辑删除Refund（只更改userdeletekey）
	 * @param refund
	 * @param sorder
	 * @throws Exception
	 */
	public void deleteRefundLogicallyByOID(Refund refund, Sorder sorder) throws Exception;
	
	/**
	 * 通过oid逻辑删除Refund（同时更改deletekey和userdeletekey）
	 * @param refund
	 * @param sorder
	 * @throws Exception
	 */
	public void deleteRefundPowerfullyByOID(Refund refund, Sorder sorder) throws Exception;
	
	/**
	 * 通过oid更新Sorder信息
	 * @param sorder
	 * @throws Exception
	 */
	public void updateSorderByOID(Sorder sorder) throws Exception;

	/**
	 * 通过oid更新Sorder的状态
	 * @param sorder
	 * @throws Exception
	 */
	public void updateSorderStatusByOID(Sorder sorder) throws Exception;
	
	public Sorder selectSorderByOid(int oid) throws Exception;
	
	/**
	 * 通过多条件查询Sorder信息
	 * @param sorderQueryCondition
	 * @return List<Sorder>
	 * @throws Exception
	 */
	public List<Sorder> selectSorderBySorderQueryCondition(SorderQueryCondition sorderQueryCondition) throws Exception;

	/**
	 * 通过uid和status查询Soder的总数
	 * @param uidAndStatus
	 * @return int
	 * @throws Exception
	 */
	public int selectCountSorderByUIDAndStatus(UIDAndStatus uidAndStatus) throws Exception;
}
