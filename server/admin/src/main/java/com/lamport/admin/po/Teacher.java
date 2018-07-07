package com.lamport.admin.po;

/**
 * 企业教师类
 * @author Lin Zhao, protector of Sherry
 *
 */
public class Teacher {
	private int qid;//企业表外键
	private int tid;				//教师主键
	private String tname;			//教师姓名
	private String tphone;			//教师联系电话
	private String introduction;	//教师简介
	private int deletekey;			//删除键
	
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTphone() {
		return tphone;
	}
	public void setTphone(String tphone) {
		this.tphone = tphone;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public int getDeletekey() {
		return deletekey;
	}
	public void setDeletekey(int deletekey) {
		this.deletekey = deletekey;
	}
}
