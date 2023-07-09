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
	
	public List<memberDTO> memberList(memberDTO dto) {
		SqlSession mapper = MySession.getSession();
		memberDAO dao = memberDAO.getInstance();
		
		List<memberDTO> memberList = dao.memberList(mapper, dto);
		
		mapper.close();
		return memberList;
	}
	
	public int memberCount(memberDTO dto) {
		SqlSession mapper = MySession.getSession();
		memberDAO dao = memberDAO.getInstance();
		
		int memberCount = dao.memberCount(mapper, dto);
		
		mapper.close();
		return memberCount;
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

	public void deleteMember(String[] delData) {
		SqlSession mapper = MySession.getSession();
		memberDAO dao = memberDAO.getInstance();
		
		dao.deleteMember(mapper, delData);
		
		mapper.commit();
		mapper.close();
	}

	public void updateMember(memberDTO dto) {
		SqlSession mapper = MySession.getSession();
		memberDAO dao = memberDAO.getInstance();
		
		dao.updateMember(mapper, dto);
		
		mapper.commit();
		mapper.close();
		
	}

	public void withdrawMember(memberDTO dto) {
		SqlSession mapper = MySession.getSession();
		memberDAO dao = memberDAO.getInstance();
		
		dao.withdrawMember(mapper, dto);
		
		mapper.commit();
		mapper.close();
	}

	public memberDTO getLogin(memberDTO dto) {
		SqlSession mapper = MySession.getSession();
		memberDAO dao = memberDAO.getInstance();
		
		
		memberDTO user = dao.getLogin(mapper, dto);
		
		mapper.close();
		return user;
	}

}
