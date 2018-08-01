package com.lamport.education.vo;

import com.lamport.education.util.PageBean;

/**
 * vo类，通过多条件查询FreeListenBook时使用
 * @author Lin Zhao, protector of Sherry
 *
 */
public class BookQueryCondition {
	private int rowId;			//待删，兼容用

	private int uid;			//用户表主键
	private String status;		//预约状态
	private PageBean pageBean;	//页码工具类
	private int startId;		//每页的起始id

	public void initPageBean(int pageSize){
		this.pageBean = new PageBean(this.startId, pageSize);
	}
	
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
