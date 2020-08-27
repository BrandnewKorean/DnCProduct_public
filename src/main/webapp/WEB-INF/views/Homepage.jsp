<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>D&C</title>
<link rel="icon" type="image/png" href="resources/image/logof.png">
<link rel="stylesheet" type="text/css" href="resources/css/Homepage.css?ver=<%= System.currentTimeMillis()%>">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="/resources/script/weather.js"></script>
<script type="text/javascript" src="/resources/script/homepage.js"></script>
</head>
<body>
	<jsp:include page="Header.jsp"></jsp:include>
	<div class="intro">
		<canvas id=w_canvas></canvas>
		<div class="head_message">
		All information about Pet<br>
		<a>Show off your pet and get useful information</a>
		</div>
	</div>
	<div class="container">
		<div class=cmenu id=diary>
			<div id=cat_footprint></div>
		</div>
		<div class=cmenu id=diary_message>
			반려동물과의<br>
			추억을 기록해보세요<br>
			<a>당신의 추억을 위한 개인 공간을 제공해드립니다</a>
		</div>
		<div class=cmenu id=store>
			<div id=truck></div>
		</div>
		<div class=cmenu id=store_message>
			반려동물을 위한 물품을<br>
			쉽게 구입해보세요<br>
			<a>다양한 물품들이 구비되어 있습니다</a>
		</div>
		<div class=cmenu id=board>
		</div>
		<div class=cmenu id=board_message>
			다양한 사람들과<br>
			소통해 보세요<br>
			<a>게시판 형태의 커뮤니티를 제공합니다</a>
		</div>
		<div class=cmenu id=gps>
			<div class=location id=location1></div>
			<div class=location id=location2></div>
			<div class=location id=location3></div>
			<div class=location id=location4></div>
			<div class=location id=location5></div>
		</div>
		<div class=cmenu id=gps_message>
			주변의<br>
			반려동물 관련 시설을<br>
			쉽게 찾이보세요<br>
			<a>카카오맵 기반 위치서비스를 제공합니다</a>
		</div>
	</div>
	<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>