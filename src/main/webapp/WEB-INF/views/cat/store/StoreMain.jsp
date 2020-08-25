<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cat Store</title>
<link rel="stylesheet" type="text/css" href="resources/css/cat/store/StoreMain.css?ver=<%= System.currentTimeMillis()%>">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<jsp:include page="StoreHeader.jsp"></jsp:include>
	<div id=ad_banner>이벤트&주력상품 광고 영역</div>
	<div class="container">
		식료품  Top 5
		<div class=top5>
			식료품 Top5 이미지 영역
		</div>
		배변/위생용품  Top 5
		<div class=top5>
			배변/위생용품 Top5 이미지 영역
		</div>
		미용용품  Top 5
		<div class=top5>
			미용용품 Top5 이미지 영역
		</div>
		생활용품  Top 5
		<div class=top5>
			생활용품 Top5 이미지 영역
		</div>
	</div>
</body>
</html>