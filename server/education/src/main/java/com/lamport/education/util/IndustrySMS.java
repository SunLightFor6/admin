package com.lamport.education.util;

import java.net.URLEncoder;

/**
 * 验证码通知短信接口
 *
 * @ClassName: IndustrySMS
 * @Description: 验证码通知短信接口
 */
public class IndustrySMS {
	
	private static String operation = "/industrySMS/sendSMS";
	private static String accountSid = Config.ACCOUNT_SID;
	
	/**
	 * 验证码通知短信
	 * 
	 * @return 验证码
	 */
	public static String execute(String to) {
		
		String code = smsCode();
		String tmpSmsContent = null;
		
	    try {
	    	// 【康迪星科技】登录验证码：{1}，如非本人操作，请忽略此短信。
	    	String smsContent = "【康迪星科技】登录验证码：" + code + "，如非本人操作，请忽略此短信。";
	    	tmpSmsContent = URLEncoder.encode(smsContent, "UTF-8");
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
	    String url = Config.BASE_URL + operation;
	    String body = "accountSid=" + accountSid + "&to=" + to + "&smsContent=" + tmpSmsContent + HttpUtil.createCommonParam();

	    // 提交请求
	    String result = HttpUtil.post(url, body);
	    System.out.println("result:" + System.lineSeparator() + result);
	    
	    return code;
	    
	}
		
	/**
	 * 创建验证码
	 */ 
	public static String smsCode(){
		String random = (int)((Math.random() * 9 + 1) * 100000) + ""; 
		System.out.println("验证码：" + random);
		return random;
	}
	
}
