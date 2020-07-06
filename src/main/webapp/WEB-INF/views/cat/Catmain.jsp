<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CatMain</title>
<link rel="icon" type="image/png" href="resources/image/logof.png">
<link rel="stylesheet" type="text/css" href="resources/css/cat/Catmain.css?ver=<%= System.currentTimeMillis()%>">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="resources/script/weather.js?ver=<%= System.currentTimeMillis()%>"></script>
</head>
<body>
	<div class="header">
		<img id="catmainlogo" src="resources/image/logob.png" width=7%>
		<div class=tmenu>
			<c:if test="${logID == null }">
				<span class=text id=home>Home</span>|<span class=text id=join>Join</span>|<span class=text id=login>Login</span>
			</c:if>
			<c:if test="${logID != null}">
				<div id=profile_image></div>
				${logID} 님 환영합니다
			</c:if>
		</div>
		<div class=tmenu_result id=tmenu_result></div>
	</div>
	<div class="intro">
		<canvas id=w_canvas></canvas>
		<div class="head_message">
		All information about Cat<br>
		<a>Show off your pet and get useful information</a>
		</div>
	</div>
	<c:if test="${logID == null}">
		<div class="container">
			<div class=cmenu id=diary>
				<div id=cat_footprint></div>
			</div>
			<div class=cmenu id=diary_message>
				반려묘와의<br>
				추억을 기록해보세요<br>
				<a>당신의 추억을 위한 개인 공간을 제공해드립니다</a>
			</div>
			<div class=cmenu id=store>
				<div id=truck></div>
			</div>
			<div class=cmenu id=store_message>
				반려묘를 위한 물품을<br>
				쉽게 구입해보세요<br>
				<a>다양한 물품들이 구비되어 있습니다</a>
			</div>
			<div class=cmenu id=board>
			</div>
			<div class=cmenu id=board_message>
				다양한 사람들과<br>
				소통해 보세요<br>
				<a>wow</a>
			</div>
			<div class=cmenu id=gps>
				<div class=location id=location1></div>
				<div class=location id=location2></div>
				<div class=location id=location3></div>
				<div class=location id=location4></div>
				<div class=location id=location5></div>
			</div>
			<div class=cmenu id=gps_message>
				주변의<br>
				고양이 관련 시설을<br>
				쉽게 찾이보세요<br>
				<a>wowowow</a>
			</div>
			<script type="text/javascript" src="resources/script/catmain.js?ver=<%= System.currentTimeMillis()%>"></script>
		</div>
	</c:if>
	<c:if test="${logID != null}">
		<ul class=main_menu>
			<li><a href="#" id=menu1>Diary</a></li>
			<li><a href="#">Store</a></li>
			<li><a href="#">Board</a></li>
			<li><a href="#">Location Service</a></li>
		</ul>
		<script type="text/javascript" src="resources/script/catmain_login.js?ver=<%= System.currentTimeMillis()%>"></script>
	</c:if>
	<div class="footer">
		<div class=logo></div>
		Produce by D&C<br>
		© Copyright 2020 D&C Technologies, Inc. All rights reserved.<br>
		<br>
		<br>
		<br>
		<br>
		<br>
	</div>	
</body>
</html>