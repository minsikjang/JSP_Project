package com.tjoeun.controller.auth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tjoeun.dto.userDTO;
import com.tjoeun.service.userService;


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
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String id = (String) request.getParameter("id");
		String password = (String) request.getParameter("pwd");
		
		if (id != null && password != null) {
			userDTO dto = new userDTO();
			dto.setId(id);
			dto.setPassword(password);
			
			userDTO user = userService.getInstance().getUser(dto);
			//userDTO adminUser = new userDTO();
			
			if (user == null) {
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('사번정보를 확인 할 수 없습니다.'); window.location.href='" + request.getContextPath() + "/auth/login.do'</script>"); 
				writer.close();
			} else {
				HttpSession session = request.getSession();

				session.setAttribute("ID", user.getId());
				session.setAttribute("Name", user.getName());
				
				//session.setAttribute("ID", adminUser.getId());
				//session.setAttribute("Name", adminUser.getName());
				
				response.sendRedirect(request.getContextPath() + (path == null ? "/admin" : path));
			}
			
		}
	}

}
