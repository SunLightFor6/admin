package com.lamport.education.mapper;

import java.util.List;

public interface SwiperMapper {
	
	public List<String> selectEnterpriseSwipersByQid(int qid) throws Exception;
	public List<String> selectTeacherSwipersByQid(int qid) throws Exception;

}
