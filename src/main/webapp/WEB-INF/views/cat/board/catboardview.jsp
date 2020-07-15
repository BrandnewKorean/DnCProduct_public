<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>

<script src="resources/script/jquery-3.2.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/cat/board/catboardview.css?ver=<%=System.currentTimeMillis()%>">
<script src="resources/script/catboard.js"></script>

</head>
<body>

	<div>
		<span>작성자</span>
		<span>제목</span>
		<span>내용</span>
		<span>조회수</span>
		<span>댓글</span>
	</div>
	
	<c:forEach var="view" items="${dnc}">
		<div >
			<span>${view.id}</span>
			<span>${view.title}</span>
			<span>${view.content}</span>
			<span>${view.cnt}</span>
			<span>${view.comments}</span>
		</div>
	</c:forEach>

</body>
</html>