package com.lamport.admin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lamport.admin.po.Admin;
import com.lamport.admin.tool.Const;

/**
 * 拦截器，判断超级管理员是否登陆
 * @author Lin Zhao, protector of Sherry
 *
 */
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
			String path = "/main/login.html";
			reDirectAdmin(request, response, path);
			return false;
		}
	}
	
	/**
	 * 重定向函数
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void reDirectAdmin(HttpServletRequest request, HttpServletResponse response, String path) throws Exception{
		System.out.println("..........SuperAdminInterceptor..........reDirect()..........");
        //获取当前请求的路径
        String basePath = request.getScheme() + "://" + request.getServerName() + ":"  + request.getServerPort() + Const.redirectPath;
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
