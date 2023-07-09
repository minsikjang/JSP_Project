package com.tjoeun.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tjoeun.dao.userDAO;
import com.tjoeun.dto.userDTO;
import com.tjoeun.resource.MySession;


public class userService {
	private static userService instance;
	
	public static userService getInstance() {
		if (instance == null) {
			instance = new userService();
		}
		
		return instance;
	}
	
	public List<userDTO> userList(userDTO dto) {
		SqlSession mapper = MySession.getSession();
		userDAO dao = userDAO.getInstance();
		
		List<userDTO> userList = dao.userList(mapper,dto);
		
		mapper.close();
		return userList;
	}	   

	public int userCount(userDTO dto) { 
		SqlSession mapper = MySession.getSession(); 
		userDAO dao = userDAO.getInstance();
	  
		int userCount = dao.userCount(mapper, dto);
	  
		mapper.close(); 
		return userCount; 
	}
	  
	public userDTO getUser(userDTO dto) { 
		SqlSession mapper = MySession.getSession(); 
		userDAO dao = userDAO.getInstance();
	  
		userDTO user = dao.getUser(mapper, dto);
	  
		mapper.close(); 
		return user; 
	}
	  
	
	public void insertUser(userDTO dto) { 
		SqlSession mapper = MySession.getSession(); 
		userDAO dao = userDAO.getInstance();
	  
		dao.insertUser(mapper, dto);
	  
		mapper.commit(); 
		mapper.close(); 
	}
	  
	public void deleteUser(String[] delData) { 
		SqlSession mapper = MySession.getSession(); 
		userDAO dao = userDAO.getInstance();
	  
		dao.deleteUser(mapper, delData);
	  
		mapper.commit(); 
		mapper.close(); 
	}
	
	public void updateUser (userDTO dto) {
		SqlSession mapper = MySession.getSession();
		userDAO dao = userDAO.getInstance();
		
		dao.updateUser(mapper, dto);
		
		mapper.commit();
		mapper.close();
		
	}

	public void withdrawUser(userDTO dto) {
		SqlSession mapper = MySession.getSession();
		userDAO dao = userDAO.getInstance();
		
		dao.withdrawUser(mapper, dto);
		
		mapper.commit();
		mapper.close();
	}

	public userDTO getLogin(userDTO dto) {
		SqlSession mapper = MySession.getSession();
		userDAO dao = userDAO.getInstance();
		
		
		userDTO user = dao.getLogin(mapper, dto);
		
		mapper.close();
		return user;
	}

	
	
}
