<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
	#viewtoggle{
		position: relative;
		display: inline-block;
		width: 50px;
		background-color: gray;
		border: none;
		vertical-align: middle;
		border: 1px solid;
		padding: 1px;
	}
	#viewtoggle > span{
		position: absolute;
		background-color: white;
		width: 20px;
		height: 20px;
		left: 3px;
		transition: .4s;
	}
	#viewtoggle > input:checked + span{
		transform: translateX(26px);
	}
	#viewtoggle > input{
		opacity: 0;
	}
</style>
<link rel="stylesheet" type="text/css" href="resources/css/cat/board/Catboard.css?ver=<%=System.currentTimeMillis()%>">
<script src="resources/script/jquery-3.2.1.min.js"></script>
<meta charset="UTF-8">
<title>고양이 게시판</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
$(function(){
	var view = document.getElementById('view');
	
	$('#view').click(function(){
		if(view.checked){
			location.href = 'catboard?code=image';
		}else{
			location.href = 'catboard?code=list';
		}
	});
});
</script>


<script>
$(function(){
	$('#searchButton').on("click",function(){
	location.href="listcri"
	+"${pageMaker.makeQuery(1)}"
	+"&searchType="
	+$("#searchType").val()
	+"&keyword="
	+$("#keyword").val();
	});
}); // ready 
</script>
</head>
<body>
	<img id="boardimg" onclick="location.href='catmain'" src="resources/image/logoe.png" width=15%>
	<div id=catboard_menu>
		<label id=viewtoggle>
			<c:if test="${!view}">
				<input id=view type="checkbox">
			</c:if>
			<c:if test="${view}">
				<input id=view type="checkbox" checked="checked">
			</c:if>
			<span></span>
		</label>
		<button onclick="location.href='catboardinsertf'">글쓰기</button>
	</div>
	
	<div id="searchBar">
	<select name="searchType" id="searchType">
		<option value="null" ><c:out value="${pageMaker.search.searchType==null?'':''}"/>---</option>
		<option value="title"> <c:out value="${pageMaker.search.searchType eq 'title'?'selected':'' }"/>Title</option>
		<option value="content"> <c:out value="${pageMaker.search.searchType eq 'content'?'selected':'' }"/>Content</option>
		<option value="id"> <c:out value="${pageMaker.search.searchType eq 'id'?'selected':'' }"/>ID</option>
		<option value="titlecontent"> <c:out value="${pageMaker.search.searchType eq 'titlecontent'?'selected':'' }"/>TitleConent</option>
	</select>
	<input type="text" name="keyword" id="keyword" value="${pageMaker.search.keyword }">
	<button id="searchButton">Search</button>
</div>
	
	<c:if test="${!view}">
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
			<c:forEach var="bb" items="${list}">
				<div class="row">
					<span class="cell col1">${bb.seq}</span>
					<span class="cell col2">${bb.id}</span>
					<span class="cell col3"><a href="catboardview?seq=${bb.seq}">${bb.title}</a></span>
					<span class="cell col4">${bb.regdate}</span>
					<span class="cell col5">${bb.cnt}</span>
					<span class="cell col6">${bb.comments}</span>
				</div>
			</c:forEach>	
		</c:if>
	</div>
	</c:if>
	<c:if test="${list != '[]'}">
		<div>
			<c:choose>
				<c:when test="${startPage>perPageNO }">
					<a href="catboard?code=image&&currentPage=1">First</a>&nbsp;
					<a href="catboard?code=image&&currentPage=${startPage-1}">prev</a>&nbsp;&nbsp;
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
						<a href="catboard?code=image&&currentPage=${i }">${i }</a>
					</c:otherwise>	
				</c:choose>
			</c:forEach>
			
			</c:if>
			<c:choose>
				<c:when test="${endPage<totalPageNo }">
					<a href="catboard?code=image&&currentPage=${endPage+1}">&nbsp;&nbsp;Next</a>
					<a href="catboard?code=image&&currentPage=${totalPageNo}">&nbsp;Last</a>
				</c:when>
				<c:otherwise>
					<font color="gray">&nbsp;&nbsp;Next&nbsp;Last</font>
				</c:otherwise>
			</c:choose>
		</div>
	
	<c:if test="${list == '[]'}">
		<div>
			<span>등록된 글이 없습니다</span>
		</div>
	</c:if>
</body>
</html>