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
		<a href="catmain"><img src="resources/image/logof.png" id="logo" width=7%></a>D&C Project
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
		<div class=cmenu>
			<div id=diary>
				<div id=cat_footprint></div>
			</div>
			<div id=diary_message>
				귀여운 반려묘의<br>
				하루하루를 기록해보세요<br>
				<a>저희는 당신을 위해 private한 다이어리 공간을 제공합니다</a>
			</div>
		</div>
		<div class=cmenu id=service></div>
		<div class=cmenu></div>
		<div class=cmenu></div>
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