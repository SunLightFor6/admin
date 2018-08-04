package com.lamport.education.service;

import java.util.List;

import com.lamport.education.po.Address;

public interface AddressService {
	/**
	 * 通过qid查询分部(Address)信息
	 * @param qid
	 * @return List
	 * @throws Exception
	 */
	public List<Address> selectAddressByQid(int qid) throws Exception;
	
	
	//public String selectAllCategoryByQid(int qid) throws Exception;
}
