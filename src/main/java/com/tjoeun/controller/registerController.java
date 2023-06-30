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

@WebServlet("/login/register.do")
public class registerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public registerController() {
        //super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 받을때
		//response.setContentType("text/html; charset=UTF-8"); // 보낼때
		
		String id = request.getParameter("id");
		
		if (id == null) {
			String viewpage = "/WEB-INF/views/login/register.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewpage);
			dispatcher.forward(request, response);
		} else {
			idConfirm(request, response, id);
		}
	}
	
	private void idConfirm(HttpServletRequest request, HttpServletResponse response, String id) throws IOException {
		//request.setCharacterEncoding("UTF-8"); // 받을때
		response.setContentType("text/html; charset=UTF-8"); // 보낼때
		
		memberDTO dto = new memberDTO();
		dto.setId(id);
		
		memberDTO user = memberService.getInstance().getMember(dto);
		
		JSONObject returnObj = new JSONObject();
		
		if (user == null) {
			returnObj.put("result", 1);
			returnObj.put("message", "사용 가능한 아이디입니다.");
		} else {
			returnObj.put("result", 0);
			returnObj.put("message", "이미 사용중인 아이디입니다.");
		}
		//String result = user == null ? "사용 가능한 아이디입니다." : "이미 사용중인 아이디입니다.";
		//returnObj.put("result", result);
		
		PrintWriter writer = response.getWriter();
		writer.print(returnObj.toJSONString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 받을때
		//response.setContentType("text/html; charset=UTF-8"); // 보낼때
		
		System.out.println(request.getParameter("name"));
		
		memberDTO dto = new memberDTO();
		dto.setId(request.getParameter("id"));
		dto.setName(request.getParameter("name"));
		dto.setPassword(request.getParameter("pwd"));
		dto.setPostcode(Integer.parseInt(request.getParameter("postcode")));
		dto.setJumin1(request.getParameter("jumin1"));
		dto.setJumin2(request.getParameter("jumin2"));
		dto.setEmail1(request.getParameter("email1"));
		dto.setEmail2(request.getParameter("email2"));
		dto.setAddr1(request.getParameter("addr1"));
		dto.setAddr2(request.getParameter("addr2"));
		dto.setPhone(request.getParameter("phone"));
		
		memberService.getInstance().insertMember(dto);
		
		response.sendRedirect(request.getContextPath() +  "/index.jsp");
	}

}
