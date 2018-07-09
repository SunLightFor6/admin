package com.lamport.admin.vo;

import com.lamport.admin.tool.PageTool;

/**
 * vo类，通过多条件查询Sorder时使用
 * @author Lin Zhao, protector of Sherry
 *
 */
public class SorderQueryCondition {
	private int qid;			//企业表主键
	private int oid;			//订单表主键
	private String status;		//订单状态
	private String begainTime;	//查询起始时间
	private String endTime;		//查询终止时间
	private PageTool pageTool;	//页码工具类
	
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBegainTime() {
		return begainTime;
	}
	public void setBegainTime(String begainTime) {
		this.begainTime = begainTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public PageTool getPageTool() {
		return pageTool;
	}
	public void setPageTool(PageTool pageTool) {
		this.pageTool = pageTool;
	}
}
