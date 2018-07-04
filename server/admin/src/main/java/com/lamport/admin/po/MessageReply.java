package com.lamport.admin.po;

/*
 * 朋友圈评论类
 */
public class MessageReply {
	private int mid;//朋友圈外键
	private int id;			//朋友圈评论主键
	private String content;	//评论内容
	private String nickname;//评论者昵称		？仅存昵称是否合适
	private String stime;		//评论时间
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
