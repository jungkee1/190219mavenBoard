<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
게시글 수:${count }
<div align="center">
<table border="1">
	<tr>
		<td>글번호</td>
		<td>제목</td>
		<td>글쓴이</td>
		<td>게시일</td>
		<td>조회수</td>
		<td>삭제</td>
	</tr>
<c:forEach items="${dto }" var="dto" varStatus="status"> <!--페이징 이후의 게시글 번호를 메기기위한 status  -->
	<tr>
		<td>${number-status.index }</td>
		<c:if test="${dto.levels > 0}"> <!-- 들여쓰기 값 levels 0이상이면 들여쓰기 시행  -->
			<td>
				<img src="resources/level.gif" width="${dto.levels*8 }" height="16"> <!-- 답글에 level값에 따라 들여쓰기용 공백 이미지 --> <!--이거는 src main webapp resources에 그림 넣어놈  -->
				<img src="resources/re.gif"><!--답글이면 이미지 넣고  -->
				<a href="detail?seq=${dto.seq }">${dto.title }</a>
			</td>
		</c:if>
		<c:if test="${dto.levels <= 0}"> <!--level 값이 없는 일반 부모글들은 그냥 출력  -->
			<td>
				<a href="detail?seq=${dto.seq }">${dto.title }</a>
			</td>
		</c:if>
		
		<td>${dto.writer }</td>
		<td>${dto.regdate }</td>
		<td>${dto.hitcount }</td>
		<td><input type="button" value="삭제" onclick="location.href='delete?seq=${dto.seq}'"></td>
	</tr>
</c:forEach>
</table>
${pageHtml }
<form action="search">
<table>
	<tr>
		<td>
			<select name="field">
				<option value="writer">작성자</option>
				<option value="content">내용</option>
			</select>
		</td>
		<td>
			<input type="text" name="word">
			<input type="submit" value="검색">
		</td>
		
	</tr>
</table>
</form>
</div>

</body>
</html>