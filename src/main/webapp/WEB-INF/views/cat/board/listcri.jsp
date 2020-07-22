<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>

<script src="resources/script/jquery-3.2.1.min.js"></script>

<script>
	$(function () {
		$('#searchButton').on("click",function(){
			self.location="listcri"
			+"${pageMaker.makeQuery(1)}"
			+$("#searchType").val()
			+"&keyword="
			+$("#keyword").val();
		});
	});// functino

</script>

</head>
<body>

	<div id="searchBar">
		<select name="searchBar" id="searchType">
			<option value="null"> <c:out value="${pageMaker.search.searchType==null ? 'selected':''}" />>-----</option>
			<option value="title"> <c:out value="${pageMaker.search.searchType eq 'title' ? 'selected':'' }" />>제목</option>
			<option value="content"> <c:out value="${pageMaker.search.searchType eq 'content' ? 'selected':''}" />>내용</option>
			<option value="id"> <c:out value="${pageMaker.search.searchType eq 'id' ? 'selected':''}" />>작성자</option>
			<option value="titlecontent"> <c:out value="${pageMaker.search.searchType eq 'titlecontent' ? 'selected':''}" />>제목+내용</option>
		</select>
		
		<input type="text" name="keyword" id="keyword" value="${pageMaker.search.keyword}">
		<button id="searchButton">검색</button>
	</div>
	
	<table width=800 border="0">
		<tr align="center" height=30 bgcolor="lavender">
			<td>번호</td>		<td>작성자</td>		<td>제목</td>		<td>작성일</td>	<td>조회</td>		<td>댓글</td>
		</tr>
	
	<c:forEach var="bb" items="${dnc}">
		<tr align="center" height=30>
			<td>${bb.seq}</td>
			<td align="left">
				<c:if test="${logID!=null}">
					<a href="catboardview?seq=${bb.seq}&id=${bb.id}">${bb.title}</a>
				</c:if>
				
				<c:if test="${logID==null}">
					${bb.title}
				</c:if>
			</td>
			
			<td>${bb.id}</td>
			<td>${bb.regdate}</td>
			<td>${bb.cnt}</td>
		</tr>
	</c:forEach>
	</table>
	
<hr>
	<div align="center">
		<c:if test="${pageMaker.prev}">
			<a href="listcri${pageMaker.makeSearch(1)}">《</a>
			<a href="listcri${pageMaker.makeSearch(pageMaker.startPageNo-1}">&nbsp;</a>
		</c:if>
	
	<c:forEach begin="${pageMaker.startPageNo}" end="${pageMaker.endPageNo}" var="i">
		<c:choose>
			<c:when test="${pageMaker.search.currentPage==i}">
				<font size="5" color="orange">${i}</font>&nbsp;
			</c:when>
			
			<c:otherwise>
				<a href="listcri${pageMaker.makerSearch(i)}">${i}</a>&nbsp;
			</c:otherwise>
		</c:choose>
	</c:forEach>
	
	<c:if test="${pageMaker.next && pageMaker.endPageNo > 0}">
		<a href="listcri${pageMaker.makeSearch(pageMaker.endPageNo+1)}">&nbsp;&nbsp;</a>
		<a href="listcri${pageMaker.makeSearch(pageMaker.lastPageNo)}">》&nbsp;&nbsp;</a>
	</c:if>
	
	
	</div>	
	

</body>
</html>