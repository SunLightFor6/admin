package com.lamport.admin.po;

/*
 * 朋友圈评论类
 */
public class MessageReply {
	private int mid;//朋友圈外键
	private int uid;//用户表外键
	private int id;			//朋友圈评论主键
	private String content;	//评论内容
	private String stime;	//评论时间
	
	private User user;//用户
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
}
