package com.lamport.education.mapper;

import java.util.List;

import com.lamport.education.po.FreeListen;
import com.lamport.education.vo.LessonInfoVo;
import com.lamport.education.vo.LessonVo;

public interface FreeListenMapper {
	
	public LessonInfoVo selectFreeListenByFid(int fid) throws Exception;
	public List<FreeListen> selectFreeListenPageByQidAndBidAndCategory(LessonVo lessonVo) throws Exception;
	public List<FreeListen> selectFreeListenByPage(LessonVo lessonVo) throws Exception;
	public List<FreeListen> selectFreeListenPageByBranchNameAndCategory(LessonVo lessonVo);
}
