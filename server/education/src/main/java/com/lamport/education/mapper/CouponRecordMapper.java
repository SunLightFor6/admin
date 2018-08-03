package com.lamport.education.mapper;

import java.util.List;

import com.lamport.education.po.CouponRecord;
import com.lamport.education.vo.UIDAndPage;

/**
 * Mapper, 提供CouponRecord(优惠券发放记录)信息的增加、删除、修改、查询功能
 * @author Lin Zhao， protector of Sherry
 *
 */
public interface CouponRecordMapper {
	
	/**
	 * 通过id删除CouponRecord信息
	 * @param couponRecord
	 * @throws Exception
	 */
	public void deleteCouponRecordLogicallyByID(CouponRecord couponRecord) throws Exception;
	
	/**
	 * 通过id查询CouponRecord信息
	 * @param id
	 * @return CouponRecord
	 * @throws Exception
	 */
	public CouponRecord selectCouponRecordByID(int id) throws Exception;

	/**
	 * 通过uid和页码查询CouponRecord信息
	 * @param uidAndPage
	 * @return List
	 * @throws Exception
	 */
	public List<CouponRecord> selectCouponRecordByUIDAndPage(UIDAndPage uidAndPage) throws Exception;
}
