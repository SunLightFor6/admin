package com.lamport.admin.mapper;

/**
 * Mapper, 提供Teacher信息的增加、删除、更新、查询功能
 * @author Lin Zhao， protector of Sherry
 *
 */
public interface TeacherMapper {
	/**
	 * 根据qid查询Teacher的总数
	 * @return int
	 * @throws Exception
	 */
	public int selectCountTeacherByQID() throws Exception;
}
