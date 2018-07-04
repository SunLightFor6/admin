package com.lamport.admin.service;

/**
 * Service, 提供FreeListenBook信息的增加、删除、更新、查询功能
 * @author Lin Zhao， protector of Sherry
 *
 */
public interface FreeListenBookService {
	/**
	 * 查询FreeListenBook的总数
	 * @return int
	 * @throws Exception
	 */
	public int selectCountFreeListenBook() throws Exception;
}

