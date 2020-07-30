<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<style type="text/css">
	*{ 
		background-color: LavenderBlush;
		margin: 0;
	} 
	.container{
		margin: 0 auto;
		text-align: center;
		width: 30%;
	}
	input{
		width: 100%;
		height: 25px;
		margin-bottom: 5px;
		font-size: 15pt;
		text-align: center;
		border: none;
		outline-color: red;
		background-color: rgba(0,0,0,0.2);
	}
</style>
</head>
<body>
<div class=container>
	<img src="/ex01/resources/image/logob.png" width="100%" onclick="location.href = 'catmain'">
	<h2>Login</h2><br>
	<input type="text" id=id placeholder="ID"><br>
	<input type="password" id=password placeholder="PW"><br><br>
	<button id=submit>확인</button>
	<button id=cancel>취소</button>
	<div>
		<h5>소셜 로그인</h5>
		<a href="${google_url}"><img src="resources/image/btn_google_signin_light_normal_web.png" width=70%></a>
		<a href="${naver_url}"><img src="https://developers.naver.com/doc/review_201802/CK_bEFnWMeEBjXpQ5o8N_20180202_7aot50.png" width=70%></a>
		<a href="${kakao_url}"><img src="resources/image/kakao_login_large_narrow.png" width=70%></a>
	</div>
</div>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="resources/script/catlogin.js?ver=<%= System.currentTimeMillis()%>"></script>
</body>
</html>