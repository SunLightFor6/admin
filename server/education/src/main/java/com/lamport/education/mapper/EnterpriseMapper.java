package com.lamport.education.mapper;

import com.lamport.education.po.Enterprise;
import com.lamport.education.vo.EnterpriseCategoryVo;

public interface EnterpriseMapper {

	/**
	 * 通过qid查询Enterprise信息
	 * @param qid
	 * @return Enterprise
	 * @throws Exception
	 */
	public Enterprise selectEnterpriseByQid(int qid) throws Exception;	
	/**
	 * 通过qid查询Enterprise得三级联动信息
	 * @param qid
	 * @return EnterpriseCategoryVo
	 * @throws Exception
	 */
	public EnterpriseCategoryVo selectAllBranchCategoryByQid(int qid)throws Exception;	
}
