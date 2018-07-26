package com.lamport.education.vo;

import com.lamport.education.po.FreeListen;

public class FreeListenBookVo {
	
	private int uid;//用户表外键
	private int id;				//预约主键
	private String username;	//预约的用户名字
	private String tel;			//用户联系方式
	private String status;		//预约状态
	private String comment;		//留言
	private String booktime;	//预约时间
	private FreeListen freeListen;
	
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getBooktime() {
		return booktime;
	}

	public void setBooktime(String booktime) {
		this.booktime = booktime;
	}

	public FreeListen getFreeListen() {
		return freeListen;
	}

	public void setFreeListen(FreeListen freeListen) {
		this.freeListen = freeListen;
	}
	
}
