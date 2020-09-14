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
	.myinfo_col{
	
	}
	.myinfo_val{
		margin-bottom: 10px;
	}
</style>
</head>
<body>
	<div id=profile>
		<input type="hidden" id=image value="${client.profile}">
	</div>
	<div class=myinfo_col>ID</div><div class=myinfo_val id=myinfo_id>${cv.id}</div>
	<div class=myinfo_col>Name</div><div class=myinfo_val id=myinfo_name>${cv.name}</div>
	<div class=myinfo_col>Birthday</div><div class=myinfo_val id=myinfo_birthday>${cv.birthday}</div>
	<div class=myinfo_col>E-mail</div><div class=myinfo_val id=myinfo_email>${cv.email}</div>
	<div class=myinfo_col>Address</div><div class=myinfo_val id=myinfo_address>${cv.address}</div>
	<button id=logout>logout</button>
	<button id=updatef>update</button>
	<button id=delete>회원탈퇴</button>
	<a href="catbasketform"><button>장바구니</button></a>
<script src="http://code.jquery.com/jquery-latest.min.js?ver=<%= System.currentTimeMillis() %>"></script>
<script type="text/javascript" src="resources/script/myinfo.js?ver=<%= System.currentTimeMillis()%>"></script>
</body>
</html>