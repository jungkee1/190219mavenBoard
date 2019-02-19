package com.myspring.model;

import java.util.List;

public interface BoardDAO {
	
	//�߰�
	void insert(String sqlid, BoardDTO dto);
	
	//��ü����
	List<BoardDTO> list(String sqlid);
	
	//����
	void update(String sqlid, BoardDTO dto);
	
	//����
	void delete(String sqlid, int seq);
	
	//�󼼺���
	BoardDTO detail(String sqlid, int seq);
	
	//�н�����üũ
	
	//�Խñ� ��

}
