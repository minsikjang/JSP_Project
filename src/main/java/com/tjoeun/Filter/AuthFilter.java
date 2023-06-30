package com.tjoeun.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/admin/*"})
public class AuthFilter implements Filter {

	@Override
    public void init(FilterConfig config) throws ServletException{}
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
        HttpServletRequest httpRequeset = (HttpServletRequest)request;
        HttpSession session = httpRequeset.getSession(false);
        
        boolean login =false;
        if(session != null) {
            if (session.getAttribute("ID") != null) {
            	login = true;
            }
    	}
        
        if(login){
            chain.doFilter(request, response);
        } else {
        	HttpServletResponse httpResponse = (HttpServletResponse)response;
        	String path = httpRequeset.getServletPath();
            httpResponse.sendRedirect(httpRequeset.getContextPath() + "/auth/login.do?path=" + path);
        }
    }
    
    @Override
    public void destroy(){}
	
}
