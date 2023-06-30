package com.tjoeun.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjoeun.dto.memberDTO;
import com.tjoeun.service.memberService;


@WebServlet("/admin/member.do")
public class memberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public memberController() {
        //super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<memberDTO> list = memberService.getInstance().memberList(0);
		
		request.setAttribute("list", list);
		
		String viewpage = "/WEB-INF/views/admin/member.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewpage);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
