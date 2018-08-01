package com.lamport.education.service;

import java.util.List;

import com.lamport.education.po.FreeListen;
import com.lamport.education.vo.FreeListenQueryCondition;

public interface FreeListenService {
	
	/**
	 * 通过fid查询FreeListen信息
	 * @param fid
	 * @return FreeListen
	 * @throws Exception
	 */
	public FreeListen selectFreeListenByFid(int fid) throws Exception;
	
	/**
	 * 通过多条件查询FreeListen信息
	 * @param freeListenQueryCondition
	 * @return List<FreeListen>
	 * @throws Exception
	 */
	public List<FreeListen> selectFreeListenByFreeListenQueryCondition(FreeListenQueryCondition freeListenQueryCondition) throws Exception;
}
