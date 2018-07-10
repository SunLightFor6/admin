package com.lamport.admin.vo;

import com.lamport.admin.tool.PageTool;

/**
 * vo类，通过多条件查询Lesson时使用
 * @author Lin Zhao, protector of Sherry
 *
 */
public class LessonQueryCondition {
	private int qid;			//企业表主键
	private String branch;		//分部名称
	private PageTool pageTool;	//页码工具类
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
	public PageTool getPageTool() {
		return pageTool;
	}
	public void setPageTool(PageTool pageTool) {
		this.pageTool = pageTool;
	}
}
