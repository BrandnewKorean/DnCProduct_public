<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BoardView</title>
</head>
<body>
<h2>View</h2>
	
	<span>${bv.title}</span>
	<hr>	
	<textarea>${bv.content}</textarea>
	
	<c:if test="${logID==bv.id}">
		<button onclick="location.href='catboardupdatef?seq=${bv.seq}'">수정하기</button>
		<button onclick="location.href='catboarddelete?seq=${bv.seq}'">삭제하기</button>
	</c:if>
</body>
</html>