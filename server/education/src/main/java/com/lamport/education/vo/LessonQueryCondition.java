package com.lamport.education.vo;

import com.lamport.education.util.PageBean;

/**
 * vo类，通过多条件查询Lesson时使用
 * @author Lin Zhao, protector of sherry
 *
 */
public class LessonQueryCondition {
	private int qid;			//企业表主键
	private String branchName;	//分部名称
	private String category;	//课程类型
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
