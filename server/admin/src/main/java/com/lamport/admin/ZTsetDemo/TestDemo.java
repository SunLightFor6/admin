package com.lamport.admin.ZTsetDemo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.lamport.admin.tool.Creator;

public class TestDemo {

	public static void main(String[] args) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
//		String currentTime = "2018-01-01 00:00:00";
//		Date date = new Date();
//		Date date = df.parse(currentTime);
//		String current = df.format(date);
//		System.out.println("current = " + current);
		
		calendar.add(Calendar.DATE, 0);
		String day = df.format(calendar.getTime());
		System.out.println(0 + " : " + day);
		for(int i=1; i<30; i++){
			calendar.add(Calendar.DATE, -1);
			day = df.format(calendar.getTime());
			System.out.println(i + " : " + day);
		}
		
	}

}
