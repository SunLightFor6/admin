package com.lamport.admin.po;

/*
 * 订单退款类
 */
public class Refund {
	private int oid; 				//主键，订单表外键
	private String refundreason;	//退款原因
	private String status;			//退款状态
	private String refundtime;		//退款时间
	
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
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
}
