package com.myspring.model;

import java.util.HashMap;
import java.util.List;

public interface BoardDAO {
	
	//추가
	void insert(String sqlid, BoardDTO dto);
	
	//전체보기
	List<BoardDTO> list(String sqlid, HashMap<String, Integer>hm);
	
	//수정
	void update(String sqlid, BoardDTO dto);
	
	//삭제
	void delete(String sqlid, int seq);
	
	//상세보기
	BoardDTO detail(String sqlid, int seq);
	
	//게시글 수
	
}
