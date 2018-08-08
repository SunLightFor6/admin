package com.lamport.admin.mapper;

import java.util.List;

import com.lamport.admin.po.FreeListen;
import com.lamport.admin.po.FreeListenBook;
import com.lamport.admin.vo.BookQueryCondition;
import com.lamport.admin.vo.QIDAndPage;
import com.lamport.admin.vo.StatisticsQueryResult;

/**
 * Mapper, 提供FreeListenBook信息的删除、修改、查询功能
 * @author Lin Zhao， protector of Sherry
 *
 */
public interface FreeListenBookMapper {
	/**
	 * 通过fid逻辑删除FreeListenBook信息
	 * @param id
	 * @return 1 删除成功 0 删除失败
	 * @throws Exception
	 */
	public int deleteFreeListenBookLogicallyByFID(int fid) throws Exception;
	/**
	 * 通过fid批量逻辑删除FreeListenBook信息
	 * @param freeListens
	 * @throws Exception
	 */
	public void deleteMultiFreeListenBookLogicallyByFID(List<FreeListen> freeListens) throws Exception;
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
	/**
	 * 通过BookQueryCondition查询FreeListenBook信息
	 * @param bookQueryCondition
	 * @return int
	 * @throws Exception
	 */
	public int selectCountFreeListenBookByBookQueryCondition(BookQueryCondition bookQueryCondition) throws Exception;
	/**
	 * 通过qid和条数按月查询Book总数
	 * @param qidAndPage
	 * @return List
	 * @throws Exception
	 */
	public List<StatisticsQueryResult> selectMonthCountBookByQIDAndPage(QIDAndPage qidAndPage) throws Exception;
}
