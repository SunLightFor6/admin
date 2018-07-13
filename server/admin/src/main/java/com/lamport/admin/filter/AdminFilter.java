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

public class AdminFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println(".....AdminFilter.........doFilter()........过滤....");
		
		HttpSession session = ((HttpServletRequest)request).getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		if(admin != null){
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
