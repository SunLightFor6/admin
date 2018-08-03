package com.lamport.admin.po;

/**
 * 签到类
 * @author Lin Zhao, protector of Sherry
 *
 */
public class Sign {
	private int uid;//用户表外键
	private int sid;			//签到表主键
	private String signdate;	//签到时间
	private int days;			//连续签到天数
	private int point;			//此次签到获得的积分
	private int deletekey;		//删除键
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSigndate() {
		return signdate;
	}
	public void setSigndate(String signdate) {
		this.signdate = signdate;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getDeletekey() {
		return deletekey;
	}
	public void setDeletekey(int deletekey) {
		this.deletekey = deletekey;
	}
}
