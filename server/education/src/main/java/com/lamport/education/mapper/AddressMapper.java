package com.lamport.education.mapper;

import java.util.List;

import com.lamport.education.po.Address;
import com.lamport.education.vo.EnterpriseCategoryVo;

public interface AddressMapper {
	/**
	 * 通过qid查询分部(Address)信息
	 * @param qid
	 * @return List
	 * @throws Exception
	 */
	public List<Address> selectAddressByQid(int qid) throws Exception;
	
	public EnterpriseCategoryVo selectAllCategoryByQid(int qid);
	
}

