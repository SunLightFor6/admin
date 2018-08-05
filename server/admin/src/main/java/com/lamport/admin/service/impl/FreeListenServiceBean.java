package com.lamport.admin.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lamport.admin.mapper.AddressMapper;
import com.lamport.admin.mapper.FreeListenMapper;
import com.lamport.admin.po.Address;
import com.lamport.admin.po.FreeListen;
import com.lamport.admin.service.FreeListenService;
import com.lamport.admin.tool.Const;
import com.lamport.admin.tool.FileTool;
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
	private AddressMapper addressMapper;
	@Autowired
	private JedisPool jedisPool;
	
	@Override
	public int saveFreeListen(FreeListen freeListen, MultipartFile img, String path) throws Exception {
		System.out.println("..........FreeListenServiceBean..........saveFreeListen()..........");

		int saveResult = 1;
		
		String imgurl = null;
		File imgFile = null;
		if(img != null) {
//			Creator creator = new Creator();
//			String filename = creator.createFilename();
			String filename = System.currentTimeMillis() + img.getOriginalFilename();
			imgFile = new File(path + Const.ImgFreeListenPath, filename);
			imgurl = Const.ImgFreeListenPath + "/" + filename;
		}
		freeListen.setImgurl(imgurl);
		freeListen.setDeletekey(0);
		saveResult = freeListenMapper.saveFreeListen(freeListen);
		if(imgurl != null) {
			img.transferTo(imgFile);
		}
		
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
	
		String imgurl = null;
		File imgFile = null;
		if(img != null){
//			Creator creator = new Creator();
//			String filename = creator.createFilename();
			String filename = System.currentTimeMillis() + img.getOriginalFilename();
			imgFile = new File(path + Const.ImgFreeListenPath, filename);
			imgurl = Const.ImgFreeListenPath + "/" + filename;
		}
		String oldImgurl = path + freeListenMapper.selectFreeListenImgurlByID(freeListen.getId());
		freeListen.setImgurl(imgurl);
		updateResult = freeListenMapper.updateFreeListenByID(freeListen);
		if(imgurl != null){
			img.transferTo(imgFile);//保存文件
			FileTool.deleteFile(oldImgurl);
		}
		
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
