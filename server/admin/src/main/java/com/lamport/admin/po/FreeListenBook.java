package com.lamport.admin.po;

/**
 * 免费试听课预约类
 * @author Lin Zhao, protector of Sherry
 *
 */
public class FreeListenBook {
	private int fid;//免费试听课表外键
	private int uid;//用户表外键
	private int id;				//预约主键
	private String username;	//预约的用户名字
	private String tel;			//用户联系方式
	private String status;		//预约状态
	private String comment;		//留言
	private String booktime;	//预约时间
	private int deletekey;		//删除键
	private int userdeletekey;	//用户删除键
	
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getBooktime() {
		return booktime;
	}
	public void setBooktime(String booktime) {
		this.booktime = booktime;
	}
	public int getDeletekey() {
		return deletekey;
	}
	public void setDeletekey(int deletekey) {
		this.deletekey = deletekey;
	}
	public int getUserdeletekey() {
		return userdeletekey;
	}
	public void setUserdeletekey(int userdeletekey) {
		this.userdeletekey = userdeletekey;
	}
}
