<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DogMain</title>
<link rel="icon" type="image/png" href="resources/image/logof.png">
<link rel="stylesheet" type="text/css" href="resources/css/dog/Dogmain.css?ver=<%= System.currentTimeMillis()%>">
</head>
<body>
	<div class="header">
		<a href="dogmain"><img src="resources/image/logof.png" width=7%></a>D&C Project
		<div class=tmenu>
			<span class=text id=home>Home</span>|<span class=text id=join>Join</span>|<span class=text id=login>Login</span>
		</div>
		<div class=tmenu_result id=tmenu_result></div>
	</div>
	<div class="intro">
		<div class="head_image"></div>
		<div class="head_message">
		All information about Dog<br>
		<a>Show off your pet and get useful information</a>
		</div>
	</div>
	<div class="container">
		container 영역
	</div>
	<div class="footer">
		<div class=logo></div>
		Produce by D&C<br>
		© Copyright 2020 Y2K Technologies, Inc. All rights reserved.<br>
		<br>
		<br>
		<br>
		<br>
		<br>
	</div>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript" src="resources/script/dogmain.js?ver=<%= System.currentTimeMillis()%>"></script>
</body>
</html>