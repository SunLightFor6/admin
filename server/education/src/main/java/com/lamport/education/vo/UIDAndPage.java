package com.lamport.education.vo;

import com.lamport.education.util.PageBean;

/**
 * vo类，通过uid和页码查询信息时使用
 * @author Lin Zhao, protector of Sherry
 *
 */
public class UIDAndPage {
	private int uid;			//用户表主键
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
}
