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

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println(".....LoginFilter.........doFilter()........过滤....");
		
		HttpSession session = ((HttpServletRequest)request).getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		Admin superAdmin = (Admin)session.getAttribute("superAdmin");
		if(admin == null && superAdmin == null){
			chain.doFilter(request, response);
		}else if(admin != null){
			((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath()+"/main/page/index.html");
		}else{
			((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath()+"/main/superadmin/enterprise_index.html");
		}	
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
