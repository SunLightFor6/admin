package com.lamport.education.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamport.education.mapper.AddressMapper;
import com.lamport.education.mapper.CouponRecordMapper;
import com.lamport.education.mapper.EnterpriseMapper;
import com.lamport.education.mapper.LessonMapper;
import com.lamport.education.mapper.PointMapper;
import com.lamport.education.mapper.RefundMapper;
import com.lamport.education.mapper.SorderMapper;
import com.lamport.education.mapper.UserMapper;
import com.lamport.education.po.Address;
import com.lamport.education.po.CouponRecord;
import com.lamport.education.po.Enterprise;
import com.lamport.education.po.Lesson;
import com.lamport.education.po.Point;
import com.lamport.education.po.Refund;
import com.lamport.education.po.Sorder;
import com.lamport.education.po.User;
import com.lamport.education.service.SorderService;
import com.lamport.education.util.Config;
import com.lamport.education.vo.QIDAndBranch;
import com.lamport.education.vo.SorderQueryCondition;
import com.lamport.education.vo.UIDAndStatus;

@Service
public class SorderServiceBean implements SorderService {

	@Autowired
	SorderMapper sorderMapper;	
	@Autowired
	RefundMapper refundMapper;
	@Autowired
	AddressMapper addressMapper;
	@Autowired
	EnterpriseMapper enterpriseMapper;
	@Autowired
	CouponRecordMapper couponRecordMapper;
	@Autowired
	LessonMapper lessonMapper;
	@Autowired
	PointMapper pointMapper;
	@Autowired
	UserMapper userMapper;
	
