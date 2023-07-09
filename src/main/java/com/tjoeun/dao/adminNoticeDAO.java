package com.tjoeun.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.tjoeun.dto.adminNoticeDTO;

public class adminNoticeDAO {

	private static adminNoticeDAO instance = new adminNoticeDAO();
	private adminNoticeDAO() { }
	public static adminNoticeDAO getInstance() {
		if (instance == null) {
			instance = new adminNoticeDAO();
		}
		
		return instance;
	}
	
	public void insert(SqlSession mapper, adminNoticeDTO dto) {
//		System.out.println("adminNoticeDAO의 insert()");
		mapper.insert("noticeInsert", dto);
	}
	
	public List<adminNoticeDTO> noticeList(SqlSession mapper, adminNoticeDTO dto) {
		return (ArrayList<adminNoticeDTO>) mapper.selectList("noticeList", dto);
	}
	
	public void deleteNotice(SqlSession mapper, String[] delData) {
		mapper.delete("deleteNotice", delData);
	}
	
	
	public int noticeCount(SqlSession mapper, adminNoticeDTO dto) {
		return (Integer) mapper.selectOne("noticeCount", dto);
	}
	
	
	public adminNoticeDTO selectByIdx(SqlSession mapper, int idx) {
//		System.out.println("adminNoticeDAO의 selectByIdx()");
		return (adminNoticeDTO) mapper.selectOne("getNotice", idx);
	}
	public void update(SqlSession mapper, adminNoticeDTO notice) {
//	    System.out.println("adminNoticeDAO의 update()");
	    mapper.update("noticeUpdate", notice);
	}

	
	
	
	
}
