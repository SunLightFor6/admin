package com.lamport.education.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lamport.education.mapper.SwiperMapper;
import com.lamport.education.service.SwiperService;
import com.lamport.education.vo.QIDAndCategory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

@Service
public class SwiperServiceBean implements SwiperService {

	@Autowired
	SwiperMapper swiperMapper;
	@Autowired
	private JedisPool jedisPool;
	
	@Override
	public List<String> selectSwiperImgurlByQIDAndCategory(QIDAndCategory qidAndCategory) throws Exception {
		List<String> swiperImgurls = null;
		
		/*------------------------------Redis相关------------------------------*/
		Jedis jedis = jedisPool.getResource();
		Gson gson = new Gson();
		String key = "swiperImgurls" + "-" + qidAndCategory.getQid() + "-" + qidAndCategory.getCategory();
		String imgurls = jedis.get(key);
		if(imgurls == null){
			//redis没有，从mySQL中查询
			swiperImgurls = swiperMapper.selectSwiperImgurlByQIDAndCategory(qidAndCategory);
			//将取出来的对象打包成json字符串
			String jsonString = gson.toJson(swiperImgurls);
			System.out.println(jsonString);/*########################################*/
			//将json字符串放入redis中
			Transaction transaction = jedis.multi();
			transaction.set(key, jsonString);
			transaction.exec();
		}else{
			System.out.println("It's from Redis");/*########################################*/
			swiperImgurls = gson.fromJson(imgurls, new TypeToken<List<String>>(){}.getType());
		}
		/*------------------------------Redis相关------------------------------*/
//		swiperImgurls = swiperMapper.selectSwiperImgurlByQIDAndCategory(qidAndCategory);
		
		return swiperImgurls;
	}

}
