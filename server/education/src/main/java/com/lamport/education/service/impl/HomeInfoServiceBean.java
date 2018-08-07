package com.lamport.education.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lamport.education.mapper.FreeListenMapper;
import com.lamport.education.mapper.LessonMapper;
import com.lamport.education.po.FreeListen;
import com.lamport.education.po.Lesson;
import com.lamport.education.service.HomeInfoService;
import com.lamport.education.vo.QIDAndPage;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

@Service
public class HomeInfoServiceBean implements HomeInfoService {

	@Autowired
	LessonMapper lessonMapper;
	@Autowired
	FreeListenMapper freeListenMapper;
	@Autowired
	private JedisPool jedisPool;
	
	@Override
	public List<Lesson> selectHomePageLessonByQid(QIDAndPage qidAndPage) throws Exception {
		List<Lesson> lessons = null;

		/*------------------------------Redis相关------------------------------*/
		Jedis jedis = jedisPool.getResource();
		Gson gson = new Gson();
		String key = "homePageLesson" + "-" + qidAndPage.getQid();
		String homePageLesson = jedis.get(key);		
		if(homePageLesson == null){
			//redis没有，从mySQL中查询
			lessons = lessonMapper.selectLessonByQIDAndPage(qidAndPage);
			//将取出来的对象打包成json字符串
			String jsonString = gson.toJson(lessons);
			System.out.println(jsonString);/*########################################*/
			//将json字符串放入redis中
			Transaction transaction = jedis.multi();
			transaction.set(key, jsonString);
			transaction.exec();
			
		}else{
			System.out.println("It's from Redis");/*########################################*/
			lessons = gson.fromJson(homePageLesson, new TypeToken<List<Lesson>>(){}.getType());
		}
		/*------------------------------Redis相关------------------------------*/
//		lessons = lessonMapper.selectLessonByQIDAndPage(qidAndPage);
		
		return lessons;
	}

	@Override
	public List<FreeListen> selectHomePageFreeListenByQid(QIDAndPage qidAndPage) throws Exception {
		List<FreeListen> freeListens = null;

		/*------------------------------Redis相关------------------------------*/
		Jedis jedis = jedisPool.getResource();
		Gson gson = new Gson();
		String key = "homePageFreeListen" + "-" + qidAndPage.getQid();
		String homePageFreeListen = jedis.get(key);		
		if(homePageFreeListen == null){
			//redis没有，从mySQL中查询
			freeListens = freeListenMapper.selectFreeListenByQIDAndPage(qidAndPage);
			//将取出来的对象打包成json字符串
			String jsonString = gson.toJson(freeListens);
			System.out.println(jsonString);/*########################################*/
			//将json字符串放入redis中
			Transaction transaction = jedis.multi();
			transaction.set(key, jsonString);
			transaction.exec();
		}else{
			System.out.println("It's from Redis");/*########################################*/
			freeListens = gson.fromJson(homePageFreeListen, new TypeToken<List<Lesson>>(){}.getType());
		}
		/*------------------------------Redis相关------------------------------*/
//		freeListens = freeListenMapper.selectFreeListenByQIDAndPage(qidAndPage);
		
		return freeListens;
	}

}
