package com.lamport.admin.po;

import java.util.List;

/**
 * 精品课类
 * @author Lin Zhao, protector of Sherry
 *
 */
public class Lesson {
	private int qid;//企业表外键
	private int lid;			//课程主键
	private String lname;		//精品课名称
	private String imgurl;		//精品课图片存放路径
	private double lprice;		//精品课价格
	private String ldesc;		//精品课描述
	private String status;		//课程状态
	private String category;	//精品课类别
	private String pubtime;		//课程发布时间
	private int deletekey;		//删除键
	
	private List<Address> branches;//所有分部
	/**
	 * 具体的一个分部
	 */
	private Address address;//某一分部
	private String[] branchs;//分部名称数组

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
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public double getLprice() {
		return lprice;
	}
	public void setLprice(double lprice) {
		this.lprice = lprice;
	}
	public String getLdesc() {
		return ldesc;
	}
	public void setLdesc(String ldesc) {
		this.ldesc = ldesc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPubtime() {
		return pubtime;
	}
	public void setPubtime(String pubtime) {
		this.pubtime = pubtime;
	}
	public List<Address> getBranches() {
		return branches;
	}
	public void setBranches(List<Address> branches) {
		this.branches = branches;
	}
	public int getDeletekey() {
		return deletekey;
	}
	public void setDeletekey(int deletekey) {
		this.deletekey = deletekey;
	}
	/**
	 * 具体的一个分部， 慎用，容易抛出空指针异常
	 * @return Address
	 */
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String[] getBranchs() {
		return branchs;
	}
	public void setBranchs(String[] branchs) {
		this.branchs = branchs;
	}
}
