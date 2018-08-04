package com.lamport.education.service;

import com.lamport.education.po.Enterprise;
import com.lamport.education.vo.EnterpriseCategoryVo;

public interface EnterpriseService {

	/**
	 * 通过qid查询Enterprise信息
	 * @param qid
	 * @return Enterprise
	 * @throws Exception
	 */
	public Enterprise selectEnterpriseByQid(int qid) throws Exception;
	public EnterpriseCategoryVo selectAllBranchCategoryByQid(int qid) throws Exception;
}
