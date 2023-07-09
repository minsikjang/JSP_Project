package com.tjoeun.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.tjoeun.dto.eventDTO;


public class eventDAO {
	private static eventDAO instance;
	
	public static eventDAO getInstance () {
		if (instance == null) {
			instance = new eventDAO();
		}
		
		return instance;
	}
	
	public ArrayList<eventDTO> eventList(SqlSession mapper, eventDTO dto) {
		return (ArrayList<eventDTO>) mapper.selectList("eventList", dto);
	}
	
	public int eventCount(SqlSession mapper, eventDTO dto) {
		return (Integer) mapper.selectOne("eventCount", dto);
	}

	public void insertEvent(SqlSession mapper, eventDTO dto) {
		mapper.insert("insertEvent", dto);
	}

	public void deleteEvent(SqlSession mapper, String[] delData) {
		mapper.delete("deleteEvent", delData);
	}

}
