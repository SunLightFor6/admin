package com.lamport.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamport.admin.mapper.SorderMapper;
import com.lamport.admin.po.Sorder;
import com.lamport.admin.service.SorderService;
import com.lamport.admin.vo.SorderQueryCondition;

/**
 * implements SorderService
 * @author Lin Zhao, protector of Sherry
 *
 */
@Service
public class SorderServiceBean implements SorderService {

	@Autowired
	private SorderMapper sorderMapper;
	
	@Override
	public int updateSorderByID(Sorder sorder) throws Exception {
		int updateResult = 1;
		
		updateResult = sorderMapper.updateSorderByID(sorder);
		
		return updateResult;
	}

	@Override
	public int updateRefundByID(Sorder sorder) throws Exception {
		int updateResult = 1;
		
		updateResult *= sorderMapper.updateSorderByID(sorder);
		updateResult *= sorderMapper.updateRefundByID(sorder.getRefund());
		
		return updateResult;
	}

	@Override
	public List<Sorder> selectSorderBySorderQueryCondition(SorderQueryCondition sorderQueryCondition) throws Exception {
		List<Sorder> sorders = null;
		
		int count = sorderMapper.selectCountSorderByQID(sorderQueryCondition.getOid());
		sorderQueryCondition.getPageTool().setCount(count);
		sorders = sorderMapper.selectSorderBySorderQueryCondition(sorderQueryCondition);
		
		return sorders;
	}

	@Override
	public int selectCountSorderByQID(int qid) throws Exception {
		int countSorder = 0;
		
		countSorder = sorderMapper.selectCountSorderByQID(qid);
		
		return countSorder;
	}

	@Override
	public double selectCountSorderActualByQID(int qid) throws Exception {
		double countSorderActual = 0;
		
		countSorderActual = sorderMapper.selectCountSorderActualByQID(qid);
		
		return countSorderActual;
	}

}