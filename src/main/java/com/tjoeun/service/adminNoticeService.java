package com.tjoeun.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tjoeun.dao.adminNoticeDAO;
import com.tjoeun.resource.MySession;
import com.tjoeun.dto.adminNoticeDTO;

public class adminNoticeService {
	
	private static adminNoticeService instance = new adminNoticeService();
	private adminNoticeService() {	}
	public static adminNoticeService getInstance() {
		return instance;
	}
	
	public void insert(adminNoticeDTO dto) {
//		System.out.println("adminNoticeService의 insert()");
		SqlSession mapper = MySession.getSession();
//		System.out.println("연결 성공: " + mapper);
		adminNoticeDAO.getInstance().insert(mapper, dto);
		mapper.commit();
		mapper.close();
	}
		
		public List<adminNoticeDTO> noticeList(adminNoticeDTO dto) {
			SqlSession mapper = MySession.getSession();
			adminNoticeDAO dao = adminNoticeDAO.getInstance();
			List<adminNoticeDTO> noticeList = dao.noticeList(mapper,dto);
			
			return noticeList;
		}	   
		public void deleteNotice(String[] delData) {
//			System.out.println("삭제 서비스");
			SqlSession mapper = MySession.getSession();
			adminNoticeDAO dao = adminNoticeDAO.getInstance();
			dao.deleteNotice(mapper, delData);
			mapper.commit();
			mapper.close();
		}
		
		public int noticeCount(adminNoticeDTO dto) {
			SqlSession mapper = MySession.getSession();
			adminNoticeDAO dao = adminNoticeDAO.getInstance();
			
			int noticeCount = dao.noticeCount(mapper, dto);
			mapper.close();
			return noticeCount;
		}
		public adminNoticeDTO selectByIdx(int idx) {
//			System.out.println("adminNoticeService의 selectByIdx()");
			SqlSession mapper = MySession.getSession();
			adminNoticeDTO dto = adminNoticeDAO.getInstance().selectByIdx(mapper, idx);
			mapper.close();
			return dto;
		}

		public void update(adminNoticeDTO notice) {
		    SqlSession mapper = MySession.getSession();
		    adminNoticeDAO.getInstance().update(mapper, notice);
		    mapper.commit();
		    mapper.close();
		}
		

		
		
		
}
