package com.lamport.education.util;

public class PageBean {
	private int pageSize;	 
	private int startRowId;
	
	public PageBean(int pageSize, int startRowId) {
		this.pageSize = pageSize;
		this.startRowId = startRowId;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartRowId() {
		return startRowId;
	}

	public void setStartRowId(int startRowId) {
		this.startRowId = startRowId;
	}

	
}
