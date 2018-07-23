package com.lamport.education.mapper;

import java.util.List;

import com.lamport.education.po.Address;
import com.lamport.education.vo.EnterpriseInfoVo;

public interface AddressMapper {
	
	public List<Address> selectAddressPageByQid(EnterpriseInfoVo enterpriseInfoVo) throws Exception;
	
}

