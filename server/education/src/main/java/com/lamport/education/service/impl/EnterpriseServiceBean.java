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
	
	@Override
	public List<String> selectEnterpriseSwipersByQid(int qid) throws Exception {
		List<String> enterpriseSwipers = swiperMapper.selectEnterpriseSwipersByQid(qid);
		return enterpriseSwipers;
	}

}
