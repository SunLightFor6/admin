package com.lamport.admin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lamport.admin.po.Admin;

/**
 * 拦截器，判断管理员是否登陆
 * @author Lin Zhao, protector of Sherry
 *
 */
public class AdminInterceptor implements HandlerInterceptor {

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
		System.out.println("..........AdminInterceptor..........preHandler()..........");

		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		if(admin != null){
			return true;
		}else{
			response.sendRedirect(request.getContextPath()+"/main/login.html");
			return false;
		}
	}

}
