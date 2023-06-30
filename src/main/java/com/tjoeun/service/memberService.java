package com.tjoeun.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tjoeun.dao.memberDAO;
import com.tjoeun.dto.memberDTO;
import com.tjoeun.resource.MySession;


public class memberService {
	private static memberService instance;
	
	public static memberService getInstance() {
		if (instance == null) {
			instance = new memberService();
		}
		
		return instance;
	}
	
	public List<memberDTO> memberList(int currentPage) {
		SqlSession mapper = MySession.getSession();
		memberDAO dao = memberDAO.getInstance();
		
		List<memberDTO> memberList = dao.selectList(mapper);
		
		mapper.close();
		return memberList;
	}
	
	public memberDTO getMember(memberDTO dto) {
		SqlSession mapper = MySession.getSession();
		memberDAO dao = memberDAO.getInstance();
		
		
		memberDTO user = dao.getMember(mapper, dto);
		
		mapper.close();
		return user;
	}

	public void insertMember(memberDTO dto) {
		SqlSession mapper = MySession.getSession();
		memberDAO dao = memberDAO.getInstance();
		
		dao.insertMember(mapper, dto);
		
		mapper.commit();
		mapper.close();
	}
}
