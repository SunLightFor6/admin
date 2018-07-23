package com.lamport.education.service;

import java.util.List;

import com.lamport.education.po.FreeListen;
import com.lamport.education.util.PageBean;
import com.lamport.education.vo.LessonInfoVo;

public interface FreeListenService {
	
	public LessonInfoVo  selectFreeListenByFid(int fid) throws Exception;
	public List<FreeListen> selectFreeListenPage(PageBean page, int qid) throws Exception;
	public List<FreeListen> selectFreeListenPageByBidAndCategory(PageBean page, int bid, String category) throws Exception;
	public List<FreeListen> selectFreeListenPageByBranchNameAndCategory(PageBean page,int qid, String branchName, String category) throws Exception;

}
