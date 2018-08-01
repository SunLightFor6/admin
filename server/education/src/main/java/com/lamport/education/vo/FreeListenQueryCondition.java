package com.lamport.education.vo;

import com.lamport.education.util.PageBean;

/**
 * vo类，通过多条件查询FreeListen时使用
 * @author Lin Zhao, protector of Sherry
 *
 */
public class FreeListenQueryCondition {
	private int rowId;			//待删，兼容用

	private int qid;			//企业表主键
	private String branch;		//分部名称
	private String category;	//课程类别
	private PageBean pageBean;	//页码工具类
	private int startId;		//每页的起始id

	public void initPageBean(int pageSize){
		this.pageBean = new PageBean(this.startId, pageSize);
	}
	
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public int getStartId() {
		return startId;
	}
	public void setStartId(int startId) {
		this.startId = startId;
	}
	public int getRowId() {
		return rowId;
	}
	public void setRowId(int rowId) {
		this.rowId = rowId;
	}
}
