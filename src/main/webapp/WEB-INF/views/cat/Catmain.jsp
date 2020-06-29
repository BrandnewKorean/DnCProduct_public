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
		<div class=cmenu id=diary></div>
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