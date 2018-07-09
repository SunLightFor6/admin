package com.lamport.admin.vo;

import com.lamport.admin.tool.PageTool;

/**
 * vo类，通过多条件查询FreeListenBook时使用
 * @author Lin Zhao, protector of Sherry
 *
 */
public class BookQueryCondition {
	private int qid;			//企业表主键
	private int fid;			//免费试听课表主键
	private String nickname;	//用户昵称
	private String status;		//预约状态
	private String begainTime;	//查询起始时间
	private String endTime;		//查询终止时间
	private PageTool pageTool;	//页码工具类
	
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
