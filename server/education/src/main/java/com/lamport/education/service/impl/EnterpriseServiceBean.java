package com.lamport.education.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamport.education.mapper.EnterpriseMapper;
import com.lamport.education.mapper.SwiperMapper;
import com.lamport.education.po.Enterprise;
import com.lamport.education.service.EnterpriseService;

@Service
public class EnterpriseServiceBean implements EnterpriseService {
	@Autowired
	EnterpriseMapper enterpriseMapper;
	@Autowired
	SwiperMapper swiperMapper;
	
	@Override
	public Enterprise selectEnterpriseByQid(int qid) throws Exception {
		Enterprise enterprise = enterpriseMapper.selectEnterpriseByQid(qid);
		return enterprise;
	}
	
	/**
	 * 查找企业轮播图 
	 */
	@Override
	public List<String> selectEnterpriseSwiperByQid(int qid) throws Exception {
		// TODO Auto-generated method stub
		return swiperMapper.selectAllSwiperByQid(qid);
	}

}
