package com.lamport.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamport.admin.mapper.AddressMapper;
import com.lamport.admin.mapper.FreeListenMapper;
import com.lamport.admin.mapper.LessonBranchMapper;
import com.lamport.admin.mapper.LessonMapper;
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
	@Autowired
	private LessonMapper lessonMapper;
	
	@Override
	public int saveAddress(Address address) throws Exception {
		int saveResult = 1;

		address.setDeletekey(0);
		saveResult = addressMapper.saveAddress(address);
		
		return saveResult;
	}

	@Override
	public int deleteAddressLogicallyByID(int id) throws Exception {
		int deleteResult = 1;

		List<Integer> lids = lessonBranchMapper.selectLIDByBranchID(id);
		deleteResult *= sorderMapper.deleteSorderLogicallyByBranchID(id);
		deleteResult *= freeListenMapper.deleteFreeListenLogicallyByBranchID(id);
		deleteResult *= lessonBranchMapper.deleteLessonBranchLogicallyByBranchID(id);
		deleteResult *= addressMapper.deleteAddressLogicallyByID(id);
		//校验，删除没有分部的精品课
		for(Integer lid: lids){
			int count = lessonBranchMapper.selectCountLessonBranchByLID(lid);
			if(count == 0){
				deleteResult *= lessonMapper.deleteLessonLogicallyByID(lid);
			}
		}
		deleteResult = deleteResult>0 ? 1 : 0;
		
		return deleteResult;
	}

	@Override
	public int updateAddressByID(Address address) throws Exception {
		int updateResult = 1;

		updateResult *= addressMapper.updateAddressByID(address);
		
		return updateResult;
	}

	@Override
	public List<Address> selectAddressByQIDAndPage(QIDAndPage qidAndPage) throws Exception {
		List<Address> addresses = null;

		addresses = addressMapper.selectAddressByQIDAndPage(qidAndPage);
		
		return addresses;
	}

	@Override
	public List<Address> selectAddressByQID(int qid) throws Exception {
		List<Address> addresses = null;

		addresses = addressMapper.selectAddressByQID(qid);
		
		return addresses;
	}

}
