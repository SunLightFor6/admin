package com.lamport.admin.vo;

import com.lamport.admin.tool.PageTool;

/**
 * vo类，通过branchid和页码查询信息时使用
 * @author Lin Zhao, protector of Sherry
 *
 */
public class BranchIDAndPage {
	private int branchid;		//分部表主键
	private PageTool pageTool;	//页码工具类
	public int getBranchid() {
		return branchid;
	}
	public void setBranchid(int branchid) {
		this.branchid = branchid;
	}
	public PageTool getPageTool() {
		return pageTool;
	}
	public void setPageTool(PageTool pageTool) {
		this.pageTool = pageTool;
	}
}
