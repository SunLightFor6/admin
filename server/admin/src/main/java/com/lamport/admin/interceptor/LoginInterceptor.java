package com.lamport.admin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lamport.admin.po.Admin;

/**
 * 拦截器，判断是否有账号登陆
 * @author Lin Zhao, protector of Sherry
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

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
		System.out.println("..........LoginInterceptor..........preHandler()..........");

		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		Admin superAdmin = (Admin)session.getAttribute("superAdmin");
		if(admin == null && superAdmin == null){
			return true;
		}else if(admin != null){
			response.sendRedirect(request.getContextPath()+"/main/page/index.html");
			return false;
		}else{
			response.sendRedirect(request.getContextPath()+"/main/superadmin/enterprise_index.html");
			return false;
		}
	}

}
