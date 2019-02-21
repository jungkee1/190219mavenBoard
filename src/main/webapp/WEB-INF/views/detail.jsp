<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
window.onload = function () { // window.onload는 doucnemnt.getready랑 비슷한데 전자는 페이이지당 한개의 함수만 실행되고 후자는 여러번 사용가능하며 순서대로 모든 함수가 다 실행됨
	//콜백함수
/* 	$.get("commentList",	 
	function(data){
		data=$.parseJSON(data);
		var htmlStr ="";
		htmlStr += "<table>";
		for(var i=0;i<data.length;i++){
			htmlStr += "<tr>";
			htmlStr += "<td>" + data[i].msg+"</td>";
			htmlStr += "<td>"+ data[i].cName+"</td>";
			htmlStr += "</tr>";
		}
		htmlStr += "</table>";
		$("#result").html(htmlStr);
	}		
	) */
	$.ajax({	//콜백함수 ajax 버전
		url:"commentList",
		type:"get",
		success:function(data){
			data=$.parseJSON(data);
			var htmlStr ="";
			htmlStr += "<table>";
			for(var i=0;i<data.length;i++){
				htmlStr += "<tr>";
				htmlStr += "<td>" + data[i].msg+"</td>";
				htmlStr += "<td>"+ data[i].cName+"</td>";
				htmlStr += "</tr>";
			}
			htmlStr += "</table>";
			$("#result").html(htmlStr);
		}
	})
		
}


function comment(){
	$.ajax({
		url:"comment",
		type:"get",
		data:{"comment" : $("#comment").val(), "cName" : $("#cName").val()},
		success:function(data){
			data=$.parseJSON(data);
			var htmlStr ="";
			htmlStr += "<table>";
			for(var i=0;i<data.length;i++){
				htmlStr += "<tr>";
				htmlStr += "<td>" + data[i].msg+"</td>";
				htmlStr += "<td>"+ data[i].cName+"</td>";
				htmlStr += "</tr>";
			}
			htmlStr += "</table>";
			$("#result").html(htmlStr);
		},
		error:function(e){
			alert("오류 메시지:" +e);
		}
	})
}
</script>
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
</form>
<input type="submit" value="수정하기">
<input type="button" value="답글쓰기" onclick="location='replyForm?groups=${dto.groups}&levels=${dto.levels}&steps=${dto.steps}'"><br>
<input type="text" size="50" name="comment" id="comment"> <input type="text" id="cName"> <input type="button" value="코멘트남기기" onclick="comment()">
<div id="result">

</div>

</div>

</body>
</html>