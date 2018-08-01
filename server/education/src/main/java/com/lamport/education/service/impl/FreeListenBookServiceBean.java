package com.lamport.education.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamport.education.mapper.FreeListenBookMapper;
import com.lamport.education.po.FreeListenBook;
import com.lamport.education.vo.BookQueryCondition;
import com.lamport.education.vo.UIDAndStatus;
import com.lamport.education.service.FreeListenBookService;

@Service
public class FreeListenBookServiceBean implements FreeListenBookService {

	@Autowired
	FreeListenBookMapper freeListenBookMapper;
	
	@Override
	public void saveFreeListenBook(FreeListenBook freeListenBook) throws Exception {
		freeListenBook.setDeletekey(0);
		freeListenBook.setUserdeletekey(0);
		freeListenBookMapper.saveFreeListenBook(freeListenBook);
	}

	@Override
	public void deleteFreeListenBookLogicallyById(FreeListenBook freeListenBook) throws Exception {
		freeListenBook.setUserdeletekey(1);
		freeListenBookMapper.deleteFreeListenBookLogicallyById(freeListenBook);
	}

	@Override
	public void deleteFreeListenBookPowerfullyById(FreeListenBook freeListenBook) throws Exception {
		freeListenBook.setDeletekey(1);
		freeListenBook.setUserdeletekey(1);
		freeListenBookMapper.deleteFreeListenBookPowerfullyById(freeListenBook);
	}

	@Override
	public List<FreeListenBook> selectFreeListenBookByBookQueryCondition(BookQueryCondition bookQueryCondition) throws Exception {
		List<FreeListenBook> freeListenBooks = null;
		
		freeListenBooks = freeListenBookMapper.selectFreeListenBookByBookQueryCondition(bookQueryCondition);
		
		return freeListenBooks;
	}

	@Override
	public int selectCountBookByUIDAndStatus(UIDAndStatus uidAndStatus) throws Exception{
		int countBookUnprocessed = 0;
		
		countBookUnprocessed = freeListenBookMapper.selectCountBookByUIDAndStatus(uidAndStatus);
		
		return countBookUnprocessed;
	}
}
