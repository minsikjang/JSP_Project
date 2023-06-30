package com.tjoeun.controller.auth;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/auth/login.do")
public class loginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String path;
    
    public loginController() {
        //super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		path = (String) request.getParameter("path");
		
		String viewpage = "/WEB-INF/views/admin/login.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewpage);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = (String) request.getParameter("id");
		String pwd = (String) request.getParameter("pwd");
		
		System.out.println((String) request.getParameter("addr"));
		
		if (id != null && pwd != null) {
			HttpSession session = request.getSession();

			session.setAttribute("ID", id);
			
			response.sendRedirect(request.getContextPath() + (path == null ? "/admin" : path));
		} else {
			
		}
	}

}
