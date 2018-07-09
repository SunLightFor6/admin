package com.lamport.admin.service;

import java.util.List;

import com.lamport.admin.po.Sorder;
import com.lamport.admin.vo.SorderQueryCondition;

/**
 * Service, 提供Sorder信息的更新、查询功能
 * @author Lin Zhao， protector of Sherry
 *
 */
public interface SorderService {
	/**
	 * 通过id修改Sorder信息
	 * @param sorder
	 * @return 1 更新成功 0 更新失败
	 * @throws Exception
	 */
	public int updateSorderByID(Sorder sorder) throws Exception;
	/**
	 * 通过id修改Refund和相应的Sorder信息
	 * @param sorder
	 * @return 1 更新成功 0 更新失败
	 * @throws Exception
	 */
	public int updateRefundByID(Sorder sorder) throws Exception;
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
	 * 根据qid查询Sorder的订单总额
	 * @return double
	 * @throws Exception
	 */
	public double selectCountSorderActualByQID(int qid) throws Exception;
}

