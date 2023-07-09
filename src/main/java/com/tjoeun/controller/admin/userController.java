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
import com.tjoeun.dto.userDTO;
import com.tjoeun.service.memberService;
import com.tjoeun.service.userService;


@WebServlet("/admin/user.do")
public class userController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public userController() {
        //super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		request.setCharacterEncoding("UTF-8"); // 받을때
 		response.setContentType("text/html; charset=UTF-8"); // 보낼때
		
 		String idx = request.getParameter("idx");
		
	 	if (idx == null) {
	 		int currentPage = request.getParameter("currentPage") == null ? 1 : Integer.parseInt(request.getParameter("currentPage"));

	 		userDTO dto = new userDTO();
	 		dto.setNum(request.getParameter("num"));
	 		dto.setId(request.getParameter("id"));
	 		dto.setPassword(request.getParameter("password"));
	 		dto.setPosition(request.getParameter("position"));
	 		dto.setName(request.getParameter("name"));
	 		dto.setPhone(request.getParameter("phone"));
	 		dto.setEmail(request.getParameter("email"));
	 		
			dto.setCurrentPage((currentPage - 1) * 10);
	 		
	 		
			List<userDTO> list = userService.getInstance().userList(dto);
			int totalCount = userService.getInstance().userCount(dto);
			
			
	        request.setAttribute("list", list);
	        request.setAttribute("totalCount", totalCount);
	        request.setAttribute("paging", new paging(totalCount, currentPage, 10, dto.queryString()).getPage());

			
			String viewpage = "/WEB-INF/views/admin/user.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewpage);
			dispatcher.forward(request, response);
			
	 	} else {
	 		
	 		userDTO dto = new userDTO();
			dto.setIdx(Integer.parseInt(idx));
			
	 		userDTO user = userService.getInstance().getUser(dto);
	 		
	 		JSONObject returnObj = new JSONObject();
	 		
	 		returnObj.put("idx", user.getIdx());
	 		returnObj.put("num", user.getNum());
	 		returnObj.put("id", user.getId());
	 		returnObj.put("password", user.getPassword());
	 		returnObj.put("position", user.getPosition());
	 		returnObj.put("name", user.getName());
	 		returnObj.put("phone", user.getPhone());
	 		returnObj.put("email", user.getEmail());
	 		
	 		PrintWriter writer = response.getWriter();
	 		writer.print(returnObj.toJSONString());
	 	}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/html; charset=UTF-8");
        
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
			
	        userDTO dto = new userDTO();
	        
	        dto.setId(jsonObject.get("id").toString());
	        dto.setPassword(jsonObject.get("password").toString());
	        dto.setPosition(jsonObject.get("position").toString());
	        dto.setName(jsonObject.get("name").toString());
	        dto.setPhone(jsonObject.get("phone").toString());
	        dto.setEmail(jsonObject.get("email").toString());
	        
	        if (jsonObject.get("idx").toString().equals("")) {
	        	userService.getInstance().insertUser(dto);
			} else {
				dto.setIdx(Integer.parseInt(jsonObject.get("idx").toString()));
				userService.getInstance().updateUser(dto);
			}
	        
	        JSONObject returnObj = new JSONObject();
	        returnObj.put("result", "success");
			returnObj.put("message", "저장 완료");
	        
			PrintWriter writer = response.getWriter();
			writer.print(returnObj.toJSONString());
		} catch (ParseException e) {
			e.printStackTrace();
		}

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
			
			userService.getInstance().deleteUser(delData);
			
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
			userDTO dto = new userDTO();
			dto.setIdx(Integer.parseInt(jsonObject.get("idx").toString()));
			dto.setNum(jsonObject.get("num").toString());
			dto.setId(jsonObject.get("id").toString());
			dto.setPassword(jsonObject.get("password").toString());
			dto.setPosition(jsonObject.get("position").toString());
			dto.setName(jsonObject.get("name").toString());
			dto.setPhone(jsonObject.get("phone").toString());
			dto.setEmail(jsonObject.get("email").toString());
			userService.getInstance().updateUser(dto);
			
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
