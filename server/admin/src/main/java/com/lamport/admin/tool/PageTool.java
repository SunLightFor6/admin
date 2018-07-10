package com.lamport.admin.tool;

public class PageTool {
	
	
	private int limit;		//每页显示的条数
	private int page;		//当前请求的页码
	private int count;		//总条数
	private int startrow;
	
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public int getStartrow() {
		return startrow;
	}
	public void setStartrow() {
		this.startrow = (page-1) * limit;
		if(page<1){
			this.startrow = 0;
		}
	}
	/**
	 * 不提供无参的构造方法
	 * 要进行构造必须传入参数page和limit
	 * @param page
	 * @param limit
	 */
	public PageTool(int page, int limit){
		this.setPage(page);
		this.setLimit(limit);
		this.setStartrow();
	}
}
