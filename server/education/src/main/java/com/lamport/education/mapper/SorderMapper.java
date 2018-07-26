package com.lamport.education.mapper;

import java.util.List;

import com.lamport.education.po.Sorder;
import com.lamport.education.vo.SorderQueryCondition;

public interface SorderMapper {
	/**
	 * 创建Sorder信息
	 * @param sorder
	 * @throws Exception
	 */
	public void saveSorder(Sorder sorder) throws Exception;
	
	/**
	 * 通过oid逻辑删除sorder（只更改userdeletekey）
	 * @param Sorder sorder
	 * @throws Exception
	 */
	public void deleteSorderLogicallyByOID(Sorder sorder) throws Exception;
	
	/**
	 * 通过oid逻辑删除sorder（同时更改deletekey和userdeletekey）
	 * @param Sorder sorder
	 * @throws Exception
	 */
	public void deleteSorderPowerfullyByOID(Sorder sorder) throws Exception;
	
	/**
	 * 通过oid更新Sorder的状态
	 * @param sorder
	 * @throws Exception
	 */
	public void updateSorderStatusByOid(Sorder sorder) throws Exception;
	
	
	
	
	public void updatePaymentDetail(Sorder sorder) throws Exception;
	
	public Sorder selectSorderByOid(int oid);
	
	
	/**
	 * 通过多条件查询Sorder信息
	 * @param sorderQueryCondition
	 * @return List
	 * @throws Exception
	 */
	public List<Sorder> selectSorderBySorderQueryCondition(SorderQueryCondition sorderQueryCondition) throws Exception;
}
