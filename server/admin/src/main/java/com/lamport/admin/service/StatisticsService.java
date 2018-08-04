package com.lamport.admin.service;

import java.util.List;

import com.lamport.admin.vo.QIDAndPage;
import com.lamport.admin.vo.StatisticsQueryResult;

/**
 * Service, 提供统计信息的查询功能
 * @author Lin Zhao， protector of Sherry
 *
 */
public interface StatisticsService {

	/**
	 * 通过qid和条数按月查询Sorder总数和Book总数
	 * @param qidAndPage
	 * @return List
	 * @throws Exception
	 */
	public List<StatisticsQueryResult> selectMonthCountSorderAndBookByQIDAndPage(QIDAndPage qidAndPage) throws Exception;
	
	/**
	 * 通过qid和条数按天查询Sorder的订单总额
	 * @param qidAndPage
	 * @return List
	 * @throws Exception
	 */
	public List<StatisticsQueryResult> selectDayCountSorderActualByQIDAndPage(QIDAndPage qidAndPage) throws Exception;
}
