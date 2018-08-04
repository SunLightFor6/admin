package com.lamport.education.service;

import java.util.List;

import com.lamport.education.po.CouponRecord;
import com.lamport.education.vo.UIDAndPage;

/**
 * Service, 提供Coupon信息的查询功能
 * @author Lin Zhao, protector of Sherry
 *
 */
public interface CouponService {

	/**
	 * 通过uid和页码查询CouponRecord信息
	 * @param uidAndPage
	 * @return List
	 * @throws Exception
	 */
	public List<CouponRecord> selectCouponRecordByUIDAndPage(UIDAndPage uidAndPage) throws Exception;
}
