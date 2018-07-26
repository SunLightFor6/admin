package com.lamport.education.vo;

/**
 * vo类，通过qid和tel查询信息时使用
 * @author Lin Zhao, protector of Sherry
 *
 */
public class QIDAndTel {
	private int qid;		//企业表外键
	private String tel;		//用户手机号，登陆用
	
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
}
