package com.lamport.education.service;

import java.util.List;

import com.lamport.education.po.Refund;
import com.lamport.education.po.Sorder;
import com.lamport.education.util.PageBean;
import com.lamport.education.vo.SorderInfoVo;

public interface SorderService {
	
	public void saveObligation(Sorder sorder) throws Exception; //添加待付款记录
	public void savePaymentInfo(Sorder sorder) throws Exception; //如果之前有待付款记录（参数sorder的id不为0），则更新status字段为“已付款”；如果之前没有待付款记录（参数sorder的id为0），则增添新的订单
	public void saveRefundRequest(int oid) throws Exception; //（在订单处于“已付款”状态时，）申请退款，将订单状态置为“退款中”，并且添加一条退款记录（refund表）
	public void deleteObligationLogicallyByOid(int oid) throws Exception; //取消待付款，删除待付款记录，同时设置deletekey和userdeletekey
	public void deleteFinalSorderLogicallyByOid(int oid) throws Exception; //删除“已使用”的订单，只设置userdeletekey
	public void deleteRefundRequestLogicallyByOid(int oid) throws Exception; //取消退款申请，删除退款记录，同时设置deletekey和userdeletekey，将订单状态置回“已付款”
	public void deleteFinalRefundLogicallyByOid(int oid) throws Exception; //或者删除“已退款”的订单，只设置userdeletekey
	public Sorder selectSorderByOid(int oid)throws Exception;
	public List<SorderInfoVo> selectSorderPageByUid(PageBean page, int uid) throws Exception;
	public List<SorderInfoVo> selectSorderPageByUidAndStatus(PageBean page, int uid, String status) throws Exception;
	
}
