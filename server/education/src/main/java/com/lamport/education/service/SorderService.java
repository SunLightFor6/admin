package com.lamport.education.service;

import java.util.List;

import com.lamport.education.po.Point;
import com.lamport.education.po.Refund;
import com.lamport.education.po.Sorder;
import com.lamport.education.po.User;
import com.lamport.education.vo.SorderQueryCondition;
import com.lamport.education.vo.UIDAndStatus;

public interface SorderService {
	
	/**
	 * 支付尚未付款的订单
	 * @return 0 支付失败 1 支付成功 2 金融相关错误（actual、point计算出错， 优惠券不存在）
	 * @throws Exception
	 */
	public int updateSorderUnpaid(Sorder sorder, int recordid, Point pointObject, User user) throws Exception;
	
	/**
	 * 创建Sorder信息
	 * 如果之前有待付款记录（参数sorder的id不为0），则更新status字段为“已付款”；
	 * 如果之前没有待付款记录（参数sorder的id为0），则增添新的订单
	 * @param sorder
	 * @return 0 支付失败 1 支付成功 2 金融相关错误（actual、point计算出错， 优惠券不存在）
	 * @throws Exception
	 */
	public int saveSorder(Sorder sorder, int recordid, Point pointObject, User user) throws Exception; 
	
	/**
	 * 创建待付款的Sorder信息
	 * @param sorder
	 * @throws Exception
	 */
	public void saveSorderUnpaid(Sorder sorder) throws Exception;
	
	/**
	 * 创建Refund信息，同时修改Sorder的status
	 * （在订单处于“已付款”状态时，）申请退款，将订单状态置为“退款中”，并且添加一条退款记录（refund表）
	 * @param oid
	 * @throws Exception
	 */
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
	
	/**
	 * 通过oid查询Sorder信息
	 * @param oid
	 * @return Sorder
	 * @throws Exception
	 */
	public Sorder selectSorderByOID(int oid) throws Exception;
	
	/**
	 * 通过rid查询Sorder和Refund信息
	 * @param rid
	 * @return Sorder
	 * @throws Exception
	 */
	public Sorder selectRefundByRID(int rid) throws Exception;
	
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
