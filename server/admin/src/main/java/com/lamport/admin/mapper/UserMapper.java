package com.lamport.admin.mapper;

/**
 * Mapper, 提供User信息的增加、删除、更新、查询功能
 * @author Lin Zhao， protector of Sherry
 *
 */
public interface UserMapper {
	/**
	 * 查询User的总数
	 * @return int
	 * @throws Exception
	 */
	public int selectCountUser() throws Exception;
}
