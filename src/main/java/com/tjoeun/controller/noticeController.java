package com.tjoeun.controller;

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

@WebServlet("/notice.do")
public class noticeController extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    
    public noticeController() {
        //super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// 	   System.out.println(request.getParameter("idx"));
 	   //String idx = request.getParameter("idx");
 	    
 	   adminNoticeDTO dto = new adminNoticeDTO();
 	   //dto.setName(request.getParameter("name"));
 	   //dto.setSubject(request.getParameter("subject"));
	  
 	   List<adminNoticeDTO> list = adminNoticeService.getInstance().noticeList(dto);
 	   System.out.println(list.toString());
 	   request.setAttribute("list", list);
	 
 	   String viewpage = "/WEB-INF/views/notice.jsp";
 	   RequestDispatcher dispatcher = request.getRequestDispatcher(viewpage);
 	   dispatcher.forward(request, response);
       
    }


   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

   }
  
}