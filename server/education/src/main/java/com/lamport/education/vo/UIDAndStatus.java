package com.lamport.education.vo;

/**
 * vo类，通过uid和status查询信息时使用
 * @author Lin Zhao, protector of Sherry
 *
 */
public class UIDAndStatus {
	private int uid;			//User表主键
	private String status;		//被查询的信息的status属性
	
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
}
