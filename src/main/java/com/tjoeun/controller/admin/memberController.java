package com.tjoeun.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.tjoeun.common.paging;
import com.tjoeun.dto.memberDTO;
import com.tjoeun.service.memberService;


@WebServlet("/admin/member.do")
public class memberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public memberController() {
        //super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage = request.getParameter("currentPage") == null ? 1 : Integer.parseInt(request.getParameter("currentPage"));
		
		memberDTO dto = new memberDTO();
		dto.setId(request.getParameter("id"));
		dto.setName(request.getParameter("name"));
		dto.setPhone(request.getParameter("phone"));
		dto.setUseYN(request.getParameter("useYN"));
		dto.setCurrentPage((currentPage - 1) * 10);
		
		
		List<memberDTO> list = memberService.getInstance().memberList(dto);
		int totalCount = memberService.getInstance().memberCount(dto);
		
		request.setAttribute("list", list);
		request.setAttribute("paging", new paging(totalCount, currentPage, 10, dto.queryString()).getPage());
		
		String viewpage = "/WEB-INF/views/admin/member.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewpage);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 받을때
		response.setContentType("text/html; charset=UTF-8"); // 보낼때
		
		String[] delData = request.getParameter("delData").split(",");
		
		memberService.getInstance().deleteMember(delData);
		
		JSONObject returnObj = new JSONObject();
		
		returnObj.put("result", "success");
		returnObj.put("message", "삭제 완료.");
		
		PrintWriter writer = response.getWriter();
		writer.print(returnObj.toJSONString());
	}

	
}
