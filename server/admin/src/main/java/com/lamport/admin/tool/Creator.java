package com.lamport.admin.tool;

/**
 * 创造类，用来创造本程序所需的各种变量
 * @author Lin Zhao, protector of Sherry
 *
 */
public class Creator {
	
	public static String createAdminName(){
		long usefulTime = System.currentTimeMillis() - Const.UselessTime;
		String adminName = Long.toString(usefulTime);
		return adminName;
	}
}
