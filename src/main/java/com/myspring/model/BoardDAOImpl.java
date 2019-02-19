package com.myspring.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("bDao")
public class BoardDAOImpl implements BoardDAO{
	@Autowired
	private SqlSession sqlMap;

	//글 쓰기
	@Override
	public void insert(String sqlid, BoardDTO dto) {
		// TODO Auto-generated method stub
		sqlMap.insert(sqlid,dto); //sqlid에 "insert"가 담겨있음 service에서 이미 정해줌
		
	}
	
	//게시글 전체보기
	@Override
	public List<BoardDTO> list(String sqlid) {
		// TODO Auto-generated method stub
		List<BoardDTO> dto = sqlMap.selectList(sqlid);
		return dto;
	}
	
	//상세보기
	@Override
	public BoardDTO detail(String sqlid, int seq) {
		// TODO Auto-generated method stub
		BoardDTO dto = sqlMap.selectOne(sqlid,seq);
		return dto;
		
	}
	
	//수정하기
	@Override
	public void update(String sqlid, BoardDTO dto) {
		// TODO Auto-generated method stub
		sqlMap.update(sqlid,dto);
	}
	
	//삭제하기
	@Override
	public void delete(String sqlid, int seq) {
		// TODO Auto-generated method stub
		sqlMap.delete(sqlid, seq);
		
		
	}

}
