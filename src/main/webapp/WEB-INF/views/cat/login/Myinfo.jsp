<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내정보</title>
<style type="text/css">
	#profile{
		margin: 0 auto;
		width: 100px;
		height: 100px;
		border-radius: 50%;
	}
</style>
</head>
<body>
	<div id=profile>
		<input type="hidden" id=image value="${client.profile}">
	</div>
	
	ID : ${cv.id}<br>
	Name : ${cv.name}<br>
	Birthday : ${cv.birthday}<br>
	E-mail : ${cv.email}<br>
	Address : ${cv.address}<br>
	 
	 <!-- cv에서 값을 가져온다 -->

    <script src="http://code.jquery.com/jquery-latest.min.js?ver=<%= System.currentTimeMillis() %>"></script>
	<script type="text/javascript" src="resources/script/myinfo.js?ver=<%= System.currentTimeMillis()%>"></script>


</body>
</html>