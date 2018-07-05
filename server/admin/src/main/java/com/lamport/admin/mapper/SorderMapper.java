package com.lamport.admin.mapper;

/**
 * Mapper, 提供Sorder信息的增加、删除、更新、查询功能
 * @author Lin Zhao， protector of Sherry
 *
 */
public interface SorderMapper {
	/**
	 * 根据qid查询Sorder的总数
	 * @return int
	 * @throws Exception
	 */
	public int selectCountSorderByQID() throws Exception;
	/**
	 * 根据qid查询Sorder的订单总额
	 * @return double
	 * @throws Exception
	 */
	public double selectCountSorderActualByQID() throws Exception;
}
