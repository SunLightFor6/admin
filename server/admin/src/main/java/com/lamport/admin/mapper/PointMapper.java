package com.lamport.admin.mapper;

import java.util.List;

import com.lamport.admin.po.User;

/**
 * Mapper, 提供Point信息的删除功能
 * @author Lin Zhao， protector of Sherry
 *
 */
public interface PointMapper {

	/**
	 * 通过uid批量逻辑删除Point信息
	 * @param uid
	 * @throws Exception
	 */
	public void deleteMultiPointLogicallyByUID(List<User> users) throws Exception;
}
