package com.lamport.education.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.lamport.education.mapper.RefundMapper;
import com.lamport.education.mapper.SorderMapper;
import com.lamport.education.po.Refund;
import com.lamport.education.po.Sorder;
import com.lamport.education.service.SorderService;
import com.lamport.education.util.PageBean;
import com.lamport.education.vo.BusinessVo;
import com.lamport.education.vo.SorderInfoVo;
@Service
public class SorderServiceBean implements SorderService {
	@Autowired
	SorderMapper sorderMapper;
	@Autowired
	RefundMapper refundMapper;
	@Override
	public void saveObligation(Sorder sorder) throws Exception { 
		sorderMapper.saveSorder(sorder);//添加待付款记录
	}

	@Override
	public void savePaymentInfo(Sorder sorder) throws Exception { 
		//如果之前有待付款记录（参数sorder的id不为0），则更新status字段为“已付款”；如果之前没有待付款记录（参数sorder的id为0），则增添新的订单
		sorder.setStatus("已付款");
		if(sorder.getOid()==0) // 支付
		{
			sorderMapper.saveSorder(sorder);
		}else{
			sorderMapper.updatePaymentDetail(sorder);
		}
	}

	@Override
	public void saveRefundRequest(int oid) throws Exception {
		Sorder sorder = new Sorder();
		sorder.setOid(oid);
		sorder.setStatus("退款中");
		Refund refund = new Refund(); 
		refund.setOid(oid);
		refund.setUserdeletekey(0);
		sorderMapper.updateStatusOfSorderByOid(sorder); // 更新sorder状态
		refundMapper.saveRefund(refund); //  添加退款记录
	}

	@Override
	public void deleteObligationLogicallyByOid(int oid) throws Exception { 
		 //取消待付款，删除待付款记录，同时设置deletekey和userdeletekey
		sorderMapper.deleteObligationLogicallyByOid(oid);
	}

	@Override
	public void deleteFinalSorderLogicallyByOid(int oid) throws Exception { 
		//删除“已使用”的订单，只设置userdeletekey
		sorderMapper.deleteFinalSorderLogicallyByOid(oid);
	}

	@Override
	public void deleteRefundRequestLogicallyByOid(int oid) throws Exception { 
		//取消退款申请，删除退款记录，同时设置deletekey和userdeletekey，将订单状态置回“已付款”
		Sorder sorder = new Sorder();
		sorder.setOid(oid);
		sorder.setStatus("已付款");
		sorderMapper.updateStatusOfSorderByOid(sorder);
		refundMapper.deleteRefundRequestLogicallyByOid(oid);
	}

	@Override
	public void deleteFinalRefundLogicallyByOid(int oid) throws Exception { 
		//或者删除“已退款”的订单，只设置userdeletekey
		refundMapper.deleteFinalRefundLogicallyByOid(oid);
	}

	@Override
	public List<SorderInfoVo> selectSorderPageByUid(PageBean page, int uid) throws Exception {
		BusinessVo businessVo = new BusinessVo();
		businessVo.setPage(page);
		businessVo.setUid(uid);
		businessVo.setStatus("");
		return sorderMapper.selectSorderPageByUid(businessVo);
	}

	@Override
	public List<SorderInfoVo> selectSorderPageByUidAndStatus(PageBean page, int uid, String status) throws Exception {
		BusinessVo businessVo = new BusinessVo();
		businessVo.setPage(page);
		businessVo.setUid(uid);
		businessVo.setStatus(status);
		return sorderMapper.selectSorderPageByUidAndStatus(businessVo);
	}

	@Override
	public Sorder selectSorderByOid(int oid) throws Exception {
		return sorderMapper.selectSorderByOid(oid);	
	}

}
