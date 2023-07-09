package com.tjoeun.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.tjoeun.dto.roomDTO;


public class roomDAO {
	private static roomDAO instance;
	
	public static roomDAO getInstance () {
		if (instance == null) {
			instance = new roomDAO();
		}
		
		return instance;
	}
	
	public ArrayList<roomDTO> roomList(SqlSession mapper, roomDTO dto) {
		
		return (ArrayList<roomDTO>) mapper.selectList("roomList", dto);
	}
	public int roomCount(SqlSession mapper, roomDTO dto) {
		return (Integer) mapper.selectOne("roomCount", dto);
	}
	

	public roomDTO getroom(SqlSession mapper, roomDTO dto) {
		return (roomDTO) mapper.selectOne("getroom", dto);
	}

	public void insertroom(SqlSession mapper, roomDTO dto) {
		mapper.insert("insertroom", dto);
	}

	public void deleteroom(SqlSession mapper, String[] delData) {
		mapper.delete("deleteroom", delData);
	}

	public void updateroom(SqlSession mapper, roomDTO dto) {
		mapper.update("updateroom", dto);
	}
	/*
	 * public void withdrawMember(SqlSession mapper, memberDTO dto) {
	 * mapper.update("withdrawMember", dto); }
	 * 
	 * public memberDTO getLogin(SqlSession mapper, memberDTO dto) { return
	 * (memberDTO) mapper.selectOne("getLogin", dto); }
	 */
}
