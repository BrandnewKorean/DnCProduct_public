<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고양이 게시판</title>

<link rel="stylesheet" type="text/css" href="resources/css/cat/board/Catboard.css?ver=<%=System.currentTimeMillis()%>">
<script type="text/javascript" src="resources/script/jquery-3.2.1.min.js"></script>
<script type="text/javascript">

// 	function catboardview(seq){
// 		location.href = 'catboardview?seq='+seq;
// 	}
	
</script>
</head>
<body>
	<img id="boardimg" onclick="location.href='catmain'" src="resources/image/logoe.png" width=15%>
	<div id=catboard_menu>
		<button onclick="location.href='catboardinsertf'">글쓰기</button>
	</div>
	<div id="table">
		<div class="row" id="rowtitle">
			<span class="cell col1">번호</span>
			<span class="cell col2">작성자</span>
			<span class="cell col3">제목</span>
			<span class="cell col4">작성일</span>
			<span class="cell col5">조회</span>
			<span class="cell col6">댓글</span>
		</div>
		<c:if test="${list != '[]'}">
			<c:forEach var="bb" items="${dnc}">
				<div class="row">
					<%-- <span class="cell col1" onclick="catboardview(${bb.seq})">${bb.seq}</span> --%>
					<span class="cell col1"><a href='catboardview?seq=${bb.seq}'>${bb.seq}</a></span>
					<span class="cell col2">${bb.id}</span>
					<%-- <span class="cell col3" onclick="catboardview(${bb.seq})">${bb.title}</span> --%>
					<span class="cell col3"><a href='catboardview?seq=${bb.seq}'>${bb.title}</a></span>
					<span class="cell col4">${bb.regdate}</span>
					<span class="cell col5">${bb.cnt}</span>
					<span class="cell col6">${bb.comments}</span>
				</div>
			</c:forEach>	
		</c:if>
	</div>
	<c:if test="${list == '[]'}">
		<div id=testd>
			<span>등록된 글이 없습니다</span>
		</div>
	</c:if>
	<div id=test>
	</div>
</body>
</html>