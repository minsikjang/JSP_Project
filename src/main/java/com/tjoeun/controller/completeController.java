package com.tjoeun.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.tjoeun.dto.memberDTO;
import com.tjoeun.service.memberService;

@WebServlet("/login/complete.do")
public class completeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public completeController() {
        //super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 받을때
		//response.setContentType("text/html; charset=UTF-8"); // 보낼때
		
		String id = request.getParameter("id");
		
		request.setAttribute("id", id);
		
		if (id != null) {
			String viewpage = "/WEB-INF/views/login/complete.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewpage);
			dispatcher.forward(request, response);
		} 
	}
	
}
