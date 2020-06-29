<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dg&Ct</title>
<link rel="icon" type="image/png" href="resources/image/test.png">
<link rel="stylesheet" type="text/css" href="resources/css/Homepage.css?ver=<%= System.currentTimeMillis()%>">
</head>
<body>
	<div class=container>
		<div class=blue>
			<div class=bluepointer id=blue1></div>
			<div class=bluepointer id=blue2></div>
		</div>
		<div class=red>
			<div class=redpointer id=red1></div>
			<div class=redpointer id=red2></div>
		</div>
		<div class=dog id=dog1></div>
		<div class=cat id=cat1></div>
		<div class=dog id=dog2></div>
		<div class=cat id=cat2></div>
		<div class=message>
			<a style="color:red;">P</a>
			<a style="color:orange;">e</a>
			<a style="color:gold;">t</a>
			<a style="color:green">s</a><br>
			<a style="color:DarkSalmon">W</a>
			<a style="color:Fuchsia">o</a>
			<a style="color:GreenYellow">r</a>
			<a style="color:PaleGreen">l</a>
			<a style="color:LightPink">d</a>
		</div>
	</div>
	<div class=logo></div>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript" src="resources/script/homepage.js?ver=<%= System.currentTimeMillis()%>"></script>
</body>
</html>