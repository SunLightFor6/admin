package com.lamport.education.service;

import com.lamport.education.po.Sign;
import com.lamport.education.po.User;

/**
 * Service, 提供Sign信息的增加、查询功能
 * @author Lin Zhao, protector of Sherry
 *
 */
public interface SignService {
	
	/**
	 * 创建Sign信息
	 * @param sign
	 * @return int 0 签到失败 1 签到成功 2 重复签到（今日已经签到过）
	 * @throws Exception
	 */
	public int saveSign(Sign sign, User user) throws Exception;
	
	/**
	 * 通过uid查询该用户连续签到的天数
	 * @param uid
	 * @return int
	 * @throws Exception
	 */
	public int selectDaysByUID(int uid) throws Exception;
}
