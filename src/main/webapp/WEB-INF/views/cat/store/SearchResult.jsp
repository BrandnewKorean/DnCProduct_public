<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[${search.keyword}] 검색결과</title>
<link rel="stylesheet" type="text/css" href="resources/css/cat/store/SearchResult.css?ver=<%= System.currentTimeMillis()%>">
</head>
<body>
	<jsp:include page="StoreHeader.jsp"></jsp:include>
	<div class=container>
		<a class=group1_title>[식료품] 검색결과</a><br>
		<div class=result>
			<c:choose>
				<c:when test="${resultMap.get(0).size() > 0}">
				</c:when>
				<c:otherwise>결과가 없습니다</c:otherwise>
			</c:choose>
			
		</div>
		<a class=group1_title>[배변/위생용품] 검색결과</a><br>
		<div class=result>
			<c:choose>
				<c:when test="${resultMap.get(1).size() > 0}">
				</c:when>
				<c:otherwise>결과가 없습니다</c:otherwise>
			</c:choose>
		</div>
		<a class=group1_title>[미용용품] 검색결과</a><br>
		<div class=result>
			<c:choose>
				<c:when test="${resultMap.get(2).size() > 0}">
				</c:when>
				<c:otherwise>결과가 없습니다</c:otherwise>
			</c:choose>
		</div>
		<a class=group1_title>[생활용품] 검색결과</a><br>
		<div class=result>
			<c:choose>
				<c:when test="${resultMap.get(3).size() > 0}">
				</c:when>
				<c:otherwise>결과가 없습니다</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>