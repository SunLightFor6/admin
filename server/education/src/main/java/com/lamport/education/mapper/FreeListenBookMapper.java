package com.lamport.education.mapper;

import java.util.List;

import com.lamport.education.po.FreeListenBook;
import com.lamport.education.vo.BookQueryCondition;
import com.lamport.education.vo.UIDAndStatus;

public interface FreeListenBookMapper {

	/**
	 * 创建FreeListenBook信息
	 * @param freeListenBook
	 * @throws Exception
	 */
	public void saveFreeListenBook(FreeListenBook freeListenBook) throws Exception;	
	
	/**
	 * 通过id逻辑删除FreeListenBook信息（只更改userdeletekey）
	 * @param freeListenBook
	 * @throws Exception
	 */
	public void deleteFreeListenBookLogicallyById(FreeListenBook freeListenBook) throws Exception; 
	
	/**
	 * 通过id逻辑删除FreeListenBook信息（同时更改deletekey和userdeletekey）
	 * @param freeListenBook
	 * @throws Exception
	 */
	public void deleteFreeListenBookPowerfullyById(FreeListenBook freeListenBook) throws Exception; 
	
	/**
	 * 通过多条件查询FreeListenBook信息
	 * @param bookQueryCondition
	 * @return List<FreeListenBook>
	 * @throws Exception
	 */
	public List<FreeListenBook> selectFreeListenBookByBookQueryCondition(BookQueryCondition bookQueryCondition) throws Exception;

	/**
	 * 通过uid和status查询预约的总数
	 * @param uidAndStatus
	 * @return int
	 * @throws Exception
	 */
	public int selectCountBookByUIDAndStatus(UIDAndStatus uidAndStatus) throws Exception;
}
