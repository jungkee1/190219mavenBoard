package com.myspring.model;

import java.util.HashMap;
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
	public List<BoardDTO> list(HashMap<String, Integer> hm){
		List<BoardDTO> dto = bDao.list(boardSql+".list", hm); //sqlid 만 인자 값으로 넘김
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
	
	//count
	public int count() {
		int count = bDao.count(boardSql+".count");
		return count;
	}
	
	//search
	public List<BoardDTO> search(String field, String word){
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("field", field);
		hm.put("word", word);
		return bDao.search(boardSql+".search", hm);
	}
	
	//reply
	public void reply(BoardDTO dto) {
		bDao.reply(boardSql+".reply",dto);
	}
	
	//comment
	public void comment(String comment, String cName) {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("msg", comment);
		hm.put("cName", cName);
		bDao.comment(boardSql+".comment",hm);
	}
	
	//commentList
	public List<CommentDTO> commentList() {
		List<CommentDTO> dto = bDao.commentList("commentList");
		return dto;
	}

}
