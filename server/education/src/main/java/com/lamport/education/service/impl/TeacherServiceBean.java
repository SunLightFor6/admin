package com.lamport.education.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamport.education.mapper.TeacherMapper;
import com.lamport.education.po.Teacher;
import com.lamport.education.service.TeacherService;
import com.lamport.education.util.PageBean;
import com.lamport.education.vo.EnterpriseInfoVo;
@Service
public class TeacherServiceBean implements TeacherService {
	@Autowired
	TeacherMapper teacherMapper;
	@Override
	public List<Teacher> selectTeacherPageByQid(PageBean page, int qid) throws Exception {
		EnterpriseInfoVo enterpriseInfoVo = new EnterpriseInfoVo();
		enterpriseInfoVo.setPage(page);
		enterpriseInfoVo.setQid(qid);
		return teacherMapper.selectTeacherPageByQid(enterpriseInfoVo);
	}

}
