<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="resources/script/jquery-3.2.1.min.js"></script>
<script>
$(function(){
	$('#searchBtn').on("click",function(){
	self.location="listcri"
	+"${pageMaker.makeQuery(1)}"
	+"&serchType="
	+$("#searchType").val()
	+"&keyword="
	+$("#keyword").val();
	});
}); // ready 
</script>
</head>
<body>
<div id="searchBar">
	<select name="searchType" id="searchType">
		<option value="null" <c:out value="${pageMaker.search.searchType==null?'selected':" }"/>>
		---</option>
		<option value="title" <c:out value="${pageMaker.search.searchType eq 'title'?'selected':" }"/>>
		Title</option>
		<option value="content" <c:out value="${pageMaker.search.searchType eq 'content'?'selected':" }"/>>
		Content</option>
		<option value="id" <c:out value="${pageMaker.search.searchType eq 'id'?'selected':" }"/>>
		ID</option>
		<option value="titlecontent" <c:out value="${pageMaker.search.searchType eq 'titlecontent'?'selected':" }"/>>
		TitleConent</option>
	</select>
	<input type="text" name="keyword" id="keyword" value="${pageMaker.search.keyword }">
	<button id="searchBtn">Search</button>
</div>
<table width=800 border=0>
<tr align="center" height=30 bgcolor="PowderBlue">
	<td>번호</td><td>작성자</td><td>제목</td>
	<td>작성일</td><td>조회수</td><td>댓글</td>
</tr>
</table>
<c:forEach var="bb" items="${list }">
	<tr align="center" height=30> 
		<td>${bb.seq}</td>
		<td align="left">
		<c:if test="${logID!=null }">
			<a href="catboardview?seq=${bb.seq}&id=${bb.id}">${bb.title}</a>
		</c:if>
		<c:if test="${logID==null }">
			${bb.title}
		</c:if>	
		</td>
		<td>${bb.id }</td>
		<td>${bb.regdate }</td>
		<td>${bb.cnt }</td>
		
	</tr>
</c:forEach>

<hr>
<div align="center">
<c:if test="${pageMaker.prev }">
	<a href="listcri${pageMaker.makeSearch(1)}">First&nbsp;</a>
	<a href="listcri${pageMaker.makeSearch(pageMaker.startPageNo-1 }">&laquo;&nbsp;</a>
</c:if>

<c:forEach begin="${pageMaker.startPageNo}" end="${pageMaker.endPageNo }" var="i">
	<c:choose>
		<c:when test="${pageMaker.search.currentPage==i }">
			<font size="5" color="Orange">${i}</font>
		</c:when>
		<c:otherwise>
			<a href="listcri${pageMaker.makeSearch(i) }">${i}</a>&nbsp;
		</c:otherwise>
	</c:choose>
</c:forEach>

	<c:if test="${pageMaker.next&&pageMaker.endPageNo>0 }">
		<a href="listcri${pageMaker.makeSearch(pageMaker.endPageNo+1) }">&nbsp;&raquo;</a>
		<a href="listcri${pageMaker.makeSearch(pageMaker.lastPageNo)} ">&nbsp;Last</a>
	</c:if>
</div>
<hr>

</body>
</html>