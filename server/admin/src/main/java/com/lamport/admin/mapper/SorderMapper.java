package com.lamport.admin.mapper;

/**
 * Mapper, 提供Sorder信息的删除、更新、查询功能
 * @author Lin Zhao， protector of Sherry
 *
 */
public interface SorderMapper {
	/**
	 * 通过lid逻辑删除Sorder的信息
	 * @return 1 删除成功 0 删除失败
	 * @throws Exception
	 */
	public int deleteSorderLogicallyByLID(int lid) throws Exception;
	/**
	 * 通过branchid逻辑删除Sorder的信息
	 * @return 1 删除成功 0 删除失败
	 * @throws Exception
	 */
	public int deleteSorderLogicallyByBranchID(int branchid) throws Exception;
	/**
	 * 通过qid逻辑删除Sorder的信息
	 * @return 1 删除成功 0 删除失败
	 * @throws Exception
	 */
	public int deleteSorderLogicallyByQID(int qid) throws Exception;
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
