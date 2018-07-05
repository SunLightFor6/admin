package com.lamport.admin.service;

/**
 * Service, 提供FreeListen信息的增加、删除、更新、查询功能
 * @author Lin Zhao， protector of Sherry
 *
 */
public interface FreeListenService {
	/**
	 * 根据qid查询FreeListen的总数
	 * @return int
	 * @throws Exception
	 */
	public int selectCountFreeListenByQID() throws Exception;
}