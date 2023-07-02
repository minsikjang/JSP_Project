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
	
	public ArrayList<memberDTO> memberList(SqlSession mapper, memberDTO dto) {
		
		return (ArrayList<memberDTO>) mapper.selectList("memberList", dto);
	}
	
	public int memberCount(SqlSession mapper, memberDTO dto) {
		// TODO Auto-generated method stub
		return (Integer) mapper.selectOne("memberCount", dto);
	}

	public memberDTO getMember(SqlSession mapper, memberDTO dto) {
		return (memberDTO) mapper.selectOne("getMember", dto);
	}

	public void insertMember(SqlSession mapper, memberDTO dto) {
		mapper.insert("insertMember", dto);
	}

	public void deleteMember(SqlSession mapper, String[] delData) {
		mapper.delete("deleteMember", delData);
	}

}
