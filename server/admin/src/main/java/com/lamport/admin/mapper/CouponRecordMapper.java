package com.lamport.admin.mapper;

import java.util.List;

import com.lamport.admin.po.CouponRecord;

/**
 * Mapper, 提供CouponRecord信息的增加、删除、修改、查询功能
 * @author Lin Zhao， protector of Sherry
 *
 */
public interface CouponRecordMapper {

	/**
	 * 批量创建CouponRecord信息
	 * @param couponRecords
	 * @throws Exception
	 */
	public void saveMultiCouponRecord(List<CouponRecord> couponRecords) throws Exception;
	
	/**
	 * 通过cid逻辑删除CouponRecord信息
	 * @param cid
	 * @throws Exception
	 */
	public void deleteCouponRecordLogicallyByCID(int cid) throws Exception;
}
