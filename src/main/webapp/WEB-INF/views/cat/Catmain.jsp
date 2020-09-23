<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CatMain</title>
<link rel="icon" type="image/png" href="resources/image/logof.png">
<link rel="stylesheet" type="text/css" href="resources/css/cat/Catmain.css?ver=<%= System.currentTimeMillis()%>">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="/resources/script/weather.js?ver=<%= System.currentTimeMillis()%>"></script>
</head>
<body>
	<jsp:include page="../Header.jsp"></jsp:include>
	<div class=intro>
		<img id=background src="/resources/image/catmain.png">
		<canvas id=w_canvas></canvas>
	</div>
	<div class="head_message">
	All information about Cat<br>
	<a>Show off your pet and get useful information</a>
	</div>
	<ul class=main_menu>
		<li id=diaryf><a>Diary</a></li>
		<li id=storemain><a>Store</a></li>
		<li id=catboard><a>Board</a></li>
		<li><a href="location">Location Service</a></li>
	</ul>
	<jsp:include page="../Footer.jsp"></jsp:include>
	<script type="text/javascript" src="/resources/script/catmain_login.js?ver=<%= System.currentTimeMillis()%>"></script>
</body>
</html>
	
	
	