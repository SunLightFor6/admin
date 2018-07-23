package com.lamport.education.mapper;

import java.util.List;

import com.lamport.education.po.Lesson;
import com.lamport.education.vo.LessonInfoVo;
import com.lamport.education.vo.LessonVo;

public interface LessonMapper {
	
	public LessonInfoVo selectLessonByLid(int lid) throws Exception;
	public List<Lesson> selectLessonPageByQidAndBidAndCategory(LessonVo lessonVo) throws Exception;
	public List<Lesson> selectLessonsByQid(LessonVo lessonVo) throws Exception;
	public List<Lesson> selectLessonPageByBranchNameCategory(LessonVo lessonVo);
	public LessonInfoVo selectLessonByOid(int oid);

	
}
