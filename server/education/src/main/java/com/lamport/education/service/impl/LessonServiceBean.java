package com.lamport.education.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisPool;

import com.lamport.education.mapper.LessonMapper;
import com.lamport.education.po.Lesson;
import com.lamport.education.service.LessonService;
import com.lamport.education.vo.LessonQueryCondition;

@Service
public class LessonServiceBean implements LessonService {
	
	@Autowired
	LessonMapper lessonMapper;
	@Autowired
	JedisPool jedisPool;
	
	@Override
	public Lesson selectLessonByLid(int lid) throws Exception {
		Lesson lesson = null;
		
		lesson = lessonMapper.selectLessonByLid(lid);
		
		return lesson;
	}

	@Override
	public Lesson selectLessonByOid(int oid) throws Exception{
		Lesson lesson = null;
		
		lesson = lessonMapper.selectLessonByOid(oid);
		
		return lesson;
	}

	@Override
	public List<Lesson> selectLessonByLessonQueryCondition(LessonQueryCondition lessonQueryCondition) throws Exception {
		List<Lesson> lessons = null;
		
		lessons = lessonMapper.selectLessonByLessonQueryCondition(lessonQueryCondition);
		
		return lessons;
	}
 
}
