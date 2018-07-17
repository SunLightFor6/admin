package com.lamport.education.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamport.education.mapper.FreeListenMapper;
import com.lamport.education.po.Address;
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
	public List<FreeListen> selectFreeListenPageByBidAndCategory(PageBean page, int bid, String category) throws Exception {
		LessonVo lessonVo = new LessonVo();
		lessonVo.setBid(bid);
		lessonVo.setPage(page);
		lessonVo.setCategory(category);
		return  freeListenMapper.selectFreeListenPageByQidAndBidAndCategory(lessonVo);
	}

	@Override
	public List<FreeListen> selectFreeListenPage(PageBean page, int qid) throws Exception {
		LessonVo lessonVo = new LessonVo();
		lessonVo.setQid(qid);
		lessonVo.setPage(page);
		return freeListenMapper.selectFreeListenByPage(lessonVo);
	}

	@Override
	public List<FreeListen> selectFreeListenPageByBranchNameAndCategory(PageBean page,int qid, String branchName, String category) throws Exception {
		LessonVo lessonVo = new LessonVo();
		lessonVo.setBranchName(branchName);
		lessonVo.setPage(page);
		lessonVo.setQid(qid);
		lessonVo.setCategory(category);
		return  freeListenMapper.selectFreeListenPageByBranchNameAndCategory(lessonVo);
	}

}
