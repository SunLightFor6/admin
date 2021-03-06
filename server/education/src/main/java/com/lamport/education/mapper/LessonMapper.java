package com.lamport.education.mapper;

import java.util.List;

import com.lamport.education.po.Lesson;
import com.lamport.education.vo.LessonQueryCondition;
import com.lamport.education.vo.QIDAndPage;

public interface LessonMapper {
	
	/**
	 * 通过lid查询Lesson信息
	 * @param lid
	 * @return Lesson
	 * @throws Exception
	 */
	public Lesson selectLessonByLid(int lid) throws Exception;
	
	/**
	 * 通过oid查询Lesson信息
	 * @param oid
	 * @return Lesson
	 * @throws Exception
	 */
	public Lesson selectLessonByOid(int oid);
	
	/**
	 * 通过多条件查询Lesson信息
	 * @param lessonQueryCondition
	 * @return List<Lesson>
	 * @throws Exception
	 */
	public List<Lesson> selectLessonByLessonQueryCondition(LessonQueryCondition lessonQueryCondition) throws Exception;
	
	/**
	 * 根据qid和页码查询Lesson信息
	 * @param qidAndPage
	 * @return List<Lesson>
	 * @throws Exception
	 */
	public List<Lesson> selectLessonByQIDAndPage(QIDAndPage qidAndPage) throws Exception;
	
}
