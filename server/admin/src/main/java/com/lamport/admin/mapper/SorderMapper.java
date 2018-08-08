package com.lamport.admin.mapper;

import java.util.List;

import com.lamport.admin.po.Refund;
import com.lamport.admin.po.Sorder;
import com.lamport.admin.vo.QIDAndPage;
import com.lamport.admin.vo.SorderQueryCondition;
import com.lamport.admin.vo.StatisticsQueryResult;

/**
 * Mapper, 提供Sorder信息的删除、修改、查询功能
 * @author Lin Zhao， protector of Sherry
 *
 */
public interface SorderMapper {
	/**
	 * 通过lid逻辑删除Sorder信息
	 * @return 1 删除成功 0 删除失败
	 * @throws Exception
	 */
	public int deleteSorderLogicallyByLID(int lid) throws Exception;
	/**
	 * 通过branchid逻辑删除Sorder信息
	 * @return 1 删除成功 0 删除失败
	 * @throws Exception
	 */
	public int deleteSorderLogicallyByBranchID(int branchid) throws Exception;
	/**
	 * 通过qid逻辑删除Sorder信息
	 * @return 1 删除成功 0 删除失败
	 * @throws Exception
	 */
	public int deleteSorderLogicallyByQID(int qid) throws Exception;
	/**
	 * 通过oid批量逻辑删除Refund信息
	 * @param sorders
	 * @throws Exception
	 */
	public void deleteMultiRefundLogicallyByOID(List<Sorder> sorders) throws Exception;
	/**
	 * 通过id修改Sorder信息
	 * @param sorder
	 * @return 1 更新成功 0 更新失败
	 * @throws Exception
	 */
	public int updateSorderByID(Sorder sorder) throws Exception;
	/**
	 * 通过id修改Refund信息
	 * @param sorder
	 * @return 1 更新成功 0 更新失败
	 * @throws Exception
	 */
	public int updateRefundByID(Refund refund) throws Exception;
	/**
	 * 通过qid查询Sorder信息
	 * @param qid
	 * @return List
	 * @throws Exception
	 */
	public List<Sorder> selectSorderByQID(int qid) throws Exception;
	/**
	 * 通过branchid查询Sorder信息
	 * @param branchid
	 * @return List
	 * @throws Exception
	 */
	public List<Sorder> selectSorderByBranchID(int branchid) throws Exception;
	/**
	 * 通过lid查询Sorder信息
	 * @param lid
	 * @return List
	 * @throws Exception
	 */
	public List<Sorder> selectSorderByLID(int lid) throws Exception;
	/**
	 * 通过SorderQueryCondition查询Sorder信息
	 * @param sorderQueryCondition
	 * @return List
	 * @throws Exception
	 */
	public List<Sorder> selectSorderBySorderQueryCondition(SorderQueryCondition sorderQueryCondition) throws Exception;
	/**
	 * 根据qid查询Sorder的总数
	 * @return int
	 * @throws Exception
	 */
	public int selectCountSorderByQID(int qid) throws Exception;
	/**
	 * 通过SorderQueryCondition查询满足条件的Sorder的总数
	 * @param sorderQueryCondition
	 * @return int
	 * @throws Exception
	 */
	public int selectCountSorderBySorderQueryCondition(SorderQueryCondition sorderQueryCondition) throws Exception;
	/**
	 * 通过qid查询Sorder的订单总额
	 * @return double
	 * @throws Exception
	 */
	public Double selectCountSorderActualByQID(int qid) throws Exception;
	/**
	 * 通过qid和条数按月查询Sorder总数
	 * @param qidAndPage
	 * @return List
	 * @throws Exception
	 */
	public List<StatisticsQueryResult> selectMonthCountSorderByQIDAndPage(QIDAndPage qidAndPage) throws Exception;
	
	/**
	 * 通过qid和条数按天查询Sorder的订单总额
	 * @param qidAndPage
	 * @return List
	 * @throws Exception
	 */
	public List<StatisticsQueryResult> selectDayCountSorderActualByQIDAndPage(QIDAndPage qidAndPage) throws Exception;
}
