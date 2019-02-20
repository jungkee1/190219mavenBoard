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

	//�� ����
	@Override
	public void insert(String sqlid, BoardDTO dto) {
		// TODO Auto-generated method stub
		sqlMap.insert(sqlid,dto); //sqlid�� "insert"�� ������� service���� �̹� ������
		
	}
	
	//�Խñ� ��ü����
	@Override
	public List<BoardDTO> list(String sqlid, HashMap<String, Integer> hm) {
		// TODO Auto-generated method stub
		List<BoardDTO> dto = sqlMap.selectList(sqlid, hm);
		return dto;
	}
	
	//�󼼺���
	@Override
	public BoardDTO detail(String sqlid, int seq) {
		// TODO Auto-generated method stub
		BoardDTO dto = sqlMap.selectOne(sqlid,seq);
		return dto;
		
	}
	
	//�����ϱ�
	@Override
	public void update(String sqlid, BoardDTO dto) {
		// TODO Auto-generated method stub
		sqlMap.update(sqlid,dto);
	}
	
	//�����ϱ�
	@Override
	public void delete(String sqlid, int seq) {
		// TODO Auto-generated method stub
		sqlMap.delete(sqlid, seq);
	}
	//�Խñ� ����
	public int count(String sqlid) {
		int count = sqlMap.selectOne(sqlid);
		return count;
	}
	
	//�˻��ϱ�
	public List<BoardDTO> search(String sqlid, HashMap<String, String>hm){
		return sqlMap.selectList(sqlid, hm);
	}
	
	//��۴ޱ�
	public void reply(String sqlid, BoardDTO dto) {
		sqlMap.update("reupdate",dto); //��� �޸������� ���� �θ��� ��۵� ��� stpe+1 ��Ű�� ��
		sqlMap.insert(sqlid,dto); //��� �ޱ�
	}
	
	//��۴ޱ�
	public void comment(String sqlid, HashMap<String, String>hm) {
		System.out.println("dao" + hm.get("msg"));
		System.out.println("dao" + hm.get("cName"));
		sqlMap.insert(sqlid, hm);
	}
	
	//��� �Ѹ���
	public List<CommentDTO> commentList(String sqlid) {
		List<CommentDTO> dto = sqlMap.selectList(sqlid);
		return dto;
	}

}
