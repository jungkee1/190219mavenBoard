<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
<form action="update" method="post">
<input type="hidden" name="seq" value="${dto.seq }">
<input type="hidden" name="password" value="${dto.password }">
	<table border="1" width="500">
	<tr>
		<td>title</td>
		<td><input type="text" name="title" value="${dto.title }"></td>
	</tr>
	<tr>
		<td>writer</td>
		<td><input type="text" name="writer" value="${dto.writer }"></td>
	</tr>
	<tr>
		<td>content</td>
		<td><textarea name="content" rows="30" cols="50" >${dto.content }</textarea></td>
	</tr>
	<tr>
		<td>password</td>
		<td><input type="text" name="password1"></td>
	</tr>
</table>
<input type="submit" value="수정하기">
</form>


</div>

</body>
</html>