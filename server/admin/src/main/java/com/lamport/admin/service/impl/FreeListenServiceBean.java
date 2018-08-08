package com.lamport.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lamport.admin.fileupload.FileManager;
import com.lamport.admin.mapper.AddressMapper;
import com.lamport.admin.mapper.FreeListenBookMapper;
import com.lamport.admin.mapper.FreeListenMapper;
import com.lamport.admin.po.Address;
import com.lamport.admin.po.FreeListen;
import com.lamport.admin.service.FreeListenService;
import com.lamport.admin.vo.FreeListenQueryCondition;
import com.lamport.admin.vo.QIDAndBranch;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

/**
 * implements FreeListenService
 * @author Lin Zhao, protector of Sherry
 *
 */
@Service
public class FreeListenServiceBean implements FreeListenService {

	@Autowired
	private FreeListenMapper freeListenMapper;
	@Autowired
	private FreeListenBookMapper freeListenBookMapper;
	@Autowired
	private AddressMapper addressMapper;
	@Autowired
	private JedisPool jedisPool;
	
	@Override
	public int saveFreeListen(FreeListen freeListen, MultipartFile img, String path) throws Exception {
		System.out.println("..........FreeListenServiceBean..........saveFreeListen()..........");
		int saveResult = 1;
		
		QIDAndBranch qidAndBranch = new QIDAndBranch();
		qidAndBranch.setQid(freeListen.getQid());
		qidAndBranch.setBranch(freeListen.getBranchName());
		Address address = addressMapper.selectAddressIDByQIDAndBranch(qidAndBranch);		
		String imgurl = null;
		if(img != null) {
			imgurl = FileManager.upload(img);
		}
		freeListen.setImgurl(imgurl);
		freeListen.setBranchid(address.getId());
		freeListen.setDeletekey(0);
		saveResult = freeListenMapper.saveFreeListen(freeListen);
		
		/*------------------------------Redis相关------------------------------*/
		//FreeListen已经发生了变化，将旧的HomePageFreeListen信息从Redis中删除
		Jedis jedis = jedisPool.getResource();
		String key = "homePageFreeListen" + "-" + freeListen.getQid();
		//开启事务
		Transaction transaction = jedis.multi();
		//删除
		transaction.del(key);
		//结束事务
		transaction.exec();
		/*------------------------------Redis相关------------------------------*/
		
		return saveResult;
	}

	@Override
	public int deleteFreeListenLogicallyByID(int id, int qid) throws Exception {
		System.out.println("..........FreeListenServiceBean..........deleteFreeListenLogicallyByID()..........");
		int deleteResult = 0;

		freeListenBookMapper.deleteFreeListenBookLogicallyByFID(id);
		deleteResult = freeListenMapper.deleteFreeListenLogicallyByID(id);
		/*------------------------------Redis相关------------------------------*/
		//FreeListen已经发生了变化，将旧的HomePageFreeListen信息从Redis中删除
		Jedis jedis = jedisPool.getResource();
		String key = "homePageFreeListen" + "-" + qid;
		//开启事务
		Transaction transaction = jedis.multi();
		//删除
		transaction.del(key);
		//结束事务
		transaction.exec();
		/*------------------------------Redis相关------------------------------*/
		
		return deleteResult;
	}

	@Override
	public int updateFreeListenByID(FreeListen freeListen, MultipartFile img, String path) throws Exception {
		System.out.println("..........FreeListenServiceBean..........updateFreeListenByID()..........");
		int updateResult = 1;
	
		QIDAndBranch qidAndBranch = new QIDAndBranch();
		qidAndBranch.setQid(freeListen.getQid());
		qidAndBranch.setBranch(freeListen.getBranchName());
		Address address = addressMapper.selectAddressIDByQIDAndBranch(qidAndBranch);
		String imgurl = null;
		if(img != null){
			imgurl = FileManager.upload(img);
		}
		freeListen.setImgurl(imgurl);
		freeListen.setBranchid(address.getId());
		updateResult = freeListenMapper.updateFreeListenByID(freeListen);
		
		/*------------------------------Redis相关------------------------------*/
		//FreeListen已经发生了变化，将旧的HomePageFreeListen信息从Redis中删除
		Jedis jedis = jedisPool.getResource();
		String key = "homePageFreeListen" + "-" + freeListen.getQid();
		//开启事务
		Transaction transaction = jedis.multi();
		//删除
		transaction.del(key);
		//结束事务
		transaction.exec();
		/*------------------------------Redis相关------------------------------*/
		
		return updateResult;
	}

	@Override
	public List<FreeListen> selectFreeListenByFreeListenQueryCondition(FreeListenQueryCondition freeListenQueryCondition) throws Exception {
		System.out.println("..........FreeListenServiceBean..........selectFreeListenByFreeListenQueryCondition()..........");

		List<FreeListen> freeListens = null;
//		List<FreeListen> freeListens_query = null;

		QIDAndBranch qidAndBranch = new QIDAndBranch();
		qidAndBranch.setQid(freeListenQueryCondition.getQid());
		int count = freeListenMapper.selectCountFreeListenByFreeListenQueryCondition(freeListenQueryCondition);
		freeListenQueryCondition.getPageTool().setCount(count);
		
		if(freeListenQueryCondition.getBranch()==null || freeListenQueryCondition.getBranch().equals("")){
			freeListenQueryCondition.setBranch(null);
			freeListenQueryCondition.setBranchid(0);
			freeListens = freeListenMapper.selectFreeListenByFreeListenQueryCondition(freeListenQueryCondition);
			for(FreeListen freeListen : freeListens){
				Address address = addressMapper.selectAddressByID(freeListen.getBranchid());
				freeListen.setBranch(address);
			}
		}else{
			qidAndBranch.setBranch(freeListenQueryCondition.getBranch());
			Address address = addressMapper.selectAddressIDByQIDAndBranch(qidAndBranch);
			if(address == null){
				freeListenQueryCondition.setBranchid(-1);
				//既然Address不存在，那么就没有必要再查下去了
				freeListens = new ArrayList<FreeListen>();
			}else{
				freeListenQueryCondition.setBranchid(address.getId());
				freeListens = freeListenMapper.selectFreeListenByFreeListenQueryCondition(freeListenQueryCondition);
				for(FreeListen freeListen : freeListens){
					freeListen.setBranch(address);
				}
			}
		}
//		for(FreeListen freeListen : freeListens){
//			Address address = addressMapper.selectAddressByID(freeListen.getBranchid());
//			freeListen.setBranch(address);
//			if(freeListenQueryCondition.getBranch().equals("") || address.getBranch().equals(freeListenQueryCondition.getBranch())) {
//				freeListens_query.add(freeListen);
//			}
//		}
		
		return freeListens;
	}

	@Override
	public int selectCountFreeListenByQID(int qid) throws Exception {
		System.out.println("..........FreeListenServiceBean..........selectCountFreeListenByQID()..........");

		int countFreeListen = 0;
		
		countFreeListen = freeListenMapper.selectCountFreeListenByQID(qid);
		
		return countFreeListen;
	}

}
