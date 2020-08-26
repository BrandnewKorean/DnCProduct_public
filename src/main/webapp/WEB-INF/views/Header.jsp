<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>
<link rel="stylesheet" type="text/css" href="resources/css/Header.css?ver=<%= System.currentTimeMillis()%>">
<script type="text/javascript" src="/resources/script/header.js?ver=<%= System.currentTimeMillis()%>"></script>
</head>
<body>
	<div class="header">
		<img id="catmainlogo" src="resources/image/logob.png" width=7%>
		<input type="hidden" id=islogin value="${logID}">
		<c:choose>
			<c:when test="${logID == null }">
				<div class=tmenu>
					<span class=text id=home>Home</span>&nbsp;|&nbsp;<span class=text id=join>Join</span>&nbsp;|&nbsp;<span class=text id=login>Login</span>
				</div>
			</c:when>
			<c:otherwise>
				<div id=profile_image></div>
				<div id=client_info>
					<div id=client_result></div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>