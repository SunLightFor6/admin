package com.lamport.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamport.admin.mapper.CouponMapper;
import com.lamport.admin.mapper.CouponRecordMapper;
import com.lamport.admin.po.Coupon;
import com.lamport.admin.po.CouponRecord;
import com.lamport.admin.service.CouponService;
import com.lamport.admin.vo.CouponQueryCondition;
import com.lamport.admin.vo.MeetingCondition;

/**
 * implements CouponService
 * @author Lin Zhao, protector of Sherry
 *
 */
@Service
public class CouponServiceBean implements CouponService {

	@Autowired
	CouponMapper couponMapper;
	@Autowired
	CouponRecordMapper couponRecordMapper;
	
	@Override
	public void saveCoupon(Coupon coupon) throws Exception {
		System.out.println("..........CouponServiceBean..........saveCoupon()..........");
		
		coupon.setGet(0);
		coupon.setDisabledkey(0);
		coupon.setDeletekey(0);
		couponMapper.saveCoupon(coupon);
	}

	@Override
	public void deleteCouponLogicallyByCID(Coupon coupon) throws Exception {
		System.out.println("..........CouponServiceBean..........deleteCouponLogicallyByCID()..........");
		
		coupon.setDisabledkey(1);
		coupon.setDeletekey(1);
		couponMapper.deleteCouponLogicallyByCID(coupon);;
	}
	
	@Override
	public void updateCouponDeadLineByCID(Coupon coupon) throws Exception{
		System.out.println("..........CouponServiceBean..........updateCouponDeadLineByCID()..........");
		
		couponMapper.updateCouponDeadLineByCID(coupon);
	}

	@Override
	public void updateCouponByMeetingCondition(MeetingCondition meetingCondition) throws Exception {
		System.out.println("..........CouponServiceBean..........updateCouponByMeetingCondition()..........");
		
		List<Integer> uids = couponMapper.selectUserUIDByMeetingCondition(meetingCondition);
		Coupon coupon = new Coupon();
		coupon.setCid(meetingCondition.getCid());
		coupon.setGet(uids.size());
		List<CouponRecord> couponRecords = new ArrayList<CouponRecord>();
		if(uids!=null && !uids.isEmpty()){
			for(Integer uid : uids){
				CouponRecord couponRecord = new CouponRecord();
				couponRecord.setCid(meetingCondition.getCid());
				couponRecord.setUid(uid.intValue());
				couponRecord.setDeletekey(0);
				couponRecords.add(couponRecord);
			}
			couponRecordMapper.saveMultiCouponRecord(couponRecords);
		}
	}

	@Override
	public List<Coupon> selectCouponsByCouponQueryCondition(CouponQueryCondition couponQueryCondition)
			throws Exception {
		System.out.println("..........CouponServiceBean..........selectCouponsByCouponQueryCondition()..........");
		List<Coupon> coupons = null;
		
		coupons = couponMapper.selectCouponsByCouponQueryCondition(couponQueryCondition);
		
		return coupons;
	}

	@Override
	public int selectCountUserByMeetingCondition(MeetingCondition meetingCondition) throws Exception {
		System.out.println("..........CouponServiceBean..........selectCountUserByMeetingCondition()..........");
		int count = 0;
		
		count = couponMapper.selectCountUserByMeetingCondition(meetingCondition);
		
		return count;
	}

	@Override
	public List<String> selectCategoryByQID(int qid) throws Exception {
		System.out.println("..........CouponServiceBean..........selectCategoryByQID()..........");
		List<String> categories = null;
		
		categories = couponMapper.selectCategoryByQID(qid);
		
		return categories;
	}

}
