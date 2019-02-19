package com.myspring.model;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("bService")
public class BoardService {
	@Resource(name="bDao") //Autowired랑 같다. Autowired 해놓으면 그냥 객체 이름으로 바로 찾고, Resource로해놓으면 지정한 서로 지정한 name으로 찾아간다
	private BoardDAOImpl bDao;
	String boardSql = "com.myspring.model.DAOXML"; //DBXML 을 찾아가는 용도다. 
	
	//insert
	public void insert(BoardDTO dto) {
		bDao.insert(boardSql+".insert",dto); //sqlid 넘어가는 값을 "insert"로 설정
	}
	
	//list
	public List<BoardDTO> list(){
		List<BoardDTO> dto = bDao.list(boardSql+".list"); //sqlid 만 인자 값으로 넘김
		return dto;
	}

	//detail
	public BoardDTO detail(int seq) {
		BoardDTO dto = bDao.detail(boardSql+".detail", seq);
		return dto;
	}
	
	//update
	public void update(BoardDTO dto) {
		bDao.update(boardSql+".update", dto);
	}
	
	//delete
	public void delete(int seq) {
		bDao.delete(boardSql+".delete",seq);
		
	}

}
