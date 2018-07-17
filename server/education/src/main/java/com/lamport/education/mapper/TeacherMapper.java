package com.lamport.education.mapper;

import java.util.List;

import com.lamport.education.po.Teacher;
import com.lamport.education.vo.EnterpriseInfoVo;

public interface TeacherMapper {
	
	public List<Teacher> selectTeacherPageByQid(EnterpriseInfoVo enterpriseInfoVo) throws Exception;

}
