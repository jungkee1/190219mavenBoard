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
<c:forEach items="${dto }" var="dto" >
	<tr>
		<td>${dto.seq }</td>
		<td><a href="detail?seq=${dto.seq }">${dto.title }</a></td>
		<td>${dto.writer }</td>
		<td>${dto.regdate }</td>
		<td>${dto.hitcount }</td>
		<td><input type="button" value="삭제" onclick="location.href='delete?seq=${dto.seq}'"></td>
	</tr>
</c:forEach>
</table>
</div>

</body>
</html>