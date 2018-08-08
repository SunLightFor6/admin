package com.lamport.admin.mapper;

import java.util.List;

import com.lamport.admin.po.User;

/**
 * Mapper, 提供Sign信息的删除功能
 * @author Lin Zhao， protector of Sherry
 *
 */
public interface SignMapper {
	
	/**
	 * 通过uid批量逻辑删除Sign信息
	 * @param uid
	 * @throws Exception
	 */
	public void deleteMultiSignLogicallyByUID(List<User> users) throws Exception;
}
