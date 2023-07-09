package com.tjoeun.controller.admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
		request.setCharacterEncoding("UTF-8"); // 받을때
		response.setContentType("text/html; charset=UTF-8"); // 보낼때
		
		String idx = request.getParameter("idx");
		
		if (idx == null) {
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
			request.setAttribute("totalCount", totalCount);
			request.setAttribute("paging", new paging(totalCount, currentPage, 10, dto.queryString()).getPage());
			
			String viewpage = "/WEB-INF/views/admin/member.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewpage);
			dispatcher.forward(request, response);
		} else {
			memberDTO dto = new memberDTO();
			dto.setIdx(Integer.parseInt(idx));
			
			memberDTO user = memberService.getInstance().getMember(dto);
			
			JSONObject returnObj = new JSONObject();
			
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
			
			PrintWriter writer = response.getWriter();
			writer.print(returnObj.toJSONString());
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 받을때
		response.setContentType("text/html; charset=UTF-8"); // 보낼때
		
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 받을때
		response.setContentType("text/html; charset=UTF-8"); // 보낼때
		
		String body = null;
        String parameter = "";
        
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
        
        while((body = br.readLine()) != null){
        	parameter += body;
        }
        
        JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = null;
        
        try {
			jsonObject = (JSONObject) jsonParser.parse(parameter);
			String[] delData = jsonObject.get("delData").toString().split(",");
			
			memberService.getInstance().deleteMember(delData);
			
			JSONObject returnObj = new JSONObject();
			
			returnObj.put("result", "success");
			returnObj.put("message", "삭제 완료.");
			
			PrintWriter writer = response.getWriter();
			writer.print(returnObj.toJSONString());
        } catch (ParseException e) {
			e.printStackTrace();
		}
        
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 받을때
		response.setContentType("text/html; charset=UTF-8"); // 보낼때
		
		String body = null;
        String parameter = "";
        
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
        
        while((body = br.readLine()) != null){
        	parameter += body;
        }
        
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = null;
		
		try {
			jsonObject = (JSONObject) jsonParser.parse(parameter);
			memberDTO dto = new memberDTO();
			dto.setIdx(Integer.parseInt(jsonObject.get("idx").toString()));
			dto.setId(jsonObject.get("id").toString());
			dto.setName(jsonObject.get("name").toString());
			dto.setPassword(jsonObject.get("password").toString());
			dto.setJumin1(jsonObject.get("jumin1").toString());
			dto.setJumin2(jsonObject.get("jumin2").toString());
			dto.setPostcode(Integer.parseInt(jsonObject.get("postcode").toString()));
			dto.setAddr1(jsonObject.get("addr1").toString());
			dto.setAddr2(jsonObject.get("addr2").toString());
			dto.setEmail1(jsonObject.get("email1").toString());
			dto.setEmail2(jsonObject.get("email2").toString());
			dto.setPhone(jsonObject.get("phone").toString());
			dto.setUseYN(jsonObject.get("useYN").toString());
			dto.setRegdate(Date.valueOf(jsonObject.get("regdate").toString()));
			
			memberService.getInstance().updateMember(dto);
			
			JSONObject returnObj = new JSONObject();
			returnObj.put("result", "success");
			returnObj.put("message", "수정 완료");
			
			PrintWriter writer = response.getWriter();
			writer.print(returnObj.toJSONString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
	}

	
}
