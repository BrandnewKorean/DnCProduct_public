<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CatMain</title>
<link rel="icon" type="image/png" href="resources/image/logof.png">
<link rel="stylesheet" type="text/css" href="resources/css/cat/Catmain.css?ver=<%= System.currentTimeMillis()%>">
</head>
<body>
	<div class="header">
<<<<<<< HEAD
		<a href="catmain"><img id="catmainlogo" src="resources/image/logob.png" id="logo" width=7%></a>
=======
		<img id="catmainlogo" src="resources/image/logob.png" width=7%>
>>>>>>> refs/remotes/origin/master
		<div class=tmenu>
			<span class=text id=home>Home</span>|<span class=text id=join>Join</span>|<span class=text id=login>Login</span>
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
	</div>
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
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript" src="resources/script/weather.js?ver=<%= System.currentTimeMillis()%>"></script>
	<script type="text/javascript" src="resources/script/catmain.js?ver=<%= System.currentTimeMillis()%>"></script>
</body>
</html>