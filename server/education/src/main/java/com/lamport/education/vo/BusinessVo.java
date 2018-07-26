package com.lamport.education.vo;

import com.lamport.education.util.PageBean;

//待删
public class BusinessVo {
	
	private int uid;
	private String status;
	private PageBean page;
	
	public int getUid() {
		return uid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public PageBean getPage() {
		return page;
	}
	
	public void setPage(PageBean page) {
		this.page = page;
	}

}
