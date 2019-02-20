package com.myspring.model;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("bService")
public class BoardService {
	@Resource(name="bDao") //Autowired�� ����. Autowired �س����� �׳� ��ü �̸����� �ٷ� ã��, Resource���س����� ������ ���� ������ name���� ã�ư���
	private BoardDAOImpl bDao;
	String boardSql = "com.myspring.model.DAOXML"; //DBXML �� ã�ư��� �뵵��. 
	
	//insert
	public void insert(BoardDTO dto) {
		bDao.insert(boardSql+".insert",dto); //sqlid �Ѿ�� ���� "insert"�� ����
	}
	
	//list
	public List<BoardDTO> list(HashMap<String, Integer> hm){
		List<BoardDTO> dto = bDao.list(boardSql+".list", hm); //sqlid �� ���� ������ �ѱ�
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
