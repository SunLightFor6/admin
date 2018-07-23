package com.lamport.education.po;

/**
 * 企业管理员类
 * @author Lin Zhao, protector of Sherry
 *
 */
public class Admin {
	private int qid;//企业表外键
	private int id;				//管理员主键
	private String adminname;	//管理员登陆账号
	private String password;	//管理员登陆密码
	private String jurisdiction;//权限，管理员或超级管理员
	private int deletekey;		//删除键
	
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
	public String getAdminname() {
		return adminname;
	}
	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getJurisdiction() {
		return jurisdiction;
	}
	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}
	public int getDeletekey() {
		return deletekey;
	}
	public void setDeletekey(int deletekey) {
		this.deletekey = deletekey;
	}
}
