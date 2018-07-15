package com.lamport.admin.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lamport.admin.po.Admin;

/**
 * 过滤器，判断superAdmin是否登录
 * @author Lin Zhao, protector of Sherry
 *
 */
public class SuperAdminFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println(".....SuperAdminFilter.........doFilter()........过滤....");
		
		HttpSession session = ((HttpServletRequest)request).getSession();
		Admin superAdmin = (Admin)session.getAttribute("superAdmin");
		if(superAdmin != null){
			chain.doFilter(request, response);
		}else{
			((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath()+"/main/login.html");
		}	
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}