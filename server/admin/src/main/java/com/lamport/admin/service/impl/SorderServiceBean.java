package com.lamport.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamport.admin.mapper.PointMapper;
import com.lamport.admin.mapper.SorderMapper;
import com.lamport.admin.mapper.UserMapper;
import com.lamport.admin.po.Point;
import com.lamport.admin.po.Sorder;
import com.lamport.admin.po.User;
import com.lamport.admin.service.SorderService;
import com.lamport.admin.vo.SorderQueryCondition;

/**
 * implements SorderService
 * @author Lin Zhao, protector of Sherry
 *
 */
@Service
public class SorderServiceBean implements SorderService {

	@Autowired
	private SorderMapper sorderMapper;
	@Autowired
	private PointMapper pointMapper;
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public int updateSorderByID(Sorder sorder) throws Exception {
		System.out.println("..........SorderServiceBean..........updateSorderByID()..........");

		int updateResult = 1;
		
		updateResult = sorderMapper.updateSorderByID(sorder);
		
		return updateResult;
	}

	@Override
	public int updateRefundByID(Sorder sorder) throws Exception {
		System.out.println("..........SorderServiceBean..........updateRefundByID()..........");
		int updateResult = 1;
		
		//删除积分记录，并计算该减去的分数
		List<Point> points = pointMapper.selectPointLogicallyByOID(sorder.getOid());
		int reducePoint = 0;
		int uid = 0;
		if(points!=null && !points.isEmpty()){
			for(Point point : points){
				if(point.getPoint()>0){
					uid = point.getUid();
					reducePoint = reducePoint + point.getPoint();
				}
				pointMapper.deletePointLogicallyByPID(point.getPid());
			}
		}
		updateResult *= sorderMapper.updateSorderByID(sorder);
		updateResult *= sorderMapper.updateRefundByID(sorder.getRefund());
		//退款后，修改user的totalpoint
		User user  = userMapper.selectUserByUID(uid);
		if(user!=null && reducePoint!=0){
			int totalpoint = user.getTotalpoint() - reducePoint;
			user.setTotalpoint(totalpoint);
			userMapper.updateUserTotalpointByUID(user);
		}
		
		return updateResult;
	}

	@Override
	public List<Sorder> selectSorderBySorderQueryCondition(SorderQueryCondition sorderQueryCondition) throws Exception {
		System.out.println("..........SorderServiceBean..........selectSorderBySorderQueryCondition()..........");

		List<Sorder> sorders = null;
		
		int count = sorderMapper.selectCountSorderBySorderQueryCondition(sorderQueryCondition);
		sorderQueryCondition.getPageTool().setCount(count);
		sorders = sorderMapper.selectSorderBySorderQueryCondition(sorderQueryCondition);
		
		return sorders;
	}

	@Override
	public int selectCountSorderByQID(int qid) throws Exception {
		System.out.println("..........SorderServiceBean..........selectCountSorderByQID()..........");

		int countSorder = 0;
		
		countSorder = sorderMapper.selectCountSorderByQID(qid);
		
		return countSorder;
	}

	@Override
	public double selectCountSorderActualByQID(int qid) throws Exception {
		System.out.println("..........SorderServiceBean..........selectCountSorderActualByQID()..........");
		double countSorderActual = 0;
		
		Double count = sorderMapper.selectCountSorderActualByQID(qid);
		if(count == null){
			countSorderActual = 0;
		}else{
			countSorderActual = count.doubleValue();
		}
		
		return countSorderActual;
	}

}
