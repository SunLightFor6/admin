package com.lamport.admin.po;

/*
 * 企业管理员类
 */
public class Admin {
	private int qid;//企业表外键
	private int id;				//管理员主键
	private String username;	//管理员登陆账号
	private String userpassword;//管理员登陆密码
	private String jurisdiction;//权限，管理员或超级管理员
	
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public String getJurisdiction() {
		return jurisdiction;
	}
	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}
}
