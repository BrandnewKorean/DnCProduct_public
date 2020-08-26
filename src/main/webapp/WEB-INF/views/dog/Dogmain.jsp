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
	<c:if test="${logID == null}">
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
	</c:if>
	<c:if test="${logID != null}">
		<ul>
			<li>diary</li>
			<li>Store</li>
			<li>Board</li>
			<li>Location Service</li>
		</ul>
		<script type="text/javascript" src="resources/script/dogmain_login.js?ver=<%= System.currentTimeMillis()%>"></script>
	</c:if>
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
	<script type="text/javascript" src="resources/script/catlogin.js?ver=<%= System.currentTimeMillis()%>"></script>
	<script type="text/javascript" src="resources/script/dogmain.js?ver=<%= System.currentTimeMillis()%>"></script>
</body>
</html>