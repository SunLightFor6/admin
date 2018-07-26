package com.lamport.education.service;

import java.util.List;

import com.lamport.education.po.Lesson;
import com.lamport.education.util.PageBean;
import com.lamport.education.vo.LessonInfoVo;
import com.lamport.education.vo.LessonQueryCondition;

public interface LessonService {
	
	public LessonInfoVo selectLessonByLid(int lid) throws Exception;
	public LessonInfoVo selectLessonByOid(int oid) throws Exception;
	
	/**
	 * 通过多条件查询Lesson信息
	 * @param lessonQueryCondition
	 * @return List<Lesson>
	 * @throws Exception
	 */
	public List<Lesson> selectLessonByLessonQueryCondition(LessonQueryCondition lessonQueryCondition) throws Exception;
	public List<Lesson> selectHomePageLessonByQid(PageBean pageBean, int qid)throws Exception;
	 
}
