package com.lamport.education.po;

/**
 * 微信类
 * @author Lin Zhao, protector of Sherry
 *
 */
public class WeiXin {
	
	private int qid;//企业表外键
	private String appid;
	private String appsecret;
	private String partner;
	private String partnerkey;
	private String weixinpaycallback;
	private int deletekey;				//删除键
	
	public int getQid() {
		return qid;
	}
	
	public void setQid(int qid) {
		this.qid = qid;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getPartnerkey() {
		return partnerkey;
	}

	public void setPartnerkey(String partnerkey) {
		this.partnerkey = partnerkey;
	}

	public String getWeixinpaycallback() {
		return weixinpaycallback;
	}

	public void setWeixinpaycallback(String weixinpaycallback) {
		this.weixinpaycallback = weixinpaycallback;
	}

	public int getDeletekey() {
		return deletekey;
	}

	public void setDeletekey(int deletekey) {
		this.deletekey = deletekey;
	}
	
}
