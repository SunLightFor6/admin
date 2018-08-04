package com.lamport.admin.vo;

public class StatisticsQueryResult {
	private String querymonth;		//查询的月份
	private String querydate;		//查询的日期
	private int countsorder;		//查询到的订单总数
	private int countbook;			//查询到的预约总数
	private double countActual;		//查询到的订单总额
	
	public String getQuerymonth() {
		return querymonth;
	}
	public void setQuerymonth(String querymonth) {
		this.querymonth = querymonth;
	}
	public String getQuerydate() {
		return querydate;
	}
	public void setQuerydate(String querydate) {
		this.querydate = querydate;
	}
	public int getCountsorder() {
		return countsorder;
	}
	public void setCountsorder(int countsorder) {
		this.countsorder = countsorder;
	}
	public int getCountbook() {
		return countbook;
	}
	public void setCountbook(int countbook) {
		this.countbook = countbook;
	}
	public double getCountActual() {
		return countActual;
	}
	public void setCountActual(double countActual) {
		this.countActual = countActual;
	}
}
