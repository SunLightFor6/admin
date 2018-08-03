package com.lamport.admin.service;

import java.util.List;

import com.lamport.admin.po.Coupon;
import com.lamport.admin.vo.CouponQueryCondition;
import com.lamport.admin.vo.MeetingCondition;

/**
 * Service, 提供Coupon及其相关页面所需信息的增加、删除、更新、查询功能
 * @author Lin Zhao， protector of Sherry
 *
 */
public interface CouponService {
	
	/**
	 * 创建Coupon信息
	 * @param coupon
	 * @throws Exception
	 */
	public void saveCoupon(Coupon coupon) throws Exception;
	/**
	 * 通过cid逻辑删除Coupon信息
	 * @param coupon
	 * @throws Exception
	 */
	public void deleteCouponLogicallyByCID(Coupon coupon) throws Exception;
	/**
	 * 通过cid修改coupon的deadline信息
	 * @param coupon
	 * @throws Exception
	 */
	public void updateCouponDeadLineByCID(Coupon coupon) throws Exception;
	/**
	 * 通过相关条件修改Coupon信息
	 * @param meetingCondition
	 * @throws Exception
	 */
	public void updateCouponByMeetingCondition(MeetingCondition meetingCondition) throws Exception;
	/**
	 * 通过多条件查询Coupon信息
	 * @return List
	 * @throws Exception
	 */
	public List<Coupon> selectCouponsByCouponQueryCondition(CouponQueryCondition couponQueryCondition) throws Exception;
	/**
	 * 通过相关条件查询User总数
	 * @param meetingCondition
	 * @return int
	 * @throws Exception
	 */
	public int selectCountUserByMeetingCondition(MeetingCondition meetingCondition) throws Exception;
	/**
	 * 通过qid查询Lesson和FreeListen的category
	 * @param qid
	 * @return List
	 * @throws Exception
	 */
	public List<String> selectCategoryByQID(int qid) throws Exception;
}
