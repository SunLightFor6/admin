package com.lamport.education.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamport.education.mapper.FreeListenMapper;
import com.lamport.education.po.FreeListen;
import com.lamport.education.service.FreeListenService;
import com.lamport.education.util.PageBean;
import com.lamport.education.vo.LessonInfoVo;
import com.lamport.education.vo.LessonVo;

@Service
public class FreeListenServiceBean implements FreeListenService {

	@Autowired
    FreeListenMapper freeListenMapper;
	
	@Override
	public LessonInfoVo selectFreeListenByFid(int fid) throws Exception {
		return freeListenMapper.selectFreeListenByFid(fid);
	}

	@Override
	public List<FreeListen> selectHomePageFreeListenByQid(PageBean page, int qid) throws Exception {
		LessonVo lessonVo = new LessonVo();
		lessonVo.setQid(qid);
		lessonVo.setPage(page);
		return freeListenMapper.selectFreeListenPage(lessonVo);
	}

	@Override
	public List<FreeListen> selectFreeListenByBranchNameAndCategoryAndPage(PageBean page,int qid, String branchName, String category) throws Exception {
		LessonVo lessonVo = new LessonVo();
		lessonVo.setBranchName(branchName);
		lessonVo.setPage(page);
		lessonVo.setQid(qid);
		lessonVo.setCategory(category);
		return  freeListenMapper.selectFreeListenPageByBranchNameAndCategory(lessonVo);
	}

}
