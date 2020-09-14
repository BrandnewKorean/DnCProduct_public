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
<script type="text/javascript" src="/resources/script/storemain.js?ver=<%= System.currentTimeMillis() %>>"></script>
</head>
<body>
	<jsp:include page="StoreHeader.jsp"></jsp:include>
	<div class=intro>
		<div id=ad_banner>
			<ul class=slide_wrap>
				<li class=slide_item><a href="products?productcode=cat03010003&seq=163"><img class=item src="/resources/image/choose-cat-brush.jpg"></a></li>
				<li class=slide_item><img class=item src="/resources/image/142617898-cute-persian-kitten-playing-on-cat-tower.jpg"></li>
				<li class=slide_item><img class=item src="/resources/image/84808a6f400907b372d958de64ea15c4.jpg"></li>
				<li class=slide_item><img class=item src="/resources/image/btn_google_signin_light_normal_web.png"></li>
			</ul>
		</div>
		<div id=ad_banner_remote>
			<button class=banner_btn id=banner_left>&#60;</button>
			<button class=banner_btn id=banner_right>&#62;</button>
		</div>
	</div>
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
	<jsp:include page="StoreFooter.jsp"></jsp:include>
</body>
</html>