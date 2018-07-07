package com.lamport.admin.po;

import java.util.List;

/**
 * 免费试听课类
 * @author Lin Zhao, protector of Sherry
 *
 */
public class FreeListen {
	private int qid;//企业表外键
	private int branchid;//分部表外键
	private int id;				//课程主键
	private String title;		//试听课标题
	private String imgurl;		//课程图片存放路径
	private String fdesc;		//课程描述
	private String status;		//课程状态
	private String category;	//课程类别
	private String pubtime;		//试听课发布时间
	private int deletekey;		//删除键
	
	private List<FreeListenBook> freeListenBooks;//试听课预约
	
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public int getBranchid() {
		return branchid;
	}
	public void setBranchid(int branchid) {
		this.branchid = branchid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getFdesc() {
		return fdesc;
	}
	public void setFdesc(String fdesc) {
		this.fdesc = fdesc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPubtime() {
		return pubtime;
	}
	public void setPubtime(String pubtime) {
		this.pubtime = pubtime;
	}
	public List<FreeListenBook> getFreeListenBooks() {
		return freeListenBooks;
	}
	public void setFreeListenBooks(List<FreeListenBook> freeListenBooks) {
		this.freeListenBooks = freeListenBooks;
	}
	public int getDeletekey() {
		return deletekey;
	}
	public void setDeletekey(int deletekey) {
		this.deletekey = deletekey;
	}
}
