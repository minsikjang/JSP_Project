package com.tjoeun.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.tjoeun.dto.memberDTO;
import com.tjoeun.service.memberService;

@WebServlet("/member/info.do")
public class infoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public infoController() {
        //super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 받을때
		response.setContentType("text/html; charset=UTF-8"); // 보낼때
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		System.out.println(id);
		if (id == null) {
			String viewpage = "/WEB-INF/views/member/info.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewpage);
			dispatcher.forward(request, response);
		} else {
			memberDTO dto = new memberDTO();
			dto.setId(id);
			dto.setPassword(password);
			
			memberDTO user = memberService.getInstance().getMember(dto);
			
			JSONObject returnObj = new JSONObject();
			
			if(user == null) {
				returnObj.put("result", "failed");
				returnObj.put("message", "회원을 찾을수 없습니다.");
			} else {
				returnObj.put("idx", user.getIdx());
				returnObj.put("id", user.getId());
				returnObj.put("name", user.getName());
				returnObj.put("password", user.getPassword());
				returnObj.put("jumin1", user.getJumin1());
				returnObj.put("jumin2", user.getJumin2());
				returnObj.put("postcode", user.getPostcode());
				returnObj.put("addr1", user.getAddr1());
				returnObj.put("addr2", user.getAddr2());
				returnObj.put("email1", user.getEmail1());
				returnObj.put("email2", user.getEmail2());
				returnObj.put("phone", user.getPhone());
				returnObj.put("useYN", user.getUseYN());
				returnObj.put("regdate", user.getRegdate().toString());
			}
			
			PrintWriter writer = response.getWriter();
			writer.print(returnObj.toJSONString());
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 받을때
		response.setContentType("text/html; charset=UTF-8"); // 보낼때
		
		String id = request.getParameter("id");
		//System.out.println(id);
		memberDTO dto = new memberDTO();
		dto.setId(id);
		
		memberService.getInstance().withdrawMember(dto);
		
		HttpSession session = request.getSession();
		
		session.removeAttribute("memberID");
		session.removeAttribute("memberName");
		
		JSONObject returnObj = new JSONObject();
		returnObj.put("result", "success");
		returnObj.put("message", "탈퇴 완료");
		
		PrintWriter writer = response.getWriter();
		writer.print(returnObj.toJSONString());
	}
}
