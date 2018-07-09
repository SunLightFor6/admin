package com.lamport.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamport.admin.mapper.AddressMapper;
import com.lamport.admin.mapper.EnterpriseMapper;
import com.lamport.admin.mapper.FreeListenMapper;
import com.lamport.admin.mapper.LessonBranchMapper;
import com.lamport.admin.mapper.SorderMapper;
import com.lamport.admin.po.Address;
import com.lamport.admin.service.AddressService;
import com.lamport.admin.vo.QIDAndPage;

/**
 * implements AddressService
 * @author Lin Zhao, protector of Sherry
 *
 */
@Service
public class AddressServiceBean implements AddressService {

	@Autowired
	private AddressMapper addressMapper;
	@Autowired
	private FreeListenMapper freeListenMapper;
	@Autowired
	private LessonBranchMapper lessonBranchMapper;
	@Autowired
	private SorderMapper sorderMapper;
	
	@Override
	public int saveAddress(Address address) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAddressLogicallyByID(int id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAddressByID(Address address) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Address> selectAddressByQIDAndPage(QIDAndPage qidAndPage) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
