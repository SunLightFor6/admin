package com.lamport.education.mapper;

import com.lamport.education.po.Refund;

public interface RefundMapper {
	
	public void saveRefund(Refund refund) throws Exception;
	public void deleteRefundRequestLogicallyByOid(int oid) throws Exception; //取消退款申请，删除退款记录，同时设置deletekey和userdeletekey，将订单状态置回“已付款”
	public void deleteFinalRefundLogicallyByOid(int oid) throws Exception; //或者删除“已退款”的订单，只设置userdeletekey

}
