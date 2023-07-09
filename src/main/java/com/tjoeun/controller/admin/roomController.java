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
import com.tjoeun.dto.roomDTO;
import com.tjoeun.service.roomService;

@WebServlet("/admin/room.do")
public class roomController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public roomController() {
        // super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8"); // 받을때
		response.setContentType("text/html; charset=UTF-8"); // 보낼때
    	
	String idx = request.getParameter("idx");
		
		if (idx == null) {
			int currentPage = request.getParameter("currentPage") == null ? 1 : Integer.parseInt(request.getParameter("currentPage"));
					
			roomDTO dto = new roomDTO();
			dto.setRoomno(request.getParameter("roomno"));
			dto.setType(request.getParameter("type"));
			dto.setCapacity(request.getParameter("capacity"));
			dto.setPrice(request.getParameter("price"));
			dto.setCurrentPage((currentPage - 1) * 10);
			
			
			List<roomDTO> list = roomService.getInstance().roomList(dto);
			int totalCount = roomService.getInstance().roomCount(dto);
			
			request.setAttribute("list", list);
			request.setAttribute("totalCount", totalCount);
			request.setAttribute("paging", new paging(totalCount, currentPage, 10, dto.queryString()).getPage());
			
			String viewpage = "/WEB-INF/views/admin/room.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewpage);
			dispatcher.forward(request, response);
		} else {
			roomDTO dto = new roomDTO();
			dto.setIdx(Integer.parseInt(idx));
			
			roomDTO user = roomService.getInstance().getroom(dto);
			
			JSONObject returnObj = new JSONObject();
			
			returnObj.put("roomno", user.getRoomno());
			returnObj.put("type", user.getType());
			returnObj.put("size", user.getSize());
			returnObj.put("capacity", user.getCapacity());
			returnObj.put("price", user.getPrice());
			returnObj.put("number", user.getNumber());
			returnObj.put("regdate", user.getRegdate().toString());
			
			PrintWriter writer = response.getWriter();
			writer.print(returnObj.toJSONString());
		}
// 여기부터 원본.		
    /*  roomDTO room = new roomDTO();
        room.setRoomno(roomno);
        room.setType(type);
        room.setSize(size);
        room.setCapacity(capacity);
        room.setPrice(price);
        room.setNumber(number);
		
    	int currentPage = request.getParameter("currentPage") == null ? 1
                : Integer.parseInt(request.getParameter("currentPage"));

        roomDTO dto = new roomDTO();
        // dto.setIdx(Integer.parseInt(request.getParameter("idx"))); // 주석 처리된 부분
        dto.setType(request.getParameter("type"));
        // dto.setNumber(Integer.parseInt(request.getParameter("number"))); // 주석 처리된 부분
        dto.setCurrentPage((currentPage - 1) * 10);

        List<roomDTO> list = roomService.getInstance().roomList(dto);
        int totalCount = roomService.getInstance().roomCount(dto);

        request.setAttribute("list", list);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("paging", new paging(totalCount, currentPage, 10, dto.queryString()).getPage());

        String viewpage = "/WEB-INF/views/admin/room.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewpage);
        dispatcher.forward(request, response);
        */
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/html; charset=UTF-8");
        
        System.out.println(request.getParameter("roomno"));

        String idx = request.getParameter("idx");
        String roomno = request.getParameter("roomno");
        String type = request.getParameter("type");
        String size = request.getParameter("size");
        String capacity = request.getParameter("capacity");
        String price = request.getParameter("price");
        String number = request.getParameter("number");

        roomDTO dto = new roomDTO();
        dto.setRoomno(roomno);
        dto.setType(type);
        dto.setSize(size);
        dto.setCapacity(capacity);
        dto.setPrice(price);
        dto.setNumber(number);
        
        if (idx.equals("")) {
        	roomService.getInstance().insertroom(dto);
		} else {
			dto.setIdx(Integer.parseInt(idx));
			roomService.getInstance().updateroom(dto);
		}
        
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
			
			roomService.getInstance().deleteroom(delData);
			
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
