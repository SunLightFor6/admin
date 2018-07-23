package com.lamport.education.po;

/**
 * 企业教师类
 * @author Lin Zhao, protector of Sherry
 *
 */
public class Teacher {
	private int qid;//企业表外键
	private int tid;				//教师主键
	private String tname;			//教师姓名
	private String tphoto;			//教师图片
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
	public String getTphoto() {
		return tphoto;
	}
	public void setTphoto(String tphoto) {
		this.tphoto = tphoto;
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
