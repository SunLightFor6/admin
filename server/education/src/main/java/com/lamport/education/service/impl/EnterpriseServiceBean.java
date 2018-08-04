package com.lamport.education.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamport.education.mapper.EnterpriseMapper;
import com.lamport.education.mapper.SwiperMapper;
import com.lamport.education.po.Enterprise;
import com.lamport.education.service.EnterpriseService;
import com.lamport.education.vo.EnterpriseCategoryVo;

@Service
public class EnterpriseServiceBean implements EnterpriseService {
	@Autowired
	EnterpriseMapper enterpriseMapper;
	@Autowired
	SwiperMapper swiperMapper;
	
	@Override
	public Enterprise selectEnterpriseByQid(int qid) throws Exception {
		Enterprise enterprise = null;

		enterprise = enterpriseMapper.selectEnterpriseByQid(qid);
		
		return enterprise;
	}

	@Override
	public EnterpriseCategoryVo selectAllBranchCategoryByQid(int qid) throws Exception {
		return enterpriseMapper.selectAllBranchCategoryByQid(qid);
	}
}
