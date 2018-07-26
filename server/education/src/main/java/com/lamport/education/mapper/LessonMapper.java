package com.lamport.education.mapper;

import java.util.List;

import com.lamport.education.po.Lesson;
import com.lamport.education.vo.LessonInfoVo;
import com.lamport.education.vo.LessonQueryCondition;
import com.lamport.education.vo.LessonVo;

public interface LessonMapper {
	
	public LessonInfoVo selectLessonByLid(int lid) throws Exception;
	public LessonInfoVo selectLessonByOid(int oid);
	
	/**
	 * 通过多条件查询Lesson信息
	 * @param lessonQueryCondition
	 * @return List<Lesson>
	 * @throws Exception
	 */
	public List<Lesson> selectLessonByLessonQueryCondition(LessonQueryCondition lessonQueryCondition) throws Exception;
	public List<Lesson> selectHomePageLessonByQid(LessonVo lessonVo) throws Exception;
	
}
