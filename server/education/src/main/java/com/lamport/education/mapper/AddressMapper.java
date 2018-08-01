package com.lamport.education.mapper;

import java.util.List;

import com.lamport.education.vo.QIDAndBranch;
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
	/**
	 * 通过qid和branch查询Address信息
	 * @param qidAndBranch
	 * @return int
	 * @throws Exception
	 */
	public Address selectAddressIDByQIDAndBranch(QIDAndBranch qidAndBranch) throws Exception;
	
	public EnterpriseCategoryVo selectAllCategoryByQid(int qid);
}

