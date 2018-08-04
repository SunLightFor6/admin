package com.lamport.education.service;

import java.util.List;

import com.lamport.education.po.Point;
import com.lamport.education.vo.UIDAndPage;

/**
 * Service, 提供Point信息的查询功能
 * @author Lin Zhao, protector of Sherry
 *
 */
public interface PointService {

	/**
	 * 通过uid和页码查询Point信息
	 * @param uidAndPage
	 * @return List
	 * @throws Exception
	 */
	public List<Point> selectPointByUIDAndPage(UIDAndPage uidAndPage) throws Exception;
}
