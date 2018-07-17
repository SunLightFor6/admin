package com.lamport.education.service;

import java.util.List;

import com.lamport.education.po.Enterprise;

public interface EnterpriseService {

	public Enterprise selectEnterpriseByQid(int qid) throws Exception;
	public List<String> selectEnterpriseSwiperByQid(int qid) throws Exception;
}