	@Override
	public int updateSorderUnpaid(Sorder sorder, int recordid, Point pointObject, User user) throws Exception{
		int payResult = 0;
		
		int point = pointObject.getPoint();
		CouponRecord couponRecord = null;	//此次订单使用的优惠券
		Enterprise enterprise = null;		//此次订单所对应的企业
		double couponMoney = 0;				//此次订单因优惠券而减免的金额
		double discountMoney = 0;			//此次订单因积分而折扣的金额
		int usefulPoint = 0;				//此次订单的可用积分数
		Lesson lesson = null;				//此次订单所购买的课程
		//获取此订单所关联的企业
		enterprise = enterpriseMapper.selectEnterpriseByQid(user.getQid());
		//获取当前时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = df.format(System.currentTimeMillis());
		//第一步，判断total是否正确
		lesson = lessonMapper.selectLessonByLid(sorder.getLid());
		if(lesson.getLprice() != sorder.getTotal()){
			//如果total不正确
			payResult = 2;
		}
		//第二步，判断用户是否使用了优惠券
		if(payResult==0 && recordid > 0){
			//如果用户使用了优惠券
			//查询优惠券信息
			couponRecord = couponRecordMapper.selectCouponRecordByID(recordid);
			if(couponRecord==null || couponRecord.getCoupon()==null || couponRecord.getCoupon().getCategory()==null){
				//如果没有查到正确的优惠券信息
				payResult = 2;
			}else{
				//如果查到正确的优惠券信息
				//验证优惠券是否可用
				if(Config.ordinaryCouponCategory.equals(couponRecord.getCoupon().getCategory())){
					//如果优惠券是全类型的
					if(sorder.getTotal() < couponRecord.getCoupon().getNeedmoney()){
						//如果订单总额没有达到优惠券的使用门槛
						payResult = 2;
					}else{
						//如果订单总额达到优惠券的使用门槛
						couponMoney = couponRecord.getCoupon().getMoney();
					}
				}else{
					//如果优惠券不是全类型的
					if(couponRecord.getCoupon().getCategory().equals(lesson.getCategory())){
						//如果优惠券类型和课程类型相同
						if(sorder.getTotal() < couponRecord.getCoupon().getNeedmoney()){
							//如果订单总额没有达到优惠券的使用门槛
							payResult = 2;
						}else{
							//如果订单总额达到优惠券的使用门槛
							couponMoney = couponRecord.getCoupon().getMoney();
						}
					}else{
						//如果优惠券类型和课程类型不相同
						payResult = 2;
					}
				}
			}
		}
		//第三步，判断用户是否使用了积分
		if(payResult==0 && point>0){
			//如果用户使用了积分
			//校验point是否有误
			double discount = sorder.getTotal() * enterprise.getDiscountrate();//计算积分可抵扣的金额
			if(discount%enterprise.getPerpointtomoney() == 0){
				//如果可抵扣额能够整除以单积分抵扣额
				usefulPoint = (int)(discount / enterprise.getPerpointtomoney());
			}else{
				//如果可抵扣额不能够整除以单积分抵扣额
				usefulPoint = (int)(discount / enterprise.getPerpointtomoney()) + 1;
			}
			//如果可用积分数大于用户的总积分数，则将可用积分数设置为用户的总积分数
			usefulPoint = usefulPoint>user.getTotalpoint() ? user.getTotalpoint() : usefulPoint;
			if(usefulPoint != point){
				//用户使用了积分，且传来的积分与计算得到的积分不同，说明有鬼，订单存储失败
				payResult = 2;
			}else{
				//传来的point正确
				//计算此次订单因积分而折扣的金额
				discountMoney = usefulPoint * enterprise.getPerpointtomoney();
				discountMoney = discountMoney>discount ? discount : discountMoney;
			}
		}
		//第四步，判断actual是否正确
		if(payResult == 0){
			//计算此次订单的应付金额
			double money = lesson.getLprice() - couponMoney - discountMoney;
			money = money<0 ? 0 : money;
			if(money != sorder.getActual()){
				//如果acutal不正确
				payResult = 2;
			}
		}
		//第五步，存储订单等相关信息
		if(payResult == 0){
			//通过qid和branch查询branchid
			QIDAndBranch qidAndBranch = new QIDAndBranch();
			qidAndBranch.setQid(sorder.getQid());
			qidAndBranch.setBranch(sorder.getBranch());
			Address address = addressMapper.selectAddressIDByQIDAndBranch(qidAndBranch);
			//设置sorder的剩余信息
			if(address != null){
				sorder.setBranchid(address.getId());
			}
			sorder.setOrdertime(currentTime);
			//更改sorder信息
			sorderMapper.updateSorderByOID(sorder);/********************************************************************/
			//如果使用了优惠券，则删除该优惠券
			if(couponRecord!=null && couponRecord.getCoupon()!=null){
				couponRecord.setDeletekey(1);
				couponRecordMapper.deleteCouponRecordLogicallyByID(couponRecord);
			}
			//如果使用了积分，则存储积分记录
			if(point > 0){
				//建立购买课程所花费的积分的积分记录对象
				Point reducePoint = new Point();
				reducePoint.setUid(user.getUid());
				reducePoint.setPoint(-1 * point);
				reducePoint.setCategory(Config.PointCategorySorder);
				reducePoint.setOid(sorder.getOid());
				reducePoint.setSid(0);
				reducePoint.setDate(currentTime);
				reducePoint.setDeletekey(0);
				pointMapper.savePoint(reducePoint);
			}
			//计算此次消费是否可得积分
			int newpoint = (int)(sorder.getActual()/enterprise.getMoneytoperpoint());
			if(newpoint > 0){
				//如果此次消费可得积分
				Point addPoint = new Point();
				addPoint.setUid(user.getUid());
				addPoint.setPoint(newpoint);
				addPoint.setCategory(Config.PointCategorySorder);
				addPoint.setOid(sorder.getOid());
				addPoint.setSid(0);
				addPoint.setDate(currentTime);
				addPoint.setDeletekey(0);
				pointMapper.savePoint(addPoint);
			}
			//如果有积分变化，则修改update的totalpoint
			if(point>0 || newpoint>0){
				int changePoint = newpoint - point;
				if(changePoint!=0){
					//如果总积分有变化
					User tempUser = new User();
					tempUser.setUid(user.getUid());
					tempUser.setTotalpoint(user.getTotalpoint()+changePoint);
					userMapper.updateUserTotalpoint(tempUser);
				}
				pointObject.setPoint(changePoint);
			}
			payResult = 1;
		}
		
		return payResult;
	}
	
