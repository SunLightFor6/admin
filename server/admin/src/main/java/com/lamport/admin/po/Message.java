package com.lamport.admin.po;

import java.util.List;

/**
 * 朋友圈类
 * @author Lin Zhao, protector of Sherry
 *
 */
public class Message {
	private int qid;//企业表主键
	private int mid;		//朋友圈主键
	private String mtitle;	//朋友圈标题
	private String mtime;	//朋友圈发布时间
	private int deletekey;	//删除键
	
	private List<MessageImg> imgs;//朋友圈图片
	private List<MessageLike> likes;//朋友圈点赞
	private List<MessageReply> replies;//朋友圈评论
	private Enterprise enterprise;//企业，需要其中的name
	private Swiper swiper;//选取一张企业轮播图作为该企业的LOGO
	
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getMtitle() {
		return mtitle;
	}
	public void setMtitle(String mtitle) {
		this.mtitle = mtitle;
	}
	public String getMtime() {
		return mtime;
	}
	public void setMtime(String mtime) {
		this.mtime = mtime;
	}
	public List<MessageImg> getImgs() {
		return imgs;
	}
	public void setImgs(List<MessageImg> imgs) {
		this.imgs = imgs;
	}
	public List<MessageLike> getLikes() {
		return likes;
	}
	public void setLikes(List<MessageLike> likes) {
		this.likes = likes;
	}
	public List<MessageReply> getReplies() {
		return replies;
	}
	public void setReplies(List<MessageReply> replies) {
		this.replies = replies;
	}
	public int getDeletekey() {
		return deletekey;
	}
	public void setDeletekey(int deletekey) {
		this.deletekey = deletekey;
	}
	public Swiper getSwiper() {
		return swiper;
	}
	public void setSwiper(Swiper swiper) {
		this.swiper = swiper;
	}
	public Enterprise getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
}
