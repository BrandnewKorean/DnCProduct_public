<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>StoreHeader</title>
<link rel="stylesheet" type="text/css" href="resources/css/Header.css">
<link rel="stylesheet" type="text/css" href="resources/css/cat/store/StoreHeader.css?ver=<%= System.currentTimeMillis()%>">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="resources/script/header.js"></script>
<script type="text/javascript">
$(function(){
	$('#keyword').keypress(function(e){
		if(e.keyCode == 13) $('#searchButton').click();
	});
	
	$('#searchButton').on("click",function(){
		self.location='searchresult'
		+"?keyword="
		+$("#keyword").val()
	});
});
</script>
</head>
<body>
	<div class="header">
		<img id="catmainlogo" src="resources/image/logob.png" width=7%>
		<div id="storemenu">
			<label id=search_label for="keyword">
				<input type="text" name="keyword" id="keyword" title="통합검색" value="${pageMaker.search.keyword}">
				<button id="searchButton"><img src="resources/image/search_button.png" width=27px height=27px></button>
			</label>
			<br>
			<ul class=storemainmenu>
				<li>식료품
					<ul class=storesubmenu>
						<li><a href="catstoreview?group1=식료품&group2=사료">사료</a></li>
						<li><a href="catstoreview?group1=식료품&group2=간식">간식</a></li>
						<li><a href="catstoreview?group1=식료품&group2=영양제">영양제</a></li>
					</ul>
				</li>
				<li>배변/위생용품
					<ul class=storesubmenu>
						<li><a href="catstoreview?group1=배변/위생용품&group2=모래">모래</a></li>
						<li><a href="catstoreview?group1=배변/위생용품&group2=화장실">화장실</a></li>
						<li><a href="catstoreview?group1=배변/위생용품&group2=배변용품기타">배변용품기타</a></li>
					</ul>
				</li>
				<li>미용용품
					<ul class=storesubmenu>
						<li><a href="catstoreview?group1=미용용품&group2=브러쉬/빗">브러쉬/빗</a></li>
						<li><a href="catstoreview?group1=미용용품&group2=샴푸">샴푸</a></li>
						<li><a href="catstoreview?group1=미용용품&group2=린스">린스</a></li>
					</ul>
				</li>
				<li>생활용품
					<ul class=storesubmenu>
						<li><a href="catstoreview?group1=생활용품&group2=장난감">장난감</a></li>
						<li><a href="catstoreview?group1=생활용품&group2=스크래처">스크래처</a></li>
						<li><a href="catstoreview?group1=생활용품&group2=캣타워">캣 타워</a></li>
						<li><a href="catstoreview?group1=생활용품&group2=하우스/집">하우스/집</a></li>
						<li><a href="catstoreview?group1=생활용품&group2=캐리어/이동장">캐리어/이동장</a></li>
						<li><a href="catstoreview?group1=생활용품&group2=식기">식기</a></li>
						<li><a href="catstoreview?group1=생활용품&group2=줄">줄</a></li>
						<li><a href="catstoreview?group1=생활용품&group2=터널">터널</a></li>
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
			</div>
		</c:if>
	</div>
</html>