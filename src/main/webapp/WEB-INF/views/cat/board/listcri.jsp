<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" >
<script type="resources/script/jquery-3.2.1.min.js"></script>

<script>
	$(function() {
		$('#serachBtn').on("click",function(){
			self.loaction="listcri"
			+"${pageMaker.makeQuery(1)}"
			+"&searchType="
			+$("searchType").val()
			+"&keyword="
			+$("#keyword").val();
			
		});
		
	}); // ready

</script>
</head>
<body>
<h2>** BoradList Page Criteria ** </h2>
<div id="searchBar">
	<select name="searchType" id="searchType">
		<option value="null" <c:out value="${pareMaker.search.searchType==null ?'selected':'' }"/>> 
		---</option>
		<option value="title" <c:out value="${pareMaker.search.searchType eq 'title' ?'selected':'' }"/>> 
		Title</option>
		<option value="content" <c:out value="${pareMaker.search.searchType eq 'content' ?'selected':'' }"/>> 
		Content</option>
		<option value="id" <c:out value="${pareMaker.search.searchType eq 'id' ? 'selected':'' }"/>> 
		Writer</option>
		<option value="titlecontent" <c:out value="${pareMaker.search.searchType eq 'titlecontent' ? 'selected':'' }"/>> 
		Title or Content</option>
	
	</select>
	<input type="text" name="keyword" id="keyword" value="${pageMaker.cri.keyword }">
	<button id="searchBtn">Search</button>
</div>

<table width=800 border=0>
<tr align="center" height="30" bgcolor="PowderBlue">
	<td>번호</td><td>작성자</td><td>제목</td>
	<td>작성일</td><td>조회수</td><td>댓글</td>
</tr>
<c:forEach var="bb" items="${list}">
	<tr align="center" height="30">
		<td>${bb.seq}</td>
		<td align="left">
		<c:if test="${logID != null }">
		<a href="catboardview?seq=${bb.seq}&id=${bb.id}"${bb.title}></a>
		</c:if>
		
		<c:if test="${logID == null }">
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
			<a href="listcri${pageMaker.makeSerach(1)}">First&nbsp;</a>
			<a href="listcri${pageMaker.makeSerach(pageMaker.sPageNo-1)}">&nbsp;</a>
		</c:if>
	
	<c:forEach begin="${pageMaker.sPageNo}"
			end="${pageMaker.sPageNo}" var="i">
		<c:choose>
			<c:when test="${pageMaker.cri.currPage==i}">
			<font size="5" color="Orange">${i}</font>&nbsp;
			</c:when>
			<c:otherwise>
				<a href="listcri${pageMaker.makeSerach(i)}">${i}</a>&nbsp;
			</c:otherwise>
		</c:choose>
	</c:forEach>
	
	<c:if test="${pageMaker.next && pageMaker.ePageNo > 0 }">
	<a href="listcri${pageMaker.makeSerach(pageMaker.ePageNo+1)">&nbsp;&laquo;</a>
	<a href="listcri${pageMaker.makeSerach(pageMaker.lastPageNo)">&nbsp;Last</a>
	</c:if>
	
	</div>
<hr>


</body>
</html>