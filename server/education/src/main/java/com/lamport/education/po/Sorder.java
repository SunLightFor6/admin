package com.lamport.education.po;

/**
 * 精品课订单类
 * @author Lin Zhao, protector of Sherry
 *
 */
public class Sorder {
	private int qid;//企业表外键
	private int lid;//精品课表外键
	private int branchid;//分部表外键
	private int uid;//用户表外键
	private int cid;//代金券表外键
	private int oid;				//订单主键
	private double total;			//订单价格
	private double actual;			//订单实付价格
	private String status;			//订单状态	？数据库中用Char类型是否合适
	private String ordertime;		//下单时间
	private String transactionid;	//订单流水号
	private String username;		//下单的用户名字
	private String tel;				//客户联系方式
	private int deletekey;			//删除键
	private int userdeletekey;		//用户删除键
	
	private Refund refund;//退款订单
	
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public int getBranchid() {
		return branchid;
	}
	public void setBranchid(int branchid) {
		this.branchid = branchid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getActual() {
		return actual;
	}
	public void setActual(double actual) {
		this.actual = actual;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}
	public String getTransactionid() {
		return transactionid;
	}
	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
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
	public Refund getRefund() {
		return refund;
	}
	public void setRefund(Refund refund) {
		this.refund = refund;
	}
}
