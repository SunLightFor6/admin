package com.lamport.education.vo;

import com.lamport.education.util.PageBean;

/**
 * vo类，通过qid和tel查询信息时使用
 * @author Lin Zhao, protector of Sherry
 *
 */
public class QIDAndPage {
	private int qid;			//企业表外键
	private int startId;		//每页的起始id
	private PageBean pageBean;	//页码工具类
	
	public void initPageBean(int pageSize){
		this.pageBean = new PageBean(this.startId, pageSize);
	}
	
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public int getStartId() {
		return startId;
	}
	public void setStartId(int startId) {
		this.startId = startId;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
}
