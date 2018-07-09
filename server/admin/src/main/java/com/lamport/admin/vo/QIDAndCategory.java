package com.lamport.admin.vo;

/**
 * vo类，通过qid和category查询Swiper(轮播图)信息时使用
 * @author Lin Zhao, protector of Sherry
 *
 */
public class QIDAndCategory {
	private int qid;			//企业表主键
	private String category;	//轮播图类型（企业E、教师T）
	
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}
