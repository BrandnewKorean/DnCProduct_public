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
<div>
<c:choose>
	<c:when test="${startPage>perPageNO }">
		<a href="catboard?currentPage=1"><svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-chevron-left" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd" d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z"/>
</svg></a>&nbsp;
		<a href="catboard?currentPage=${startPage-1}">prev</a>&nbsp;&nbsp;
	</c:when>
	<c:otherwise>
		<font color="gray">First&nbsp;Prev&nbsp;&nbsp;</font>
	</c:otherwise>
</c:choose>

<c:forEach var="i" begin="${startPage }" end="${endPage }">
	<c:choose>
		<c:when test="${i==currentPage}">
			<font size="5" color="Orange">${i }</font>
		</c:when>
		<c:otherwise>
			<a href="catboard?currentPage=${i }">${i }</a>
		</c:otherwise>	
	</c:choose>
</c:forEach>

<c:choose>
	<c:when test="${endPage<totalPageNo }">
		<a href="catboard?currentPage=${endPage+1}">&nbsp;&nbsp;Next</a>
		<a href="catboard?currentPage=${totalPageNo}">&nbsp;Last</a>
	</c:when>
	<c:otherwise>
		<font color="gray">&nbsp;&nbsp;Next&nbsp;Last</font>
	</c:otherwise>
</c:choose>
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