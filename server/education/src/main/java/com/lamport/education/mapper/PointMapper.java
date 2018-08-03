package com.lamport.education.mapper;

import java.util.List;

import com.lamport.education.po.Point;
import com.lamport.education.vo.UIDAndPage;

/**
 * Mapper, 提供Point(积分)信息的增加、查询功能
 * @author Lin Zhao， protector of Sherry
 *
 */
public interface PointMapper {
	
	/**
	 * 创建Point信息
	 * @param point
	 * @throws Exception
	 */
	public void savePoint(Point point) throws Exception;

	/**
	 * 通过uid和页码查询Point信息
	 * @param uidAndPage
	 * @return List
	 * @throws Exception
	 */
	public List<Point> selectPointByUIDAndPage(UIDAndPage uidAndPage) throws Exception;
}
