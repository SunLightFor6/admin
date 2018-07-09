package com.lamport.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamport.admin.mapper.FreeListenBookMapper;
import com.lamport.admin.mapper.SorderMapper;
import com.lamport.admin.po.FreeListenBook;
import com.lamport.admin.service.FreeListenBookService;
import com.lamport.admin.vo.BookQueryCondition;

/**
 * implements FreeListenBookService
 * @author Lin Zhao, protector of Sherry
 *
 */
@Service
public class FreeListenBookServiceBean implements FreeListenBookService {

	@Autowired
	private FreeListenBookMapper freeListenBookMapper;
	
	@Override
	public int updateFreeListenBookByID(FreeListenBook freeListenBook) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<FreeListenBook> selectFreeListenBookByBookQueryCondition(BookQueryCondition bookQueryCondition)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectCountFreeListenBookByQID(int qid) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
