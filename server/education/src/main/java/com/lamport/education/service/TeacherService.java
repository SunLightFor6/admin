package com.lamport.education.service;

import java.util.List;

import com.lamport.education.po.Teacher;
import com.lamport.education.util.PageBean;

public interface TeacherService {

	public List<Teacher> selectTeacherPageByQid(PageBean page, int qid) throws Exception;
	
}
