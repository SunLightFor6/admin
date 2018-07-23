package com.lamport.education.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamport.education.mapper.LessonMapper;
import com.lamport.education.po.FreeListenBook;
import com.lamport.education.po.Lesson;
import com.lamport.education.service.LessonService;
import com.lamport.education.util.PageBean;
import com.lamport.education.vo.LessonInfoVo;
import com.lamport.education.vo.LessonVo;
@Service
public class LessionServiceBean implements LessonService {
	@Autowired
	LessonMapper lessonMapper;
	@Override
	public LessonInfoVo selectLessonByLid(int lid) throws Exception {
		return lessonMapper.selectLessonByLid(lid);
	}

	@Override
	public List<Lesson> selectLessonPageByBidAndCategory(PageBean page, int bid, String category) throws Exception {
		LessonVo lessonVo = new LessonVo();
		lessonVo.setBid(bid);
		lessonVo.setPage(page);
		lessonVo.setCategory(category);
		return lessonMapper.selectLessonPageByQidAndBidAndCategory(lessonVo);
	}

	@Override
	public List<Lesson> selectLessonsByQid(PageBean page, int qid) throws Exception {
		LessonVo lessonVo = new LessonVo();
		lessonVo.setQid(qid);
		lessonVo.setPage(page);
		return lessonMapper.selectLessonsByQid(lessonVo);
	}

	@Override
	public List<Lesson> selectLessonPageByBranchNameCategoryAndPage(int qid, String branchName, String category, PageBean pageBean) {
		LessonVo lessonVo = new LessonVo();
		lessonVo.setQid(qid);
		lessonVo.setPage(pageBean);
		lessonVo.setBranchName(branchName);
		lessonVo.setCategory(category);
		return lessonMapper.selectLessonPageByBranchNameCategory(lessonVo);
	}

	@Override
	public LessonInfoVo selectLessonByOid(int oid) {
		return lessonMapper.selectLessonByOid(oid);
	}

	 
}
