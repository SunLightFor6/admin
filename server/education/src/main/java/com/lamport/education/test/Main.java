package com.lamport.education.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lamport.education.util.IndustrySMS;

public class Main {

	public static void main(String[] args) throws ParseException {
		
		// 验证码通知短信接口
//		IndustrySMS.execute("15804032497");
//		JsonObject jsonObject = new JsonObject();
//		JsonArray jsonArray = new JsonArray();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String date = df.format(System.currentTimeMillis());
//		String date = "2018-08-02 ";
//		String lastdate = "2018-08-01 ";
//		String time = " 00:00:00";
		System.out.println("date=" + date + "...");
//		System.out.println("lastdate=" + lastdate + "...");
//		System.out.println("time=" + time + "...");
//
//		Date date1 = df.parse(date + time);
//		Date date2 = df.parse(lastdate + time);
//		long d1 = date1.getTime();
//		long d2 = date2.getTime();
//		
//		int result = (int)((d1-d2)/(24*60*60*1000));
		String sdate = date.substring(0, 10);
		System.out.println("sdate=" + sdate + "...");
		
	}

}
