package com.lamport.admin.vo;

import com.lamport.admin.tool.PageTool;

/**
 * vo类，通过qid和页码查询信息时使用
 * @author Lin Zhao, protector of Sherry
 *
 */
public class QIDAndPage {
	private int qid;			//企业表主键
	private PageTool pageTool;	//页码工具类
	
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public PageTool getPageTool() {
		return pageTool;
	}
	public void setPageTool(PageTool pageTool) {
		this.pageTool = pageTool;
	}
	
}