	@Override
	public int saveSorder(Sorder sorder, int recordid, Point pointObject, User user) throws Exception {
		int saveResult = 0;
		
		int point = pointObject.getPoint();
		CouponRecord couponRecord = null;	//此次订单使用的优惠券
		Enterprise enterprise = null;		//此次订单所对应的企业
		double couponMoney = 0;				//此次订单因优惠券而减免的金额
		double discountMoney = 0;			//此次订单因积分而折扣的金额
		int usefulPoint = 0;				//此次订单的可用积分数
		Lesson lesson = null;				//此次订单所购买的课程
		//获取此订单所关联的企业
		enterprise = enterpriseMapper.selectEnterpriseByQid(user.getQid());
		//获取当前时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String currentTime = df.format(System.currentTimeMillis());
		//第一步，判断total是否正确
		lesson = lessonMapper.selectLessonByLid(sorder.getLid());
		if(lesson.getLprice() != sorder.getTotal()){
			//如果total不正确
			saveResult = 2;
		}
		//第二步，判断用户是否使用了优惠券
		if(saveResult==0 && recordid > 0){
			//如果用户使用了优惠券
			//查询优惠券信息
			couponRecord = couponRecordMapper.selectCouponRecordByID(recordid);
			if(couponRecord==null || couponRecord.getCoupon()==null || couponRecord.getCoupon().getCategory()==null){
				//如果没有查到正确的优惠券信息
				saveResult = 2;
			}else{
				//如果查到正确的优惠券信息
				//验证优惠券是否可用
				if(Config.ordinaryCouponCategory.equals(couponRecord.getCoupon().getCategory())){
					//如果优惠券是全类型的
					if(sorder.getTotal() < couponRecord.getCoupon().getNeedmoney()){
						//如果订单总额没有达到优惠券的使用门槛
						saveResult = 2;
					}else{
						//如果订单总额达到优惠券的使用门槛
						couponMoney = couponRecord.getCoupon().getMoney();
					}
				}else{
					//如果优惠券不是全类型的
					if(couponRecord.getCoupon().getCategory().equals(lesson.getCategory())){
						//如果优惠券类型和课程类型相同
						if(sorder.getTotal() < couponRecord.getCoupon().getNeedmoney()){
							//如果订单总额没有达到优惠券的使用门槛
							saveResult = 2;
						}else{
							//如果订单总额达到优惠券的使用门槛
							couponMoney = couponRecord.getCoupon().getMoney();
						}
					}else{
						//如果优惠券类型和课程类型不相同
						saveResult = 2;
					}
				}
			}
		}
		//第三步，判断用户是否使用了积分
		if(saveResult==0 && point>0){
			//如果用户使用了积分
			//校验point是否有误
			double discount = sorder.getTotal() * enterprise.getDiscountrate();//计算积分可抵扣的金额
			if(discount%enterprise.getPerpointtomoney() == 0){
				//如果可抵扣额能够整除以单积分抵扣额
				usefulPoint = (int)(discount / enterprise.getPerpointtomoney());
			}else{
				//如果可抵扣额不能够整除以单积分抵扣额
				usefulPoint = (int)(discount / enterprise.getPerpointtomoney()) + 1;
			}
			//如果可用积分数大于用户的总积分数，则将可用积分数设置为用户的总积分数
			usefulPoint = usefulPoint>user.getTotalpoint() ? user.getTotalpoint() : usefulPoint;
			if(usefulPoint != point){
				//用户使用了积分，且传来的积分与计算得到的积分不同，说明有鬼，订单存储失败
				saveResult = 2;
			}else{
				//传来的point正确
				//计算此次订单因积分而折扣的金额
				discountMoney = usefulPoint * enterprise.getPerpointtomoney();
				discountMoney = discountMoney>discount ? discount : discountMoney;
			}
		}
		//第四步，判断actual是否正确
		if(saveResult == 0){
			//计算此次订单的应付金额
			double money = lesson.getLprice() - couponMoney - discountMoney;
			money = money<0 ? 0 : money;
			if(money != sorder.getActual()){
				//如果acutal不正确
				saveResult = 2;
			}
		}
		//第五步，存储订单等相关信息
		if(saveResult == 0){
			//通过qid和branch查询branchid
			QIDAndBranch qidAndBranch = new QIDAndBranch();
			qidAndBranch.setQid(sorder.getQid());
			qidAndBranch.setBranch(sorder.getBranch());
			Address address = addressMapper.selectAddressIDByQIDAndBranch(qidAndBranch);
			//设置sorder的剩余信息
			if(address!=null){
				sorder.setBranchid(address.getId());
			}
			sorder.setDeletekey(0);
			sorder.setUserdeletekey(0);
			//存储sorder信息
			sorderMapper.saveSorder(sorder);
			//如果使用了优惠券，则删除该优惠券
			if(couponRecord!=null && couponRecord.getCoupon()!=null){
				couponRecord.setDeletekey(1);
				couponRecordMapper.deleteCouponRecordLogicallyByID(couponRecord);
			}
			//如果使用了积分，则存储积分记录
			if(point > 0){
				//建立购买课程所花费的积分的积分记录对象
				Point reducePoint = new Point();
				reducePoint.setUid(user.getUid());
				reducePoint.setPoint(-1 * point);
				reducePoint.setCategory(Config.PointCategorySorder);
				reducePoint.setOid(sorder.getOid());
				reducePoint.setSid(0);
				reducePoint.setDate(currentTime);
				reducePoint.setDeletekey(0);
				pointMapper.savePoint(reducePoint);
			}
			//计算此次消费是否可得积分
			int newpoint = (int)(sorder.getActual()/enterprise.getMoneytoperpoint());
			if(newpoint > 0){
				//如果此次消费可得积分
				Point addPoint = new Point();
				addPoint.setUid(user.getUid());
				addPoint.setPoint(newpoint);
				addPoint.setCategory(Config.PointCategorySorder);
				addPoint.setOid(sorder.getOid());
				addPoint.setSid(0);
				addPoint.setDate(currentTime);
				addPoint.setDeletekey(0);
				pointMapper.savePoint(addPoint);
			}
			//如果有积分变化，则修改update的totalpoint
			if(point>0 || newpoint>0){
				int changePoint = newpoint - point;
				if(changePoint!=0){
					//如果总积分有变化
					User tempUser = new User();
					tempUser.setUid(user.getUid());
					tempUser.setTotalpoint(user.getTotalpoint()+changePoint);
					userMapper.updateUserTotalpoint(tempUser);
				}
				pointObject.setPoint(changePoint);
			}
			saveResult = 1;
		}
		
		return saveResult;
	}
	
