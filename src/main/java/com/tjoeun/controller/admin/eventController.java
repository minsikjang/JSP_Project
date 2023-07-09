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

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tjoeun.common.paging;
import com.tjoeun.dto.eventDTO;
import com.tjoeun.dto.memberDTO;
import com.tjoeun.service.eventService;
import com.tjoeun.service.memberService;


@WebServlet("/admin/event.do")
public class eventController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public eventController() {
        //super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 받을때
		response.setContentType("text/html; charset=UTF-8"); // 보낼때
		
		int currentPage = request.getParameter("currentPage") == null ? 1 : Integer.parseInt(request.getParameter("currentPage"));
		
		eventDTO dto = new eventDTO();
		//dto.setIdx(request.getParameter("idx"));
		dto.setTitle(request.getParameter("title"));
		dto.setContent(request.getParameter("content"));
		dto.setUrl(request.getParameter("url"));
		dto.setCurrentPage((currentPage - 1) * 10);
		
		List<eventDTO> list = eventService.getInstance().eventList(dto);
		int totalCount = eventService.getInstance().eventCount(dto);
		
		request.setAttribute("list", list);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("paging", new paging(totalCount, currentPage, 10, dto.queryString()).getPage());
		
		String viewpage = "/WEB-INF/views/admin/event.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewpage);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 받을때
		response.setContentType("text/html; charset=UTF-8"); // 보낼때
		
		MultipartRequest mr = new MultipartRequest(
			request, // 요청 객체
			request.getSession().getServletContext().getRealPath("/upload"),	// 파일이 업로드 될 실제 경로
			5 * 1024 * 1024, // 업로드 할 파일의 최대 크기를 바이트 단위로 지정한다.
			"UTF-8", // 문자 인코딩 방식
			new DefaultFileRenamePolicy() // 업로드 되는 파일과 같은 이름의 파일이 존재할 경우 이름을 자동으로 변경해주는 객체
		);
		
		String originalFilename = mr.getOriginalFileName("file");
		String title = mr.getParameter("title");
		
		eventDTO dto = new eventDTO();
		dto.setTitle(mr.getParameter("title"));
		dto.setContent(mr.getParameter("content"));
		dto.setUrl(mr.getParameter("url"));
		dto.setFilename(mr.getFilesystemName("file"));
		
		eventService.getInstance().insertEvent(dto);
		
		JSONObject returnObj = new JSONObject();
		
		returnObj.put("result", "success");
		returnObj.put("message", "저장 완료.");
		
		PrintWriter writer = response.getWriter();
		writer.print(returnObj.toJSONString());
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
			
			eventService.getInstance().deleteEvent(delData);
			
			JSONObject returnObj = new JSONObject();
			
			returnObj.put("result", "success");
			returnObj.put("message", "삭제 완료.");
			
			PrintWriter writer = response.getWriter();
			writer.print(returnObj.toJSONString());
        } catch (ParseException e) {
			e.printStackTrace();
		}
        
	}

}
