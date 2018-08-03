package com.lamport.education.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamport.education.mapper.CouponRecordMapper;
import com.lamport.education.po.CouponRecord;
import com.lamport.education.service.CouponService;
import com.lamport.education.vo.UIDAndPage;

/**
 * implements CouponService
 * @author Lin Zhao, protector of Sherry
 *
 */
@Service
public class CouponServiceBean implements CouponService {

	@Autowired
	CouponRecordMapper couponRecordMapper;
	
	@Override
	public List<CouponRecord> selectCouponRecordByUIDAndPage(UIDAndPage uidAndPage) throws Exception {
		List<CouponRecord> couponRecords = couponRecordMapper.selectCouponRecordByUIDAndPage(uidAndPage);
		return couponRecords;
	}

}
