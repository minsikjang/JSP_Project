package com.tjoeun.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tjoeun.dto.memberDTO;
import com.tjoeun.service.memberService;

@WebServlet("/login/login.do")
public class loginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public loginController() {
        //super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		
		if (id != null) {
			request.setAttribute("id", id);
			
			String viewpage = "/WEB-INF/views/login/complete.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewpage);
			dispatcher.forward(request, response);
			
		} else {
			String viewpage = "/WEB-INF/views/login/login.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewpage);
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String remember = request.getParameter("remember");
		String id = request.getParameter("id");
		String password = request.getParameter("pwd");
		
		if (remember == null) {
			response.addCookie(new Cookie("remember", "N"));
		} else {
			response.addCookie(new Cookie("remember", remember));
			response.addCookie(new Cookie("memberID", id));
		}
		
		memberDTO dto = new memberDTO();
		dto.setId(id);
		dto.setPassword(password);
		//System.out.println(dto.toString());
		memberDTO user = memberService.getInstance().getLogin(dto);
		
		if (user == null) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('회원정보를 확인 할 수 없습니다.'); window.location.href='" + request.getContextPath() + "/login/login.do'</script>"); 
			writer.close();
		} else {
			HttpSession session = request.getSession();

			session.setAttribute("memberID", user.getId());
			session.setAttribute("memberName", user.getName());
			
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}
	}

}
