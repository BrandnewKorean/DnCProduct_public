<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 정보 수정하기</title>

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

	<div id = profile>
		<input type="hidden" id="image" value="${client.profile}">
	</div>


	ID : ${cv.id}<input type="hidden" id=cid value="${cv.id}"><br>
	<input type="hidden" id=cpassword value="${cv.password}">
	Name :<input type="text" id=cname value="${cv.name}"><br>
	Birthday : <input type="date" id=cbirthday value="${cv.birthday}"><br>
	<%-- Email : <input type="email" id=cemail value="${cv.email}"><br> --%> 
	Address : <input type="text" id="caddress" value="${cv.address }"><br>
	
	<button id=update>수정</button>
	
	<script src="http://code.jquery.com/jquery-latest.min.js?ver=<%= System.currentTimeMillis() %>"></script>
	<script type="text/javascript" src="resources/script/myinfo.js?ver=<%= System.currentTimeMillis()%>"></script>

</body>
</html>