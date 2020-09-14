<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>
<link rel="stylesheet" type="text/css" href="resources/css/Header.css?ver=<%= System.currentTimeMillis()%>">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
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
				<div class=header_navigation>
					<ul class=header_navi_main>
						<li><a href="catmain">catmain</a>
							<ul class=header_navi_sub>
								<li><a href="diaryf">Diary</a></li>
								<li><a href="storemain">Store</a></li>
								<li><a href="catboard">Board</a></li>
								<li><a href="location">Location</a></li>
							</ul>
						</li>
						<li><a href="dogmain">dogmain</a>
							<ul class=header_navi_sub>
								<li><a href="#">Diary</a></li>
								<li><a href="#">Store</a></li>
								<li><a href="#">Board</a></li>
								<li><a href="#">Location</a></li>
							</ul>
						</li>
					</ul>
				</div>
				<div class=header_navigation_btn_loc>
					<div id=header_navigation_btn>
						<a id=navi_up>∨</a>
						<a id=navi_down>∨</a>
					</div>
					<div id=header_navigation_btn_hidden></div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>