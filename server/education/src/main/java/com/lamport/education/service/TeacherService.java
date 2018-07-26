package com.lamport.education.service;

import java.util.List;

import com.lamport.education.po.Teacher;
import com.lamport.education.vo.QIDAndPage;

public interface TeacherService {
	/**
	 * 通过qid和页码查询Teacher信息
	 * @param qidAndPage
	 * @return List<Teacher>
	 * @throws Exception
	 */
	public List<Teacher> selectTeacherByQIDAndPage(QIDAndPage qidAndPage) throws Exception;
}
