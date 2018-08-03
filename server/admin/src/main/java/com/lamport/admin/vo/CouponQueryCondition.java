package com.lamport.admin.vo;

import com.lamport.admin.tool.PageTool;

/**
 * vo类，通过多条件查询Coupon时使用
 * @author Lin Zhao, protector of Sherry
 *
 */
public class CouponQueryCondition {
	private int qid;				//企业表主键
	private int cid;				//优惠券表主键
	private String category;		//课程类别，空值表示全部
	private String queryBasis;		//排序依据 ，有四种:截止日期、使用门槛、面额、剩余数量
	private String queryOrderBasis;	//排序顺序，有两种：升序、降序
	private String showDisabled;	//"显示"表示显示已经失效的优惠券，  ""表示不显示 
	private PageTool pageTool;		//页码工具类
	
	private int limit;//每页显示的条数
	private int page;//当前请求的页码
	
	public void initPageTool(){
		this.pageTool = new PageTool(page, limit);
	}
	
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getQueryBasis() {
		return queryBasis;
	}
	public void setQueryBasis(String queryBasis) {
		this.queryBasis = queryBasis;
	}
	public String getQueryOrderBasis() {
		return queryOrderBasis;
	}
	public void setQueryOrderBasis(String queryOrderBasis) {
		this.queryOrderBasis = queryOrderBasis;
	}
	public String getShowDisabled() {
		return showDisabled;
	}
	public void setShowDisabled(String showDisabled) {
		this.showDisabled = showDisabled;
	}
	public PageTool getPageTool() {
		return pageTool;
	}
	public void setPageTool(PageTool pageTool) {
		this.pageTool = pageTool;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
}
