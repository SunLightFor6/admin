package com.lamport.education.service;

import java.util.List;

import com.lamport.education.po.FreeListen;
import com.lamport.education.po.Lesson;
import com.lamport.education.vo.QIDAndPage;

/**
 * Service, 提供主页信息的查询功能
 * @author Lin Zhao, protector of Sherry
 *
 */
public interface HomeInfoService {
	
	/**
	 * 通过qid查询首页展示的精品课程信息
	 * @param qidAndPage
	 * @return List<Lesson>
	 * @throws Exception
	 */
	public List<Lesson> selectHomePageLessonByQid(QIDAndPage qidAndPage)throws Exception;
	
	/**
	 * 通过qid查询首页展示的试听课程信息
	 * @param qidAndPage
	 * @return List<FreeListen>
	 * @throws Exception
	 */
	public List<FreeListen> selectHomePageFreeListenByQid(QIDAndPage qidAndPage)throws Exception;
}
