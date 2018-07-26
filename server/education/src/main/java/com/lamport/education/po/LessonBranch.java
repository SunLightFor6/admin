package com.lamport.education.po;

/**
 * 课程-分部映射类
 * @author Lin Zhao, protector of Sherry
 *
 */
public class LessonBranch {
	
	private int lid;//Lesson表外键
	private int branchid;//Address表外键
	private int id;			//主键
	private int deletekey;	//删除键
	
	public int getLid() {
		return lid;
	}
	
	public void setLid(int lid) {
		this.lid = lid;
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
	
	public int getDeletekey() {
		return deletekey;
	}
	
	public void setDeletekey(int deletekey) {
		this.deletekey = deletekey;
	}

}
