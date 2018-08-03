package com.lamport.education.mapper;

import com.lamport.education.po.Sign;

/**
 * Mapper, 提供Sign(签到)信息的增加、查询功能
 * @author Lin Zhao， protector of Sherry
 *
 */
public interface SignMapper {
	
	/**
	 * 创建Sign信息
	 * @param sign
	 * @throws Exception
	 */
	public void saveSign(Sign sign) throws Exception;

	/**
	 * 通过uid查询最近一次的签到
	 * @param uid
	 * @return Sign
	 * @throws Exception
	 */
	public Sign selectLastSignByUID(int uid) throws Exception;
	
	/**
	 * 通过uid查询该用户连续签到的天数
	 * @param uid
	 * @return int
	 * @throws Exception
	 */
	public int selectDaysByUID(int uid) throws Exception;
}
