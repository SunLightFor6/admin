package com.lamport.education.po;

/**
 * 订单退款类
 * @author Lin Zhao, protector of Sherry
 *
 */
public class Refund {
	private int oid; 				//订单表外键
	private int rid;				//主键
	private String refundreason;	//退款原因
	private String status;			//退款状态
	private String refundtime;		//退款时间
	private int deletekey;			//删除键
	private int userdeletekey;		//用户删除键
	
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getRefundreason() {
		return refundreason;
	}
	public void setRefundreason(String refundreason) {
		this.refundreason = refundreason;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRefundtime() {
		return refundtime;
	}
	public void setRefundtime(String refundtime) {
		this.refundtime = refundtime;
	}
	public int getDeletekey() {
		return deletekey;
	}
	public void setDeletekey(int deletekey) {
		this.deletekey = deletekey;
	}
	public int getUserdeletekey() {
		return userdeletekey;
	}
	public void setUserdeletekey(int userdeletekey) {
		this.userdeletekey = userdeletekey;
	}
}
