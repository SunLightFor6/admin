package com.lamport.admin.po;

/**
 * 积分类
 * @author Lin Zhao, protector of Sherry
 *
 */
public class Point {
	private	int uid;//用户表外键
	private int oid;//订单表外键
	private int sid;//签到表外键
	private int pid;			//积分表主键
	private int point;			//此次记录的积分变化
	private String category;	//积分变化的类型
	private String date;		//积分变化的时间
	private int deletekey;		//删除键
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getDeletekey() {
		return deletekey;
	}
	public void setDeletekey(int deletekey) {
		this.deletekey = deletekey;
	}
}