	@Override
	public void saveSorderUnpaid(Sorder sorder) throws Exception{
		sorder.setDeletekey(0);
		sorder.setUserdeletekey(0);
		sorderMapper.saveSorder(sorder);
	}

	@Override
	public void saveRefund(Sorder sorder, Refund refund) throws Exception {
		refund.setDeletekey(0);
		refund.setUserdeletekey(0);
		sorderMapper.updateSorderStatusByOid(sorder); // 更新sorder状态
		refundMapper.saveRefund(refund); //  添加退款记录
	}
	
	@Override
	public void deleteSorderLogicallyByOID(Sorder sorder) throws Exception{
		sorder.setUserdeletekey(1);
		sorderMapper.deleteSorderLogicallyByOID(sorder);
	}
	
	@Override
	public void deleteSorderPowerfullyByOID(Sorder sorder) throws Exception{
		sorder.setDeletekey(1);
		sorder.setUserdeletekey(1);
		sorderMapper.deleteSorderPowerfullyByOID(sorder);
	}
	
	@Override
	public void deleteRefundLogicallyByOID(Refund refund, Sorder sorder) throws Exception{
		refund.setUserdeletekey(1);
		sorder.setUserdeletekey(1);
		sorderMapper.deleteSorderLogicallyByOID(sorder);
		refundMapper.deleteRefundLogicallyByOID(refund);
	}
	
	@Override
	public void deleteRefundPowerfullyByOID(Refund refund, Sorder sorder) throws Exception{
		refund.setDeletekey(1);
		refund.setUserdeletekey(1);
		sorderMapper.updateSorderStatusByOid(sorder);
		refundMapper.deleteRefundPowerfullyByOID(refund);
	}
	
	@Override
	public void updateSorderByOID(Sorder sorder) throws Exception{
		QIDAndBranch qidAndBranch = new QIDAndBranch();
		qidAndBranch.setQid(sorder.getQid());
		qidAndBranch.setBranch(sorder.getBranch());
		Address address = addressMapper.selectAddressIDByQIDAndBranch(qidAndBranch);
		sorder.setBranchid(address.getId());
		sorderMapper.updateSorderByOID(sorder);
	}
	
	@Override
	public void updateSorderStatusByOID(Sorder sorder) throws Exception{
		sorderMapper.updateSorderStatusByOid(sorder);
	}
	
	@Override
	public Sorder selectSorderByOID(int oid) throws Exception {
		return sorderMapper.selectSorderByOid(oid);	
	}
	
	@Override
	public Sorder selectRefundByRID(int rid) throws Exception{
		Sorder sorder = null;
		
		sorder = sorderMapper.selectRefundByRID(rid);
		
		return sorder;
	}
	
	@Override
	public List<Sorder> selectSorderBySorderQueryCondition(SorderQueryCondition sorderQueryCondition) throws Exception {
		List<Sorder> sorders = null;
		
		sorders = sorderMapper.selectSorderBySorderQueryCondition(sorderQueryCondition);
		
		return sorders;
	}
	
	@Override
	public List<Sorder> selectRefudBySorderQueryCondition(SorderQueryCondition sorderQueryCondition) throws Exception{
		List<Sorder> refunds = null;
		
		refunds = sorderMapper.selectRefudBySorderQueryCondition(sorderQueryCondition);
		
		return refunds;
	}

	@Override
	public int selectCountSorderByUIDAndStatus(UIDAndStatus uidAndStatus) throws Exception{
		int countSorder = 0;
		
		countSorder = sorderMapper.selectCountSorderByUIDAndStatus(uidAndStatus);
		
		return countSorder;
	}
}
