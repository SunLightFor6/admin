package com.lamport.admin.mapper;

import java.util.List;

import com.lamport.admin.po.Point;
import com.lamport.admin.po.User;

/**
 * Mapper, 提供Point信息的删除功能
 * @author Lin Zhao， protector of Sherry
 *
 */
public interface PointMapper {
	/**
	 * 通过pid逻辑删除Point信息
	 * @param pid
	 * @throws Exception
	 */
	public void deletePointLogicallyByPID(int pid) throws Exception;
	/**
	 * 通过uid批量逻辑删除Point信息
	 * @param uid
	 * @throws Exception
	 */
	public void deleteMultiPointLogicallyByUID(List<User> users) throws Exception;
	/**
	 * 通过oid查询Point信息
	 * @param oid
	 * @return List
	 * @throws Exception
	 */
	public List<Point> selectPointLogicallyByOID(int oid) throws Exception;
}
