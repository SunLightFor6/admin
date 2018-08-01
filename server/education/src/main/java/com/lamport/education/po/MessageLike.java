package com.lamport.education.po;

/**
 * 朋友圈点赞类
 * @author Lin Zhao, protector of Sherry
 *
 */
public class MessageLike {
	
	private int mid;//朋友圈外键
	private int uid;//用户表外键
	private int id;			//朋友圈点赞主键
	private String stime;	//点赞时间
	private int deletekey;	//删除键

	private User user;//用户
	
	public MessageLike(){
		user = new User();
	}
	
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
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
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getDeletekey() {
		return deletekey;
	}
	public void setDeletekey(int deletekey) {
		this.deletekey = deletekey;
	}
}
