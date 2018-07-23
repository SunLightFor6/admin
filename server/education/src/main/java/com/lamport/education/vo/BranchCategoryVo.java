package com.lamport.education.vo;

import java.util.List;

import com.lamport.education.po.FreeListen;
import com.lamport.education.po.Lesson;

public class BranchCategoryVo {
	private int bid;
	private String branch;
	private List<Lesson> lessonCategorys;
	private List<FreeListen> freeListenCategorys;
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public List<Lesson> getLessonCategorys() {
		return lessonCategorys;
	}
	public void setLessonCategorys(List<Lesson> lessonCategorys) {
		this.lessonCategorys = lessonCategorys;
	}
	public List<FreeListen> getFreeListenCategorys() {
		return freeListenCategorys;
	}
	public void setFreeListenCategorys(List<FreeListen> freeListenCategorys) {
		this.freeListenCategorys = freeListenCategorys;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	 
	
	
}
