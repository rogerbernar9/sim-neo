package sim.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import sim.manager.LoginMB;

public class FiltroLogin implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		LoginMB login = (LoginMB) ((HttpServletRequest) request).getSession().getAttribute("loginMB");
		System.out.println(login);
		if(login ==null || !login.isLogado())	{
			String contextPath = ((HttpServletRequest) request).getContextPath();
			System.out.println(contextPath);
			((HttpServletResponse) response).sendRedirect(contextPath + "/faces/public/login.xhtml"); 
			System.out.println(contextPath + "/faces/public/login.xhtml");
		}
		
		chain.doFilter(request, response); 
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
