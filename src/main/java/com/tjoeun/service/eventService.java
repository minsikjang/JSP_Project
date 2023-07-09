package com.tjoeun.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tjoeun.dao.eventDAO;
import com.tjoeun.dao.memberDAO;
import com.tjoeun.dto.eventDTO;
import com.tjoeun.dto.memberDTO;
import com.tjoeun.resource.MySession;


public class eventService {
	private static eventService instance;
	
	public static eventService getInstance() {
		if (instance == null) {
			instance = new eventService();
		}
		
		return instance;
	}
	
	public List<eventDTO> eventList(eventDTO dto) {
		SqlSession mapper = MySession.getSession();
		eventDAO dao = eventDAO.getInstance();
		
		List<eventDTO> eventList = dao.eventList(mapper, dto);
		
		mapper.close();
		return eventList;
	}
	
	public int eventCount(eventDTO dto) {
		SqlSession mapper = MySession.getSession();
		eventDAO dao = eventDAO.getInstance();
		
		int memberCount = dao.eventCount(mapper, dto);
		
		mapper.close();
		return memberCount;
	}
	
//	public memberDTO getMember(memberDTO dto) {
//		SqlSession mapper = MySession.getSession();
//		memberDAO dao = memberDAO.getInstance();
//		
//		
//		memberDTO user = dao.getMember(mapper, dto);
//		
//		mapper.close();
//		return user;
//	}

	public void insertEvent(eventDTO dto) {
		SqlSession mapper = MySession.getSession();
		eventDAO dao = eventDAO.getInstance();
		
		dao.insertEvent(mapper, dto);
		
		mapper.commit();
		mapper.close();
	}

	public void deleteEvent(String[] delData) {
		SqlSession mapper = MySession.getSession();
		eventDAO dao = eventDAO.getInstance();
		
		dao.deleteEvent(mapper, delData);
		
		mapper.commit();
		mapper.close();
	}

}
