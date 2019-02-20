package com.myspring.model;

import org.springframework.stereotype.Component;

@Component("page") //repostitory�� ���� �̸�����ã�´� �̰�
public class PagingAction {
	public String paging(int count, int pageSize, int currentPage) {
		int pageCount = count/pageSize + (count%pageSize==0?0:1); //������ ��
		int pageBlock = 3;											//ǥ���� ������ ��
		int startPage = ((currentPage-1)/pageBlock)*pageBlock+1;	//ǥ���� ������ �� ù
		int endPage = startPage + pageBlock+1;						//ǥ���� ������ �� ������
		
		if(endPage>pageCount) endPage=pageCount;
		StringBuffer sb = new StringBuffer(); //String buffer�� String���� ������ ȿ�������� ��� �����ϰ�, ���� �߰��ص� �� �������ų� �������� (append)
		if(count>0) {
			if(startPage>pageBlock) {//����
				sb.append("<a href=list?pageNum=");
				sb.append((startPage-pageBlock));
				sb.append(">[����]</a>");
			}
			//��������
			for(int i = startPage; i<=endPage; i++) {
				if(i==currentPage) {
					sb.append("[" + i + "]");
				}else {
					sb.append("<a href=list?pageNum=");
					sb.append(i);
					sb.append(">[" + i + "]</a>");
				}
			}
			if(endPage<pageCount) {//����
				sb.append("a href=list?pageNum=");
				sb.append((startPage + pageBlock));
				sb.append(">[����]</a>");
			}
		}
		return sb.toString();
		
	}

}
