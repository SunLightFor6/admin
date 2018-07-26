package com.lamport.education.mapper;

import com.lamport.education.po.Refund;

public interface RefundMapper {
	/**
	 * 创建Refund信息
	 * @param refund
	 * @throws Exception
	 */
	public void saveRefund(Refund refund) throws Exception;
	
	/**
	 * 通过oid逻辑删除Refund（只更改userdeletekey）
	 * @param oid
	 * @throws Exception
	 */
	public void deleteRefundLogicallyByOID(Refund refund) throws Exception;
	
	/**
	 * 通过oid逻辑删除Refund（同时更改deletekey和userdeletekey）
	 * @param oid
	 * @throws Exception
	 */
	public void deleteRefundPowerfullyByOID(Refund refund) throws Exception;
}
