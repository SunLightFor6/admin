package com.lamport.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamport.admin.mapper.FreeListenBookMapper;
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
		System.out.println("..........FreeListenBookServiceBean..........updateFreeListenBookByID()..........");

		int updateResult = 1;
		
		updateResult = freeListenBookMapper.updateFreeListenBookByID(freeListenBook);
		
		return updateResult;
	}

	@Override
	public List<FreeListenBook> selectFreeListenBookByBookQueryCondition(BookQueryCondition bookQueryCondition)
			throws Exception {
		System.out.println("..........FreeListenBookServiceBean..........selectFreeListenBookByBookQueryCondition()..........");

		List<FreeListenBook> freeListenBooks = null;
		
		int count = freeListenBookMapper.selectCountFreeListenBookByQID(bookQueryCondition.getQid());
		bookQueryCondition.getPageTool().setCount(count);
		freeListenBooks = freeListenBookMapper.selectFreeListenBookByBookQueryCondition(bookQueryCondition);
		System.out.println(freeListenBooks.size());
		
		return freeListenBooks;
	}

	@Override
	public int selectCountFreeListenBookByQID(int qid) throws Exception {
		System.out.println("..........FreeListenBookServiceBean..........selectCountFreeListenBookByQID()..........");

		int countBook = 0;
		
		countBook = freeListenBookMapper.selectCountFreeListenBookByQID(qid);
		
		return countBook;
	}

}
