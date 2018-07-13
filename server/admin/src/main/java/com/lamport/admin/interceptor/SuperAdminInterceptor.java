package com.lamport.admin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lamport.admin.po.Admin;

public class SuperAdminInterceptor implements HandlerInterceptor {

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
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		System.out.println("..........SuperAdminInterceptor..........preHandler()..........");
		
		HttpSession session = request.getSession();
		Admin superAdmin = (Admin)session.getAttribute("superAdmin");
		if(superAdmin != null){
			return true;
		}else{
			response.sendRedirect(request.getContextPath()+"/main/login.html");
			return false;
		}
	}

}
