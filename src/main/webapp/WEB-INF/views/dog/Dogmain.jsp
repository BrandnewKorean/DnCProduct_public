<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DogMain</title>
<link rel="icon" type="image/png" href="resources/image/logof.png">
<link rel="stylesheet" type="text/css" href="resources/css/dog/Dogmain.css?ver=<%= System.currentTimeMillis()%>">
</head>
<body>
	<jsp:include page="../Header.jsp"></jsp:include>
	<img id=test_image src="/resources/image/raindog.jpg">
	<canvas id="test_canvas"></canvas>

	<div class="main_menu">
			&nbsp;&nbsp;
			<span class=text id="mainimg_diary"><img class="mainimg_diary" src="../resources/image/dogdiary.jpg"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<span><img class="mainimg" src="../resources/image/dogshop.jpg"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<span><img class="mainimg" src="../resources/image/dogboard.jpg"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<span><img class="mainimg" src="../resources/image/doglocation.jpg"></span>
	</div>
	<jsp:include page="../Footer.jsp"></jsp:include>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript" src="/resources/script/rainyday.js"></script>
	<script type="text/javascript" src="resources/script/catlogin.js?ver=<%= System.currentTimeMillis()%>"></script>
	<script type="text/javascript" src="resources/script/dogmain.js?ver=<%= System.currentTimeMillis()%>"></script>
</body>
</html>