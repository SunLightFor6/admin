package com.lamport.education.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisPool;

import com.lamport.education.mapper.LessonMapper;
import com.lamport.education.po.Lesson;
import com.lamport.education.service.LessonService;
import com.lamport.education.util.PageBean;
import com.lamport.education.vo.LessonInfoVo;
import com.lamport.education.vo.LessonQueryCondition;
import com.lamport.education.vo.LessonVo;

@Service
public class LessonServiceBean implements LessonService {
	
	@Autowired
	LessonMapper lessonMapper;
	@Autowired
	JedisPool jedisPool;
	
	@Override
	public LessonInfoVo selectLessonByLid(int lid) throws Exception {
		return lessonMapper.selectLessonByLid(lid);
	}

	@Override
	public LessonInfoVo selectLessonByOid(int oid) throws Exception{
		return lessonMapper.selectLessonByOid(oid);
	}
	
	@Override
	public List<Lesson> selectHomePageLessonByQid(PageBean page, int qid) throws Exception {
		LessonVo lessonVo = new LessonVo();
		lessonVo.setQid(qid);
		lessonVo.setPage(page);
		return lessonMapper.selectHomePageLessonByQid(lessonVo);
	}

	@Override
	public List<Lesson> selectLessonByLessonQueryCondition(LessonQueryCondition lessonQueryCondition) throws Exception {
		List<Lesson> lessons = null;
		
		lessons = lessonMapper.selectLessonByLessonQueryCondition(lessonQueryCondition);
		
		return lessons;
	}
 
}
