package com.myspring.model;

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
	public List<BoardDTO> list(){
		List<BoardDTO> dto = bDao.list(boardSql+".list"); //sqlid �� ���� ������ �ѱ�
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
