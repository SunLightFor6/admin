package com.lamport.education.mapper;

import com.lamport.education.po.Enterprise;

public interface EnterpriseMapper {

	/**
	 * 通过qid查询Enterprise信息
	 * @param qid
	 * @return Enterprise
	 * @throws Exception
	 */
	public Enterprise selectEnterpriseByQid(int qid) throws Exception;	
}
