package com.lamport.admin.po;

/*
 * 朋友圈图片表
 */
public class MessageImg {
	private int mid;//朋友圈表主键
	private int id;			//朋友圈图片主键
	private String imgurl;	//朋友圈图片存放路径
	
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
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
}
