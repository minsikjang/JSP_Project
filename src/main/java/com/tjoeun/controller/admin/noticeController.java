package com.tjoeun.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
import com.tjoeun.dto.adminNoticeDTO;
import com.tjoeun.service.adminNoticeService;

@WebServlet("/admin/notice.do")
public class noticeController extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    
    public noticeController() {
        //super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// 	   System.out.println(request.getParameter("idx"));
 	   String idx = request.getParameter("idx");
 	   
 	   if (idx != null) {
 	     
 		   request.setCharacterEncoding("UTF-8"); // 받을때
 		   response.setContentType("text/html; charset=UTF-8"); // 보낼때
 		   
 		   adminNoticeDTO notice = adminNoticeService.getInstance().selectByIdx(Integer.parseInt(idx));
 		  
 		   	request.setAttribute("notice", notice);
 		  
	 		 JSONObject returnObj = new JSONObject();
	         returnObj.put("name", notice.getName());
	         returnObj.put("subject", notice.getSubject());
	         returnObj.put("context", notice.getContext());
	         
	         PrintWriter writer = response.getWriter();
	         writer.print(returnObj.toJSONString());
//	         System.out.println(returnObj.toJSONString());
	         
 	  } else {
 		  int currentPage = request.getParameter("currentPage") == null ? 1 : Integer.parseInt(request.getParameter("currentPage"));
 		  
 		  adminNoticeDTO dto = new adminNoticeDTO();
 		  dto.setName(request.getParameter("name"));
 		  dto.setSubject(request.getParameter("subject"));
 		  dto.setCurrentPage((currentPage - 1) * 10);
 		  
 		  List<adminNoticeDTO> list = adminNoticeService.getInstance().noticeList(dto);
 		  int totalCount = adminNoticeService.getInstance().noticeCount(dto);
 		  
 		  
 		  request.setAttribute("list", list);
 		  request.setAttribute("totalCount", totalCount);
 		  request.setAttribute("paging", new paging(totalCount, currentPage, 10, dto.queryString()).getPage());
 		 
 		  String viewpage = "/WEB-INF/views/admin/notice.jsp";
 		  RequestDispatcher dispatcher = request.getRequestDispatcher(viewpage);
 		  dispatcher.forward(request, response);
 	  }
       
       
       
    }


   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      doGet(request, response);
//      System.out.println("999");// 테스트 
      request.setCharacterEncoding("UTF-8"); // 받을때
      response.setContentType("text/html; charset=UTF-8"); // 보낼때

      
      String idx = request.getParameter("idx");
      String name = request.getParameter("name");
      String subject = request.getParameter("subject");
      String context = request.getParameter("context");
      String action = request.getParameter("action");
      LocalDateTime now = LocalDateTime.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy:MM:dd(E) a h:mm:ss", Locale.KOREA);
      String formattedDateTime = now.format(formatter);
      Date rewriteDate = null;
      try {
          rewriteDate = new SimpleDateFormat("yyyy:MM:dd(E) a h:mm:ss").parse(formattedDateTime);
      } catch (java.text.ParseException e) {
          e.printStackTrace();
      }
     
      adminNoticeDTO notice = new adminNoticeDTO();
      notice.setName(name);
      notice.setSubject(subject);
      notice.setContext(context);
      notice.setRewrite(rewriteDate);   //재작성 일 
      
      
      
	  if (action != null && action.equals("insert")) {
		  adminNoticeService.getInstance().insert(notice);
	        // insert 액션 요청 시 insert 메서드 호출
	    } else if (action != null && action.equals("update")) {
	        // update 액션 요청 시 update 메서드 호출
//	    	System.out.println("업데이트 메소드 실행");
	    	notice.setIdx(Integer.parseInt(idx));
	    	adminNoticeService.getInstance().update(notice);
	    }
	  
	  JSONObject returnObj = new JSONObject();
      
	  returnObj.put("result", "success");
	  returnObj.put("message", "저장 완료.");
      
	  PrintWriter writer = response.getWriter();
	  writer.print(returnObj.toJSONString());
    
      
   }

@Override
   protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	System.out.println("삭제 실행");
      request.setCharacterEncoding("UTF-8"); // 받을때
      response.setContentType("text/html; charset=UTF-8"); // 보낼때
      
      String[] delData = request.getParameter("delData").split(",");
      
      
      adminNoticeService.getInstance().deleteNotice(delData);
      
      JSONObject returnObj = new JSONObject();
      
      returnObj.put("result", "success");
      returnObj.put("message", "삭제 완료.");
      
      PrintWriter writer = response.getWriter();
      writer.print(returnObj.toJSONString());
   }
   
   
}