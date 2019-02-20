package com.myspring.model;

import org.springframework.stereotype.Component;

@Component("page") //repostitory랑 같음 이름으로찾는다 이건
public class PagingAction {
	public String paging(int count, int pageSize, int currentPage) {
		int pageCount = count/pageSize + (count%pageSize==0?0:1); //페이지 수
		int pageBlock = 3;											//표시할 페이지 수
		int startPage = ((currentPage-1)/pageBlock)*pageBlock+1;	//표시할 페이지 중 첫
		int endPage = startPage + pageBlock+1;						//표시할 페이지 중 마지막
		
		if(endPage>pageCount) endPage=pageCount;
		StringBuffer sb = new StringBuffer(); //String buffer는 String보다 공간을 효율적으로 사용 가능하고, 값을 추가해도 더 느려지거나 하지않음 (append)
		if(count>0) {
			if(startPage>pageBlock) {//이전
				sb.append("<a href=list?pageNum=");
				sb.append((startPage-pageBlock));
				sb.append(">[이전]</a>");
			}
			//페이지수
			for(int i = startPage; i<=endPage; i++) {
				if(i==currentPage) {
					sb.append("[" + i + "]");
				}else {
					sb.append("<a href=list?pageNum=");
					sb.append(i);
					sb.append(">[" + i + "]</a>");
				}
			}
			if(endPage<pageCount) {//다음
				sb.append("a href=list?pageNum=");
				sb.append((startPage + pageBlock));
				sb.append(">[다음]</a>");
			}
		}
		return sb.toString();
		
	}

}
