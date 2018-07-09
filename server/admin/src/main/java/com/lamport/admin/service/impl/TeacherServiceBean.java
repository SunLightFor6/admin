package com.lamport.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamport.admin.mapper.TeacherMapper;
import com.lamport.admin.po.Teacher;
import com.lamport.admin.service.TeacherService;
import com.lamport.admin.vo.QIDAndPage;

/**
 * implements TeacherService
 * @author Lin Zhao, protector of Sherry
 *
 */
@Service
public class TeacherServiceBean implements TeacherService {

	@Autowired
	private TeacherMapper teacherMapper;
	
	@Override
	public int saveTeacher(Teacher teacher) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteTeacherLogicallyByID(int id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateTeacherByID(Teacher teacher) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Teacher> selectTeacherByQIDAndPage(QIDAndPage qidAndPage) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectCountTeacherByQID(int qid) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
