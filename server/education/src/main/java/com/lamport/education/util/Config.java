package com.lamport.education.util;

public class Config {
	
	//用户头像存储路径
//	public static final String RootPath = "D:/Users/yxy2/upload_file";
	public static final String RootPath = "D:/Users/yxy2/upload_file";
	public static final String ImgUserPath = "/img/user";
	
	//手机验证码登录配置
	public static final String BASE_URL = "https://api.miaodiyun.com/20150822";
	public static final String ACCOUNT_SID = "00ca5ed4126d4b6d9a0488a19d4c7c01";
	public static final String AUTH_TOKEN = "49275cf94a1c471b923155342cdde511";
	public static final String RESP_DATA_TYPE = "json";
	
	//每页显示的条数
	public static final int HomeLessonPageSize = 3;
	public static final int HomeFreeListenPageSize = 3;
	public static final int TeacherPageSize = 3;
	public static final int SorderPageSize = 5;
	public static final int BookPageSize = 5;
	public static final int MessagePageSize = 2;
	public static final int LessonPageSize = 3;
	public static final int FreeListenPageSize = 3;
	
	//订单(Sorder)相关状态
	public static final String SorderStatusUnpaid = "待付款";
	public static final String SorderStatusPaid = "已付款";
	public static final String SorderStatusRefunding = "退款中";
	public static final String SorderStatusRefunded = "已退款";
	public static final String SorderStatusCAV = "已核销";
	
	//退款记录(Refund)相关状态
	public static final String RefundStatusUnprocessed = "待处理";
	public static final String RefundStatusProcessed = "已处理";
	
	//轮播图的类型
	public static final String EnterpriseSwiper = "E";
	public static final String TeacherSwiper = "T";
	
	//免费试听课相关状态
	public static final String BookStatusUnprocessed = "待处理";
	public static final String BookStatusProcessed = "已处理";
}