package com.lamport.education.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lamport.education.po.User;

public class UserValidation implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse response, Object arg2) throws Exception {
		 
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		req.getRequestURL();
		if(user!=null){
			System.out.println("拦截器用户验证成功,用户已登陆");
			return true;
		}
		else{
			System.out.println("拦截器用户验证失败，用户没登陆");
			String path = "/login.html";
			reDirect(req,response,path);
			//req.getRequestDispatcher("../login.jsp").forward(request, arg1);
			return false;
		}
		
	}
	
	
	public void reDirect(HttpServletRequest request, HttpServletResponse response, String path) throws Exception{
		System.out.println("..........AdminInterceptor..........reDirect()..........");
        //获取当前请求的路径
        String basePath = request.getScheme() + "://" + request.getServerName() + ":"  + request.getServerPort() + "/lamport/education";
        System.out.println(basePath);
        //如果request.getHeader("X-Requested-With") 返回的是"XMLHttpRequest"说明就是ajax请求，需要特殊处理
        if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
            //告诉ajax我是重定向
            response.setHeader("REDIRECT", "REDIRECT");
            //告诉ajax我重定向的路径
            response.setHeader("CONTENTPATH", basePath + path);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }else{
            response.sendRedirect(basePath + path);
        }
	}

}
