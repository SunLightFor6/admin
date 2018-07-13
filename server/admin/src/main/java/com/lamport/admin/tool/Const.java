package com.lamport.admin.tool;

/**
 * 常量类，提供本程序所需的各种常量
 * @author Lin Zhao, protector of Sherry
 *
 */
public class Const {
	public static final String AdminJurisdiction = "管理员";
	public static final String SuperAdminJurisdiction = "超级管理员";

	public static final long UselessTime = 1530000000000L;
	
	public static final String LessonStatusNotStarted = "未开始";
	public static final String LessonStatusUnderway = "进行中";
	public static final String LessonStatusEnded = "已结束";
	public static final String LessonStatusSoldOut = "已下架";
	
	public static final String FreeListenStatusNotStarted = "未开始";
	public static final String FreeListenUnderway = "进行中";
	public static final String FreeListenEnded = "已结束";
	
	
	public static final String SorderStatusWait = "待付款";
	public static final String SorderStatusPayed = "已付款";
	public static final String SorderStatusRefunding = "退款中";
	public static final String SorderStatusRefunded = "已退款";
	public static final String SorderStatusCAV = "已核销";

	public static final String RefundStatusUnprocessed = "待处理";
	public static final String RefundStatusProcessed = "已处理";
	
	public static final String BookStatusUnprocessed = "待处理";
	public static final String BookStatusProcessed = "已处理";

	public static final String SwiperCategoryE = "E";
	public static final String SwiperCategoryT = "T";
}
