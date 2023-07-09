package com.tjoeun.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.tjoeun.dto.userDTO;


public class userDAO {
	private static userDAO instance;
	
	public static userDAO getInstance () {
		if (instance == null) {
			instance = new userDAO();
		}
		
		return instance;
	}
	
	public ArrayList<userDTO> userList(SqlSession mapper, userDTO dto) {
		
		return (ArrayList<userDTO>) mapper.selectList("userList", dto);
	}
	
	public int userCount(SqlSession mapper, userDTO dto) {
		return (Integer) mapper.selectOne("userCount", dto);
	}
	
	public userDTO getUser(SqlSession mapper, userDTO dto) {
		return (userDTO) mapper.selectOne("getUser", dto);
	}

	public void insertUser(SqlSession mapper, userDTO dto) {
		mapper.insert("insertUser", dto);
	}

	public void deleteUser(SqlSession mapper, String[] delData) {
		mapper.delete("deleteUser", delData);
	}

	public void updateUser(SqlSession mapper, userDTO dto) {
		mapper.update("updateUser", dto);
	}

	public void withdrawUser(SqlSession mapper, userDTO dto) {
		mapper.update("withdrawUser", dto);
	}

	public userDTO getLogin(SqlSession mapper, userDTO dto) {
		return (userDTO) mapper.selectOne("getLogin", dto);
	}
	
}
