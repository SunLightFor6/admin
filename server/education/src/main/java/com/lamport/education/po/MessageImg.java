package com.lamport.education.po;

/**
 * 朋友圈图片类
 * @author Lin Zhao, protector of Sherry
 *
 */
public class MessageImg {
	
	private int mid;//朋友圈表主键
	private int id;			//朋友圈图片主键
	private String imgurl;	//朋友圈图片存放路径
	private int deletekey;	//删除键
	
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
	
	public int getDeletekey() {
		return deletekey;
	}
	
	public void setDeletekey(int deletekey) {
		this.deletekey = deletekey;
	}

}
