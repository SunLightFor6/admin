package com.lamport.education.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamport.education.mapper.TeacherMapper;
import com.lamport.education.po.Teacher;
import com.lamport.education.service.TeacherService;
import com.lamport.education.vo.QIDAndPage;

@Service
public class TeacherServiceBean implements TeacherService {

	@Autowired
	TeacherMapper teacherMapper;
	
	@Override
	public List<Teacher> selectTeacherByQIDAndPage(QIDAndPage qidAndPage) throws Exception {
		List<Teacher> teachers = null;
		
		teachers = teacherMapper.selectTeacherByQIDAndPage(qidAndPage);
		
		return teachers;
	}

}
