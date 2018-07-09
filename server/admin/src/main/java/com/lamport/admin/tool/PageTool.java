package com.lamport.admin.tool;

public class PageTool {
	
	//N为每页显示的条数
	public static int N = 10;
	
	//p_N为分页页码的个数
	public static int p_N = 9;
	
	//当前页数
	private int page = 1;
	//表中的总条数
	private int count = 0;
	//表中的总页数
	private int maxPage = 0;
	
	//分页页码的开始值
	private int startPage = 1;
	//分页页码的结束值
	private int endPage = p_N;
	
	public static int getN() {
		return N;
	}


	public static void setN(int n) {
		N = n;
	}


	public static int getP_N() {
		return p_N;
	}


	public static void setP_N(int p_N) {
		PageTool.p_N = p_N;
	}


	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
		if(page < 1){
			this.page = 1;
		}
		if(page > this.maxPage){
			this.page = this.maxPage;
		}
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public int getMaxPage() {
		return maxPage;
	}


	public void setMaxPage() {
		if(this.count % this.N == 0){
			this.maxPage = this.count / this.N;
		}else{
			this.maxPage = this.count / this.N + 1;
		}
	}


	public int getStartPage() {
		return startPage;
	}


	public void setStart_EndPage() {
		if(this.maxPage<this.p_N){
			this.startPage = 1;
			this.endPage = this.maxPage;
		}else{
			if(page <= this.p_N/2+1 ){
				this.startPage = 1;
				this.endPage = this.p_N;
			}else if(page < this.maxPage-this.p_N/2){
				this.startPage = page - this.p_N/2;
				this.endPage = page + this.p_N/2;
			}else{
				this.startPage = this.maxPage - this.p_N + 1;
				this.endPage = this.maxPage;
			}
		}
	}


	public int getEndPage() {
		return endPage;
	}


	/*
	 * 不提供无参构造方法
	 * 必须提供参数
	 * page: 当前页数
	 * count: 表中记录数
	 */
	public PageTool(int page, int count){
		this.count = count;
		this.setMaxPage();
		this.setPage(page);
		this.setStart_EndPage();
		this.display();
	}
	public void display(){
		System.out.println("\n......display.....................");
		System.out.println("page = " + this.page);
		System.out.println("count = " + this.count);
		System.out.println("maxPage = " + this.maxPage);
		System.out.println("startPage = " + this.startPage);
		System.out.println("endPage = " + this.endPage);
		System.out.println();
	}
}
