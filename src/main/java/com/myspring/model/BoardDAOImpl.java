package com.myspring.model;

import java.util.HashMap;
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
	public List<BoardDTO> list(String sqlid, HashMap<String, Integer> hm) {
		// TODO Auto-generated method stub
		List<BoardDTO> dto = sqlMap.selectList(sqlid, hm);
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
	//게시글 개수
	public int count(String sqlid) {
		int count = sqlMap.selectOne(sqlid);
		return count;
	}
	
	//검색하기
	public List<BoardDTO> search(String sqlid, HashMap<String, String>hm){
		return sqlMap.selectList(sqlid, hm);
	}
	
	//답글달기
	public void reply(String sqlid, BoardDTO dto) {
		sqlMap.update("reupdate",dto); //답글 달리기전에 같은 부모의 답글들 모두 stpe+1 시키는 곳
		sqlMap.insert(sqlid,dto); //답글 달기
	}
	
	//댓글달기
	public void comment(String sqlid, HashMap<String, String>hm) {
		System.out.println("dao" + hm.get("msg"));
		System.out.println("dao" + hm.get("cName"));
		sqlMap.insert(sqlid, hm);
	}
	
	//댓글 뿌리기
	public List<CommentDTO> commentList(String sqlid) {
		List<CommentDTO> dto = sqlMap.selectList(sqlid);
		return dto;
	}

}
