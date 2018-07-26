package com.lamport.education.po;

import java.util.List;

/**
 * 企业类
 * @author Lin Zhao, protector of Sherry
 *
 */
public class Enterprise {
	
	private int qid;			//企业主键
	private String name;		//企业名称
	private String videopath;	//企业视频存放路径
	private String introduction;//企业简介
	private String jczs;		//企业精彩展示
	private int deletekey;		//删除键
	
	private Admin administer;//管理员
	private List<Address> branches;//分部
	private List<Teacher> teachers;//教师
	private List<Message> messages;//朋友圈
	private List<Swiper> swipers;//轮播图
	
	public int getQid() {
		return qid;
	}
	
	public void setQid(int qid) {
		this.qid = qid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getVideopath() {
		return videopath;
	}
	
	public void setVideopath(String videopath) {
		this.videopath = videopath;
	}
	
	public String getIntroduction() {
		return introduction;
	}
	
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	public String getJczs() {
		return jczs;
	}
	
	public void setJczs(String jczs) {
		this.jczs = jczs;
	}
	
	public Admin getAdminister() {
		return administer;
	}
	
	public void setAdminister(Admin administer) {
		this.administer = administer;
	}
	
	public List<Address> getBranches() {
		return branches;
	}
	
	public void setBranches(List<Address> branches) {
		this.branches = branches;
	}
	
	public List<Teacher> getTeachers() {
		return teachers;
	}
	
	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}
	
	public List<Message> getMessages() {
		return messages;
	}
	
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	public List<Swiper> getSwipers() {
		return swipers;
	}
	
	public void setSwipers(List<Swiper> swipers) {
		this.swipers = swipers;
	}
	
	public int getDeletekey() {
		return deletekey;
	}
	
	public void setDeletekey(int deletekey) {
		this.deletekey = deletekey;
	}

}
