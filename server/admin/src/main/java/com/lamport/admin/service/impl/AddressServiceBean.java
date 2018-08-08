package com.lamport.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamport.admin.mapper.AddressMapper;
import com.lamport.admin.mapper.FreeListenBookMapper;
import com.lamport.admin.mapper.FreeListenMapper;
import com.lamport.admin.mapper.LessonBranchMapper;
import com.lamport.admin.mapper.LessonMapper;
import com.lamport.admin.mapper.SorderMapper;
import com.lamport.admin.po.Address;
import com.lamport.admin.po.FreeListen;
import com.lamport.admin.po.Sorder;
import com.lamport.admin.service.AddressService;
import com.lamport.admin.vo.QIDAndPage;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

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
	private FreeListenBookMapper freeListenBookMapper;
	@Autowired
	private LessonBranchMapper lessonBranchMapper;
	@Autowired
	private SorderMapper sorderMapper;
	@Autowired
	private LessonMapper lessonMapper;
	@Autowired
	private JedisPool jedisPool;
	
	@Override
	public int saveAddress(Address address) throws Exception {
		System.out.println("..........AddressServiceBean..........saveAddress()..........");
		int saveResult = 1;

		address.setDeletekey(0);
		saveResult = addressMapper.saveAddress(address);
		
		return saveResult;
	}

	@Override
	public int deleteAddressLogicallyByID(int id, int qid) throws Exception {
		System.out.println("..........AddressServiceBean..........deleteAddressLogicallyByID()..........");

		int deleteResult = 1;

		List<Integer> lids = lessonBranchMapper.selectLIDByBranchID(id);
		List<Sorder> sorders = sorderMapper.selectSorderByBranchID(id);
		List<FreeListen> freeListens = freeListenMapper.selectFreeListenByBranchID(id);
		if(sorders!=null && !sorders.isEmpty()){
			sorderMapper.deleteMultiRefundLogicallyByOID(sorders);
		}
		sorderMapper.deleteSorderLogicallyByBranchID(id);
		if(freeListens!=null && !freeListens.isEmpty()){
			freeListenBookMapper.deleteMultiFreeListenBookLogicallyByFID(freeListens);
		}
		freeListenMapper.deleteFreeListenLogicallyByBranchID(id);
		lessonBranchMapper.deleteLessonBranchLogicallyByBranchID(id);
		deleteResult *= addressMapper.deleteAddressLogicallyByID(id);
		//校验，删除没有分部的精品课
		if(lids!=null && !lids.isEmpty()){
			for(Integer lid: lids){
				int count = 0;
				if(lid != null){
					count = lessonBranchMapper.selectCountLessonBranchByLID(lid.intValue());
				}
				if(count == 0){
					deleteResult *= lessonMapper.deleteLessonLogicallyByID(lid.intValue());
				}
			}
		}
		deleteResult = deleteResult>0 ? 1 : 0;
		
		/*------------------------------Redis相关------------------------------*/
		//删除分部后，Lesson和FreeListen已经发生了变化，将旧的HomePageLesson和HomePageFreeListen信息从Redis中删除
		Jedis jedis = jedisPool.getResource();
		String lessonKey = "homePageLesson" + "-" + qid;
		String freelistenKey = "homePageFreeListen" + "-" + qid;
		//开启事务
		Transaction transaction = jedis.multi();
		//删除
		transaction.del(lessonKey);
		transaction.del(freelistenKey);
		//结束事务
		transaction.exec();
		/*------------------------------Redis相关------------------------------*/
		
		return deleteResult;
	}

	@Override
	public int updateAddressByID(Address address) throws Exception {
		System.out.println("..........AddressServiceBean..........updateAddressByID()..........");

		int updateResult = 1;

		updateResult *= addressMapper.updateAddressByID(address);
		
		return updateResult;
	}

	@Override
	public List<Address> selectAddressByQIDAndPage(QIDAndPage qidAndPage) throws Exception {
		System.out.println("..........AddressServiceBean..........selectAddressByQIDAndPage()..........");

		List<Address> addresses = null;

		int count = addressMapper.selectCountAddressByQID(qidAndPage.getQid());
		qidAndPage.getPageTool().setCount(count);
		addresses = addressMapper.selectAddressByQIDAndPage(qidAndPage);
		
		return addresses;
	}

	@Override
	public List<Address> selectAddressByQID(int qid) throws Exception {
		System.out.println("..........AddressServiceBean..........selectAddressByQID()..........");

		List<Address> addresses = null;

		addresses = addressMapper.selectAddressByQID(qid);
		
		return addresses;
	}

	@Override
	public List<String> selectBranchByQID(int qid) throws Exception{
		System.out.println("..........AddressServiceBean..........selectBranchByQID()..........");
		List<String> branches = null;
		
		branches = addressMapper.selectBranchByQID(qid);
		
		return branches;
	}
}
