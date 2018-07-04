package com.lamport.admin.po;

/*
 * 朋友圈点赞类
 */
public class MessageLike {
	private int mid;//朋友圈外键
	private int id;			//朋友圈点赞主键
	private String nickname;//点赞者昵称		？仅存昵称是否合适
	private String stime;		//点赞时间
	
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
}
