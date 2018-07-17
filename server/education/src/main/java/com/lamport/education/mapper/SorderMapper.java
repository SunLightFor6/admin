package com.lamport.education.mapper;

import java.util.List;

import com.lamport.education.po.Sorder;
import com.lamport.education.vo.BusinessVo;
import com.lamport.education.vo.SorderInfoVo;

public interface SorderMapper {
	
	public void saveSorder(Sorder sorder) throws Exception;
	public void updatePaymentDetail(Sorder sorder) throws Exception;
	public void deleteObligationLogicallyByOid(int oid) throws Exception; //取消待付款，删除待付款记录，同时设置deletekey和userdeletekey
	public void deleteFinalSorderLogicallyByOid(int oid) throws Exception; //删除“已使用”的订单，只设置userdeletekey
	public void updateStatusOfSorderByOid(Sorder sorder) throws Exception;
	public List<SorderInfoVo> selectSorderPageByUid(BusinessVo businessVo) throws Exception;
	public List<SorderInfoVo> selectSorderPageByUidAndStatus(BusinessVo businessVo) throws Exception;
	public Sorder selectSorderByOid(int oid);
	
}
