package com.lamport.admin.po;

/**
 * 优惠券类
 * @author Lin Zhao, protector of Sherry
 *
 */
public class Coupon {
	private int qid;//企业表外键
	private int cid;			//优惠券表主键
	private String category;	//优惠券关联的课程类型
	private double needmoney;	//优惠券使用门槛
	private double money;		//优惠券可代替金额
	private int total;			//优惠券总量
	private int get;			//优惠券已发放数量
	private String deadline;	//优惠券有效期限
	private int disabledkey;	//失效键
	private int deletekey;		//删除键
	
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getNeedmoney() {
		return needmoney;
	}
	public void setNeedmoney(double needmoney) {
		this.needmoney = needmoney;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getGet() {
		return get;
	}
	public void setGet(int get) {
		this.get = get;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public int getDisabledkey() {
		return disabledkey;
	}
	public void setDisabledkey(int disabledkey) {
		this.disabledkey = disabledkey;
	}
	public int getDeletekey() {
		return deletekey;
	}
	public void setDeletekey(int deletekey) {
		this.deletekey = deletekey;
	}
}
