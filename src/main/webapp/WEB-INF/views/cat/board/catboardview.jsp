<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BoardView</title>
</head>
<script type="text/javascript" src="resources/script/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="resources/script/writecomment.js?ver=<%= System.currentTimeMillis()%>"></script>
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
<c:choose>
	<c:when test="${view}">
		<button onclick="location.href = 'catboard?code=image'">이전으로</button>
	</c:when>
	<c:otherwise>
		<button onclick="location.href = 'catboard?code=list'">이전으로</button>
	</c:otherwise>
</c:choose>

<h2>View</h2>
	<input type="hidden" id="seq" value="${bv.seq }">
	<span>${bv.title}</span>
	<hr>
<pre>
${bv.content}
</pre>

<hr>
<h2 style="color: blue">↓↓하단부터 댓글 입니다↓↓</h2>

<span>
	<c:if test="${comment == '[]'}">
		<div>
			<div>등록된 댓글이 없습니다.</div>
		</div>
	</c:if>
	
	<c:if test="${comment != '[]'}">
		<c:forEach var="c" items="${comment}" varStatus="vs">
		<hr>
			<div>
				<div class=td2 style="font-size: small;">${c.id}</div>
				<div class=td1 style="font-weight: bold;">${c.content}</div>
			</div>
			
		</c:forEach>
	</c:if>
</span>

<span>
	<div>
		<textarea id=content placeholder="내용"></textarea>
		<button class=submit onclick="writeComment()">작성</button>
	</div>
</span>


	<c:if test="${logID==bv.id}">
		<button onclick="location.href='catboardupdatef?seq=${bv.seq}'">수정하기</button>
		<button onclick="catboarddelete()">삭제하기</button>
	</c:if>
</body>
</html>