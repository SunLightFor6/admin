package com.lamport.education.util;

public class PageBean {
	private int pageSize;	//每页显示的条数
	private int startId;	//每页的起始id
	
//	public PageBean(){}
	public PageBean(int startId, int pageSize){
		this.pageSize = pageSize;
		this.startId = startId;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getStartId() {
		return startId;
	}
	public void setStartId(int startId) {
		this.startId = startId;
	}	
}
