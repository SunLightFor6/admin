package com.lamport.education.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lamport.education.mapper.EnterpriseMapper;
import com.lamport.education.mapper.SwiperMapper;
import com.lamport.education.po.Enterprise;
import com.lamport.education.service.EnterpriseService;
import com.lamport.education.vo.EnterpriseCategoryVo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

@Service
public class EnterpriseServiceBean implements EnterpriseService {
	@Autowired
	EnterpriseMapper enterpriseMapper;
	@Autowired
	SwiperMapper swiperMapper;
//	@Autowired
//	private JedisPool jedisPool;
	
	@Override
	public Enterprise selectEnterpriseByQid(int qid) throws Exception {
		Enterprise enterprise = null;

		enterprise = enterpriseMapper.selectEnterpriseByQid(qid);
		
		return enterprise;
	}

	@Override
	public EnterpriseCategoryVo selectAllBranchCategoryByQid(int qid) throws Exception {
		EnterpriseCategoryVo vo = null;
		
		/*------------------------------Redis相关------------------------------*/
//		Jedis jedis = jedisPool.getResource();
//		Gson gson = new Gson();
//		String key = "EnterpriseCategoryVo" + "-" + qid;
//		String voJson = jedis.get(key);
//		if(voJson == null){
//			//redis没有，从mySQL中查询
//			vo = enterpriseMapper.selectAllBranchCategoryByQid(qid);
//			//将取出来的对象打包成json字符串
//			String jsonString = gson.toJson(vo);
//			System.out.println(jsonString);/*########################################*/
//			//开启事务
//			Transaction transaction = jedis.multi();
//			//将json字符串放入redis中
//			jedis.set(key, jsonString);
//			//结束事务
//			transaction.exec();
//		}else{
//			System.out.println("It's from Redis");/*########################################*/
//			vo = gson.fromJson(voJson, new TypeToken<EnterpriseCategoryVo>(){}.getType());
//		}
		/*------------------------------Redis相关------------------------------*/
		vo = enterpriseMapper.selectAllBranchCategoryByQid(qid);
		
		return vo;
	}
}
