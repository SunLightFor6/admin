package com.lamport.education.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamport.education.mapper.EnterpriseMapper;
import com.lamport.education.mapper.PointMapper;
import com.lamport.education.mapper.SignMapper;
import com.lamport.education.mapper.UserMapper;
import com.lamport.education.po.Enterprise;
import com.lamport.education.po.Point;
import com.lamport.education.po.Sign;
import com.lamport.education.po.User;
import com.lamport.education.service.SignService;
import com.lamport.education.util.Config;

/**
 * implements CouponService
 * @author Lin Zhao, protector of Sherry
 *
 */
@Service
public class SignServiceBean implements SignService {
	
	@Autowired
	SignMapper signMapper;
	@Autowired
	PointMapper pointMapper;
	@Autowired
	UserMapper userMapper;
	@Autowired
	EnterpriseMapper enterpriseMapper;

	@Override
	public int saveSign(Sign sign, User user) throws Exception {
		int saveResult = 0;
		
		//获取当前企业信息，并获取基础签到分数
		Enterprise enterprise = enterpriseMapper.selectEnterpriseByQid(user.getQid());
		int basicSignPoint = enterprise.getBasicsignpoint();
		//获取当前时间和日期
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String currentTime = df.format(System.currentTimeMillis());
		String currentDate = currentTime.substring(0, 10);
		//获取最近一次的签到信息
		Sign lastSign = signMapper.selectLastSignByUID(user.getUid());
		if(lastSign==null || lastSign.getSigndate()==null){
			//如果用户从未签到过
			sign.setDays(1);
			sign.setPoint(basicSignPoint);
			sign.setSigndate(currentTime);
			sign.setDeletekey(0);
			signMapper.saveSign(sign);
		}else{
			//如果用户曾经签到过
			String lastSignDate = lastSign.getSigndate().substring(0, 10);	//从from位置截取到to-1的位置
			if(currentDate.equals(lastSignDate)){
				//如果两次签到是同一天，则为重复签到
				saveResult = 2;
			}else{
				//如果两次签到不是同一天，则判断这两天是否相邻
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String time = " 00:00:00";
				Date lSignDate = sdf.parse(lastSignDate + time);//按照sdf的格式获取最近一次签到日期的0点时间
				Date cDate = sdf.parse(currentDate + time);//按照sdf的格式获取当前签到日期的0点时间
				long lSignDateMillis = lSignDate.getTime();//获取最近一次签到日期的0点时间的毫秒数
				long cDateMillis = cDate.getTime();//获取当前签到日期的0点时间的毫秒数
				int subdays = (int)((cDateMillis-lSignDateMillis)/(24*3600*1000));//计算两个日期相差的天数
				if(subdays == 1){
					//如果这两天相邻
					sign.setDays(lastSign.getDays()+1);
					int days = sign.getDays()>Config.SignInDaysLimit ? Config.SignInDaysLimit : sign.getDays();
					sign.setPoint(basicSignPoint*days);
					sign.setSigndate(currentTime);
					sign.setDeletekey(0);
					signMapper.saveSign(sign);
				}else{
					//如果这两天不相邻
					sign.setDays(1);
					sign.setPoint(basicSignPoint);
					sign.setSigndate(currentTime);
					sign.setDeletekey(0);
					signMapper.saveSign(sign);
				}
			}
		}
		//当签到成功时，添加Point记录
		if(sign.getSid() != 0){
			Point point = new Point();
			point.setCategory(Config.PointCategorySign);
			point.setDate(currentTime);
			point.setOid(0);
			point.setPoint(sign.getPoint());
			point.setSid(sign.getSid());
			point.setUid(sign.getUid());
			point.setDeletekey(0);
			pointMapper.savePoint(point);
			//添加完Point记录后，修改user中的totalpoint
			//建立tempUser是为了防止事务失败
			User tempUser = new User();
			tempUser.setUid(user.getUid());
			tempUser.setTotalpoint(user.getTotalpoint()+point.getPoint());
			userMapper.updateUserTotalpoint(tempUser);
			//签到成功，设定saveResult为1
			saveResult = 1;
		}
		return saveResult;
	}

	@Override
	public int selectDaysByUID(int uid) throws Exception {
		int days = signMapper.selectDaysByUID(uid);
		return days;
	}

}
