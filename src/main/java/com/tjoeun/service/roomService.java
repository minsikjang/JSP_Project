package com.tjoeun.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tjoeun.dao.roomDAO;
import com.tjoeun.dao.roomDAO;
import com.tjoeun.dto.roomDTO;
import com.tjoeun.dto.roomDTO;
import com.tjoeun.resource.MySession;


public class roomService {
	private static roomService instance;
	
	public static roomService getInstance() {
		if (instance == null) {
			instance = new roomService();
		}
		
		return instance;
	}
	
	public List<roomDTO> roomList(roomDTO dto) {
		SqlSession mapper = MySession.getSession();
		roomDAO dao = roomDAO.getInstance();
		
		List<roomDTO> roomList = dao.roomList(mapper, dto);
		
		mapper.close();
		return roomList;
	}
	
	  public int roomCount(roomDTO dto) { 
		  SqlSession mapper =MySession.getSession(); 
		  roomDAO dao = roomDAO.getInstance();
	  
	  int roomCount = dao.roomCount(mapper, dto);
	  
	  mapper.close(); 
	  return roomCount;
	  }
	  
	  
	  public roomDTO getroom(roomDTO dto) { 
		  SqlSession mapper =MySession.getSession(); 
		  roomDAO dao = roomDAO.getInstance();
	  
	  
			roomDTO user = dao.getroom(mapper, dto);

			mapper.close();
			return user;
		}

		public void insertroom(roomDTO dto) {
			SqlSession mapper = MySession.getSession();
			roomDAO dao = roomDAO.getInstance();

			dao.insertroom(mapper, dto);

			mapper.commit();
			mapper.close();
		}

		public void deleteroom(String[] delData) {
			SqlSession mapper = MySession.getSession();
			roomDAO dao = roomDAO.getInstance();

			dao.deleteroom(mapper, delData);

			mapper.commit();
			mapper.close();
		}
		public void updateroom(roomDTO dto) {
			SqlSession mapper = MySession.getSession();
			roomDAO dao = roomDAO.getInstance();
			
			dao.updateroom(mapper, dto);
			
			mapper.commit();
			mapper.close();
			
		}
/*
		public void withdrawroom(roomDTO dto) {
			SqlSession mapper = MySession.getSession();
			roomDAO dao = roomDAO.getInstance();
			
			dao.withdrawroom(mapper, dto);
			
			mapper.commit();
			mapper.close();
		}

		public roomDTO getLogin(roomDTO dto) {
			SqlSession mapper = MySession.getSession();
			roomDAO dao = roomDAO.getInstance();
			
			
			roomDTO user = dao.getLogin(mapper, dto);
			
			mapper.close();
			return user;
		}
*/

}
