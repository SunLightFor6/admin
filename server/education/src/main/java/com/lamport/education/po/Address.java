package com.lamport.education.po;

import java.util.ArrayList;
import java.util.List;

/**
 * 企业分部类
 * @author Lin Zhao, protector of Sherry
 *
 */
public class Address {
	
	private int qid;//企业表外键
	private int id;				//分部主键
	private String branch;		//分部名称
	private String address;		//分部地址
	private String tel;			//分部联系电话
	private double longitude;	//经度
	private double latitude;	//纬度
	private int deletekey;		//删除键
	
	private List<FreeListen> freeListens;//免费试听课
	private List<Lesson> lessons;//精品课
	
	public Address(){
		freeListens = new ArrayList<FreeListen>();
		lessons = new ArrayList<Lesson>();
	}
	
	public int getQid() {
		return qid;
	}
	
	public void setQid(int qid) {
		this.qid = qid;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getBranch() {
		return branch;
	}
	
	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public List<FreeListen> getFreeListens() {
		return freeListens;
	}
	
	public void setFreeListens(List<FreeListen> freeListens) {
		this.freeListens = freeListens;
	}
	
	public List<Lesson> getLessons() {
		return lessons;
	}
	
	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}
	
	public int getDeletekey() {
		return deletekey;
	}
	
	public void setDeletekey(int deletekey) {
		this.deletekey = deletekey;
	}

}
