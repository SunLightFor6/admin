package com.lamport.education.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamport.education.mapper.AddressMapper;
import com.lamport.education.po.Address;
import com.lamport.education.po.Enterprise;
import com.lamport.education.service.AddressService;
import com.lamport.education.util.PageBean;
import com.lamport.education.vo.EnterpriseInfoVo;
@Service
public class AddressServiceBean implements AddressService {
    @Autowired
    AddressMapper  addressMapper;
	@Override
	public List<Address> selectAddressPageByQid(PageBean page, int qid) throws Exception {
	    EnterpriseInfoVo enterpriseInfoVo = new EnterpriseInfoVo();
	    enterpriseInfoVo.setPage(page);
	    enterpriseInfoVo.setQid(qid);
		List<Address> address = addressMapper.selectAddressPageByQid(enterpriseInfoVo);
		return address;
	}

}
