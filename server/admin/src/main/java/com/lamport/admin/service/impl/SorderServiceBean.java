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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateRefundByID(Sorder sorder) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Sorder> selectSorderBySorderQueryCondition(SorderQueryCondition sorderQueryCondition) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectCountSorderByQID(int qid) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double selectCountSorderActualByQID(int qid) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
