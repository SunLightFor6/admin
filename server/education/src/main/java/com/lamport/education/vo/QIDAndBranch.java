package com.lamport.education.vo;

/**
 * vo类，通过qid和branch查询信息时使用
 * @author Lin Zhao, protector of Sherry
 *
 */
public class QIDAndBranch {
	private int qid;			//企业表主键
	private String branch;		//分部名称
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
}
