package com.tjoeun.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.tjoeun.dto.memberDTO;


public class memberDAO {
	private static memberDAO instance;
	
	public static memberDAO getInstance () {
		if (instance == null) {
			instance = new memberDAO();
		}
		
		return instance;
	}
	
	public ArrayList<memberDTO> selectList(SqlSession mapper) {
		
		return (ArrayList<memberDTO>) mapper.selectList("memberList");
	}

	public memberDTO getMember(SqlSession mapper, memberDTO dto) {
		return (memberDTO) mapper.selectOne("getMember", dto);
	}

	public void insertMember(SqlSession mapper, memberDTO dto) {
		mapper.insert("insertMember", dto);
	}
	
}
