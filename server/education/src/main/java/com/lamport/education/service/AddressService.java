package com.lamport.education.service;

import java.util.List;

import com.lamport.education.po.Address;
import com.lamport.education.util.PageBean;

public interface AddressService {

	public List<Address> selectAddressPageByQid(PageBean page, int qid) throws Exception;
	
}
