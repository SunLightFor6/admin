package com.lamport.admin.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lamport.admin.po.Lesson;
import com.lamport.admin.vo.LessonQueryCondition;

/**
 * Service, 提供Lesson(精品课)信息的增加、删除、修改、查询功能
 * @author Lin Zhao， protector of Sherry
 *
 */
public interface LessonService {
	/**
	 * 创建Lesson
	 * @param lesson
	 * @return 1 保存成功 0 保存失败
	 * @throws Exception
	 */
	public int saveLesson(Lesson lesson, MultipartFile img, String path) throws Exception;
	/**
	 * 通过id逻辑删除Lesson信息
	 * @param id
	 * @return 1 删除成功 0 删除失败
	 * @throws Exception
	 */
	public int deleteLessonLogicallyByID(int id, int qid) throws Exception;
	/**
	 * 通过id修改Lesson信息
	 * @param lesson
	 * @return 1 更新成功 0 更新失败
	 * @throws Exception
	 */
	public int updateLessonByID(Lesson lesson, MultipartFile img, String path) throws Exception;
	/**
	 * 通过多条件查询Lesson信息
	 * @return List
	 * @throws Exception
	 */
	public List<Lesson> selectLessonByLessonQueryCondition(LessonQueryCondition lessonQueryCondition) throws Exception;
	/**
	 * 通过qid查询Lesson的总数
	 * @return int
	 * @throws Exception
	 */
	public int selectCountLessonByQID(int qid) throws Exception;
}
