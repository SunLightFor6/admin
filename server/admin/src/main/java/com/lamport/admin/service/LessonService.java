package com.lamport.admin.service;

import java.util.List;

import com.lamport.admin.po.Lesson;
import com.lamport.admin.vo.QIDAndPage;

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
	public int saveLesson(Lesson lesson) throws Exception;
	/**
	 * 通过id删除Lesson信息
	 * @param id
	 * @return 1 删除成功 0 删除失败
	 * @throws Exception
	 */
	public int deleteLessonByID(int id) throws Exception;
	/**
	 * 通过id更新Lesson信息
	 * @param lesson
	 * @return 1 更新成功 0 更新失败
	 * @throws Exception
	 */
	public int updateLessonByID(Lesson lesson) throws Exception;
	/**
	 * 通过qid和页码查询Lesson信息
	 * @return List
	 * @throws Exception
	 */
	public List<Lesson> selectLessonByQIDAndPage(QIDAndPage qidAndPage) throws Exception;
	/**
	 * 通过qid查询Lesson的总数
	 * @return int
	 * @throws Exception
	 */
	public int selectCountLessonByQID(int qid) throws Exception;
	/**
	 * 通过id查询Lesson图片存放路径
	 * @param id
	 * @return String imgurl
	 * @throws Exception
	 */
	public String selectLessonImgurlByID(int id) throws Exception;
}