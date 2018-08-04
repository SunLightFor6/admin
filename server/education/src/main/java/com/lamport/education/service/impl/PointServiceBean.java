package com.lamport.education.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamport.education.mapper.PointMapper;
import com.lamport.education.po.Point;
import com.lamport.education.service.PointService;
import com.lamport.education.vo.UIDAndPage;

/**
 * implements CouponService
 * @author Lin Zhao, protector of Sherry
 *
 */
@Service
public class PointServiceBean implements PointService {

	@Autowired
	PointMapper pointMapper;
	
	@Override
	public List<Point> selectPointByUIDAndPage(UIDAndPage uidAndPage) throws Exception {
		List<Point> points = pointMapper.selectPointByUIDAndPage(uidAndPage);
		return points;
	}

}
