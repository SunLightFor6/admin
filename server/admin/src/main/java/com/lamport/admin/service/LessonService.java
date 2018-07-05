package com.lamport.admin.service;

/**
 * Service, 提供Lesson信息的增加、删除、更新、查询功能
 * @author Lin Zhao， protector of Sherry
 *
 */
public interface LessonService {
	/**
	 * 根据qid查询Lesson的总数
	 * @return int
	 * @throws Exception
	 */
	public int selectCountLessonByQID() throws Exception;
}
