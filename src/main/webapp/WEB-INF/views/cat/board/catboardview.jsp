<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BoardView</title>
<style>
body{
	margin: 0;
}
.header{
	position: -webkit-sticky;
	position: sticky;
	top: 0;
	background-color: Lavender;
	text-align: center;
}
</style>
</head>
<script type="text/javascript" src="resources/script/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
function catboarddelete(){
	$.ajax({
		url:'catboarddelete',
		data:{
			seq:$('#seq').val()
		},
		success:function(data){
			if(data.bcode==0){
				alert('삭제 되었습니다.');
				location.href="catboard";
			}else if(data.bcode==1){
				alert('삭제 실패했습니다');
			}else{
				alert('로그인 후 이용해주세요');
				location.href="catmain";
			}
		}
	});
}
</script>
<body>
<div class=header>
	<img id="boardimg" onclick="location.href='catmain'" src="resources/image/logoe.png" width=15%>
</div>
<c:choose>
	<c:when test="${view}">
		<button onclick="location.href = 'catboard?code=image'">이전으로</button>
	</c:when>
	<c:otherwise>
		<button onclick="location.href = 'catboard?code=list'">이전으로</button>
	</c:otherwise>
</c:choose>
<c:if test="${logID==bv.id}">
	<button onclick="location.href='catboardupdatef?seq=${bv.seq}'">수정하기</button>
	<button onclick="catboarddelete()">삭제하기</button>
</c:if>
<input type="hidden" id="seq" value="${bv.seq }">
<h3>${bv.title}</h3>
<hr>
<pre>
${bv.content}
</pre>
</body>
</html>