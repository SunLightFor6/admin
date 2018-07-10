package com.lamport.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lamport.admin.mapper.LessonBranchMapper;
import com.lamport.admin.mapper.LessonMapper;
import com.lamport.admin.mapper.SorderMapper;
import com.lamport.admin.po.Lesson;
import com.lamport.admin.service.LessonService;
import com.lamport.admin.vo.LessonQueryCondition;

/**
 * implements LessonService
 * @author Lin Zhao, protector of Sherry
 *
 */
@Service
public class LessonServiceBean implements LessonService {

	@Autowired
	private LessonMapper lessonMapper;
	@Autowired
	private LessonBranchMapper lessonBranchMapper;
	@Autowired
	private SorderMapper sorderMapper;
	
	@Override
	public int saveLesson(Lesson lesson, MultipartFile img) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteLessonLogicallyByID(int id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateLessonByID(Lesson lesson, MultipartFile img) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Lesson> selectLessonByLessonQueryCondition(LessonQueryCondition lessonQueryCondition) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectCountLessonByQID(int qid) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
