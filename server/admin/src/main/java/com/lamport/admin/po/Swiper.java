package com.lamport.admin.po;

/**
 * 轮播图类
 * @author Lin Zhao, protector of Sherry
 *
 */
public class Swiper {
	private int qid;//企业表外键
	private int id;				//图片主键
	private String imgurl;		//图片存放路径
	private String category;	//图片类型（企业的、教师的）
	private int deletekey;		//删除键
	
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getDeletekey() {
		return deletekey;
	}
	public void setDeletekey(int deletekey) {
		this.deletekey = deletekey;
	}
}
