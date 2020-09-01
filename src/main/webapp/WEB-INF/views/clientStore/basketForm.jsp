<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
</head>
<body>
<h1>장바구니</h1>
<h1>CatStore</h1>
	${list}<br>
	${storeMap}<br>
	${imageMap}<br>
	${productMap}<br>
	<div>
		<span>이미지</span>
		<span>이름</span>
		<span>가격</span>
		<span>구매하기</span>
	</div>
	<c:forEach var="list" items="${list}">
		<input type="checkbox">
		<img src="/resources/productimage/${imageMap.get(list.seq).get(0).filename}">
		${productMap.get(list.seq).name}
		${storeMap.get(list.seq).price}
	</c:forEach>
	<br>
	<button class=buy>구매하기</button>
</body>
</html>