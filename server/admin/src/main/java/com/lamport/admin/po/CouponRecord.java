package com.lamport.admin.po;

/**
 * 优惠券发放记录类
 * @author Lin Zhao, protector of Sherry
 *
 */
public class CouponRecord {
	private int cid;//优惠券表外键
	private int uid;//用户表外键
	private int id;				//优惠券发放记录表主键
	private String gettime;		//优惠券获得时间
	private int deletekey;		//删除键
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGettime() {
		return gettime;
	}
	public void setGettime(String gettime) {
		this.gettime = gettime;
	}
	public int getDeletekey() {
		return deletekey;
	}
	public void setDeletekey(int deletekey) {
		this.deletekey = deletekey;
	}
}