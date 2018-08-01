package com.lamport.education.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamport.education.mapper.AddressMapper;
import com.lamport.education.mapper.RefundMapper;
import com.lamport.education.mapper.SorderMapper;
import com.lamport.education.po.Address;
import com.lamport.education.po.Refund;
import com.lamport.education.po.Sorder;
import com.lamport.education.service.SorderService;
import com.lamport.education.vo.QIDAndBranch;
import com.lamport.education.vo.SorderQueryCondition;
import com.lamport.education.vo.UIDAndStatus;

@Service
public class SorderServiceBean implements SorderService {

	@Autowired
	SorderMapper sorderMapper;	
	@Autowired
	RefundMapper refundMapper;
	@Autowired
	AddressMapper addressMapper;
	
	@Override
	public void saveSorder(Sorder sorder) throws Exception {
		QIDAndBranch qidAndBranch = new QIDAndBranch();
		qidAndBranch.setQid(sorder.getQid());
		qidAndBranch.setBranch(sorder.getBranch());
		Address address = addressMapper.selectAddressIDByQIDAndBranch(qidAndBranch);
		if(address!=null){
			sorder.setBranchid(address.getId());
		}
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
	public void updateSorderByOID(Sorder sorder) throws Exception{
		QIDAndBranch qidAndBranch = new QIDAndBranch();
		qidAndBranch.setQid(sorder.getQid());
		qidAndBranch.setBranch(sorder.getBranch());
		Address address = addressMapper.selectAddressIDByQIDAndBranch(qidAndBranch);
		sorder.setBranchid(address.getId());
		sorderMapper.updateSorderByOID(sorder);
	}
	
	@Override
	public void updateSorderStatusByOID(Sorder sorder) throws Exception{
		sorderMapper.updateSorderStatusByOid(sorder);
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

	@Override
	public int selectCountSorderByUIDAndStatus(UIDAndStatus uidAndStatus) throws Exception{
		int countSorder = 0;
		
		countSorder = sorderMapper.selectCountSorderByUIDAndStatus(uidAndStatus);
		
		return countSorder;
	}
}
