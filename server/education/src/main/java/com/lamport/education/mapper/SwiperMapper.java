package com.lamport.education.mapper;

import java.util.List;

public interface SwiperMapper {
	public List<String> selectAllSwiperByQid(int qid) throws Exception;
}
