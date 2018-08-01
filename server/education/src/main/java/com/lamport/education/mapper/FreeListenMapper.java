package com.lamport.education.mapper;

import java.util.List;

import com.lamport.education.po.FreeListen;
import com.lamport.education.vo.FreeListenQueryCondition;
import com.lamport.education.vo.QIDAndPage;

public interface FreeListenMapper {
	
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

	/**
	 * 通过qid和页码查询FreeListen信息
	 * @param qidAndPage
	 * @return List<FreeListen>
	 * @throws Exception
	 */
	public List<FreeListen> selectFreeListenByQIDAndPage(QIDAndPage qidAndPage) throws Exception;
}
