package com.lamport.education.service;

import com.lamport.education.po.Enterprise;

public interface EnterpriseService {

	/**
	 * 通过qid查询Enterprise信息
	 * @param qid
	 * @return Enterprise
	 * @throws Exception
	 */
	public Enterprise selectEnterpriseByQid(int qid) throws Exception;
}
