package com.myspring.model;

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
	public List<BoardDTO> list(String sqlid) {
		// TODO Auto-generated method stub
		List<BoardDTO> dto = sqlMap.selectList(sqlid);
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

}
