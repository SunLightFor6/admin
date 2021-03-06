package com.lamport.education.po;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户类
 * @author Lin Zhao, protector of Sherry
 *
 */
public class User {
	
	private int qid;//企业表外键
	private int uid;			//用户主键
	private String tel;			//用户手机号，登陆用
	private String nickname;	//用户昵称
	private String username;	//用户真实姓名
	private String userimg;		//用户头像
	private int totalpoint;		//用户总积分
	private String openid;		//用户微信号唯一标识
	private int deletekey;		//删除键
	
	List<Sorder> sorders;//用户订单
	List<FreeListenBook> freeListenBooks;//用户预约
	
	public User(){
		sorders = new ArrayList<Sorder>();
		freeListenBooks = new ArrayList<FreeListenBook>();
	}
	
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getTotalpoint() {
		return totalpoint;
	}
	public void setTotalpoint(int totalpoint) {
		this.totalpoint = totalpoint;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public List<Sorder> getSorders() {
		return sorders;
	}
	public void setSorders(List<Sorder> sorders) {
		this.sorders = sorders;
	}
	public List<FreeListenBook> getFreeListenBooks() {
		return freeListenBooks;
	}
	public void setFreeListenBooks(List<FreeListenBook> freeListenBooks) {
		this.freeListenBooks = freeListenBooks;
	}
	public String getUserimg() {
		return userimg;
	}
	public void setUserimg(String userimg) {
		this.userimg = userimg;
	}
	public int getDeletekey() {
		return deletekey;
	}
	public void setDeletekey(int deletekey) {
		this.deletekey = deletekey;
	}
}
