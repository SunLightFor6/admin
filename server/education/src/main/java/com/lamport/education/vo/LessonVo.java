package com.lamport.education.vo;

import com.lamport.education.util.PageBean;

//待删
public class LessonVo {
	
	private int qid;
	private int bid;
	private String branchName;
	private String category;
	private PageBean page;
	
	public int getQid() {
		return qid;
	}
	
	public void setQid(int qid) {
		this.qid = qid;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public PageBean getPage() {
		return page;
	}

	public void setPage(PageBean page) {
		this.page = page;
	}
	
}
