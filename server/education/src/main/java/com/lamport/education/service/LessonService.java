package com.lamport.education.service;

import java.util.List;

import com.lamport.education.po.Lesson;
import com.lamport.education.util.PageBean;
import com.lamport.education.vo.LessonInfoVo;

public interface LessonService {
	
	public LessonInfoVo selectLessonByLid(int lid) throws Exception;
	public List<Lesson> selectLessonPageByBidAndCategory(PageBean page, int bid, String category) throws Exception;
	public List<Lesson> selectLessonsByQid(PageBean page, int qid) throws Exception;
	public List<Lesson> selectLessonPageByBranchNameCategoryAndPage(int qid, String branchName, String category, PageBean pageBean);
	public LessonInfoVo selectLessonByOid(int oid);
	 
}
