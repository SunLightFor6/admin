package com.lamport.admin.service;

import java.util.List;

import com.lamport.admin.po.FreeListenBook;
import com.lamport.admin.vo.BookQueryCondition;

/**
 * Service, 提供FreeListenBook信息的修改、查询功能
 * @author Lin Zhao， protector of Sherry
 *
 */
public interface FreeListenBookService {
	/**
	 * 通过id修改FreeListenBook信息
	 * @param freeListenBook
	 * @return 1 更新成功 0 更新失败
	 * @throws Exception
	 */
	public int updateFreeListenBookByID(FreeListenBook freeListenBook) throws Exception;
	/**
	 * 通过BookQueryCondition查询FreeListenBook信息
	 * @param bookQueryCondition
	 * @return List
	 * @throws Exception
	 */
	public List<FreeListenBook> selectFreeListenBookByBookQueryCondition(BookQueryCondition bookQueryCondition) throws Exception;
	/**
	 * 根据qid查询FreeListenBook的总数
	 * @return int
	 * @throws Exception
	 */
	public int selectCountFreeListenBookByQID(int qid) throws Exception;
}

