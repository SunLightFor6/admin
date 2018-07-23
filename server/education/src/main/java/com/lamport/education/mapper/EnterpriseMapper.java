package com.lamport.education.mapper;

import com.lamport.education.po.Enterprise;

public interface EnterpriseMapper {

	public Enterprise selectEnterpriseByQid(int qid) throws Exception;	

}
