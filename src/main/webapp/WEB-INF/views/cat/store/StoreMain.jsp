<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cat Store</title>
<link rel="stylesheet" type="text/css" href="resources/css/cat/store/StoreMain.css?ver=<%= System.currentTimeMillis()%>">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="resources/script/storemain.js?ver=<%= System.currentTimeMillis()%>"></script>
</head>
<body>
	<div class="header">
		<img id="catmainlogo" src="resources/image/logob.png" width=7%>
		<div id="storemenu">
			<ul class=storemainmenu>
				<li>식료품
					<ul class=storesubmenu>
						<li>사료</li>
						<li>간식</li>
						<li>영양제</li>
					</ul>
				</li>
				<li>배변/위생용품
					<ul class=storesubmenu>
						<li>모래</li>
						<li>화장실</li>
						<li>배변용품기타</li>
					</ul>
				</li>
				<li>미용용품
					<ul class=storesubmenu>
						<li>브러쉬/빗</li>
						<li>샴푸</li>
						<li>린스</li>
					</ul>
				</li>
				<li>생활용품
					<ul class=storesubmenu>
						<li>장난감</li>
						<li>스크래처</li>
						<li>캣 타워</li>
						<li>하우스/집</li>
						<li>캐리어</li>
						<li>식기</li>
						<li>줄</li>
						<li>터널</li>
						<li>이동장</li>
					</ul>
				</li>
			</ul>
		</div>
		<c:if test="${logID == null }">
			<div class=tmenu>
				<span class=text id=home>Home</span>&nbsp;|&nbsp;<span class=text id=join>Join</span>&nbsp;|&nbsp;<span class=text id=login>Login</span>
			</div>
		</c:if>
			<c:if test="${logID != null}">
				<div id=profile_image></div>
				<div id=client_info>
					<div id=client_result></div>
					<button id=logout>logout</button>
					<button id=updatef>update</button>
					<button id=delete>회원탈퇴</button>
				</div>
			</c:if>
		<div class=tmenu_result id=tmenu_result></div>
	</div>
	
	<div class="container">
		container 영역<br>
		식료품  Top 5<br>
		배변/위생용품  Top 5<br>
		미용용품  Top 5<br>
		생활용품  Top 5<br>
	</div>
</body>
</html>