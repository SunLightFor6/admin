package com.lamport.education.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamport.education.mapper.FreeListenMapper;
import com.lamport.education.mapper.LessonMapper;
import com.lamport.education.po.FreeListen;
import com.lamport.education.po.Lesson;
import com.lamport.education.service.HomeInfoService;
import com.lamport.education.vo.QIDAndPage;

@Service
public class HomeInfoServiceBean implements HomeInfoService {

	@Autowired
	LessonMapper lessonMapper;
	@Autowired
	FreeListenMapper freeListenMapper;
	
	@Override
	public List<Lesson> selectHomePageLessonByQid(QIDAndPage qidAndPage) throws Exception {
		List<Lesson> lessons = null;

		lessons = lessonMapper.selectLessonByQIDAndPage(qidAndPage);
		
		return lessons;
	}

	@Override
	public List<FreeListen> selectHomePageFreeListenByQid(QIDAndPage qidAndPage) throws Exception {
		List<FreeListen> freeListens = null;

		freeListens = freeListenMapper.selectFreeListenByQIDAndPage(qidAndPage);
		
		return freeListens;
	}

}
