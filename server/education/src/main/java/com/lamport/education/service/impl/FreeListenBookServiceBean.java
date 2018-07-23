package com.lamport.education.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamport.education.mapper.FreeListenBookMapper;
import com.lamport.education.po.Address;
import com.lamport.education.po.FreeListenBook;
import com.lamport.education.vo.BusinessVo;
import com.lamport.education.vo.FreeListenBookVo;
import com.lamport.education.service.FreeListenBookService;
import com.lamport.education.util.PageBean;
@Service
public class FreeListenBookServiceBean implements FreeListenBookService {
	@Autowired
	FreeListenBookMapper freeListenBookMapper;
	@Override
	public void saveFreeListenBook(FreeListenBook freeListenBook) throws Exception {
		freeListenBookMapper.saveFreeListenBook(freeListenBook);
	}

	@Override
	public List<FreeListenBookVo> selectFreeListenBookPageByUid(PageBean page, int uid) throws Exception {
		BusinessVo  businessVo = new BusinessVo();
		businessVo.setPage(page);
		businessVo.setStatus("");
		businessVo.setUid(uid);
		List<FreeListenBookVo> freeBooks = freeListenBookMapper.selectFreeListenBookPageByUid(businessVo);
		return freeBooks;
	}

	@Override
	public List<FreeListenBookVo> selectFreeListenBookPageByUidAndStatus(PageBean page, int uid, String status)throws Exception {
		BusinessVo  businessVo = new BusinessVo();
		businessVo.setPage(page);
		businessVo.setStatus(status);
		businessVo.setUid(uid);
		List<FreeListenBookVo> freeBooks = freeListenBookMapper.selectFreeListenBookPageByUidAndStatus(businessVo);
		return freeBooks;
	}

	@Override
	public void deleteBookRequestLogicallyByFbid(int fbid) throws Exception {
		freeListenBookMapper.deleteBookRequestLogicallyByFbid(fbid);
	}

	@Override
	public void deleteFinalFreeListenBookLogicallyByFbid(int fbid) throws Exception {
		freeListenBookMapper.deleteFinalFreeListenBookLogicallyByFbid(fbid);

	}



}
