package com.lamport.admin.vo;

/**
 * vo类，通过相关条件查询User时使用
 * @author Lin Zhao, protector of Sherry
 *
 */
public class MeetingCondition {
	private int qid;			//企业表主键
	private int cid;			//优惠券表主键
	private String su_category;	//用户购买过/预约过的课程类型
	private int su_points;		//用户积分达到或超过该值
	private int su_amount;		//随机选择用户数量
	private int su_per;			//每个用户发放几张优惠券
	
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getSu_category() {
		return su_category;
	}
	public void setSu_category(String su_category) {
		this.su_category = su_category;
	}
	public int getSu_points() {
		return su_points;
	}
	public void setSu_points(int su_points) {
		this.su_points = su_points;
	}
	public int getSu_amount() {
		return su_amount;
	}
	public void setSu_amount(int su_amount) {
		this.su_amount = su_amount;
	}
	public int getSu_per() {
		return su_per;
	}
	public void setSu_per(int su_per) {
		this.su_per = su_per;
	}
}
