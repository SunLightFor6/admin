package com.lamport.education.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamport.education.mapper.RefundMapper;
import com.lamport.education.mapper.SorderMapper;
import com.lamport.education.po.Refund;
import com.lamport.education.po.Sorder;
import com.lamport.education.service.SorderService;
import com.lamport.education.util.Config;
import com.lamport.education.vo.SorderQueryCondition;

@Service
public class SorderServiceBean implements SorderService {

	@Autowired
	SorderMapper sorderMapper;	
	@Autowired
	RefundMapper refundMapper;
	
	@Override
	public void saveSorder(Sorder sorder) throws Exception {
		sorder.setDeletekey(0);
		sorder.setUserdeletekey(0);
		sorderMapper.saveSorder(sorder);
	}

	@Override
	public void saveRefund(Sorder sorder, Refund refund) throws Exception {
		refund.setDeletekey(0);
		refund.setUserdeletekey(0);
		sorderMapper.updateSorderStatusByOid(sorder); // 更新sorder状态
		refundMapper.saveRefund(refund); //  添加退款记录
	}
	
	@Override
	public void deleteSorderLogicallyByOID(Sorder sorder) throws Exception{
		sorder.setUserdeletekey(1);
		sorderMapper.deleteSorderLogicallyByOID(sorder);
	}
	
	@Override
	public void deleteSorderPowerfullyByOID(Sorder sorder) throws Exception{
		sorder.setDeletekey(1);
		sorder.setUserdeletekey(1);
		sorderMapper.deleteSorderPowerfullyByOID(sorder);
	}
	
	@Override
	public void deleteRefundLogicallyByOID(Refund refund, Sorder sorder) throws Exception{
		refund.setUserdeletekey(1);
		sorder.setUserdeletekey(1);
		sorderMapper.deleteSorderLogicallyByOID(sorder);
		refundMapper.deleteRefundLogicallyByOID(refund);
	}
	
	@Override
	public void deleteRefundPowerfullyByOID(Refund refund, Sorder sorder) throws Exception{
		refund.setDeletekey(1);
		refund.setUserdeletekey(1);
		sorderMapper.updateSorderStatusByOid(sorder);
		refundMapper.deleteRefundPowerfullyByOID(refund);
	}
	
	
	@Override
	public void updateSorderStatusByOID(Sorder sorder) throws Exception{
		sorderMapper.updateSorderStatusByOid(sorder);
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
	public Sorder selectSorderByOid(int oid) throws Exception {
		return sorderMapper.selectSorderByOid(oid);	
	}
	

	@Override
	public List<Sorder> selectSorderBySorderQueryCondition(SorderQueryCondition sorderQueryCondition) throws Exception {
		List<Sorder> sorders = null;
		
		sorders = sorderMapper.selectSorderBySorderQueryCondition(sorderQueryCondition);
		
		return sorders;
	}

}
