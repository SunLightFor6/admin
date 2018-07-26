package com.lamport.education.mapper;

import java.util.List;

import com.lamport.education.po.Teacher;
import com.lamport.education.vo.QIDAndPage;

public interface TeacherMapper {
	
	public List<Teacher> selectTeacherByQIDAndPage(QIDAndPage qidAndPage) throws Exception;

}
