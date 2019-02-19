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
<form action="writeForm" method="post">
<table border="1" width="500">
	<tr>
		<td>title</td>
		<td><input type="text" name="title"></td>
	</tr>
	<tr>
		<td>writer</td>
		<td><input type="text" name="writer"></td>
	</tr>
	<tr>
		<td>content</td>
		<td><textarea name="content" rows="30" cols="50"></textarea></td>
	</tr>
	<tr>
		<td>password</td>
		<td><input type="text" name="password"></td>
	</tr>
</table>
<input type="submit" value="글작성">
</form>


</div>

</body>
</html>