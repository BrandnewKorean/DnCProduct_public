<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<style type="text/css">
	.login_form{
		margin: 10px auto;
		text-align: center;
	}
	input{
		width: 100px;
	}
</style>
</head>
<body>
<div class=login_form>
<h5>로그인</h5>
<input type="text" placeholder="ID"><br>
<input type="password" placeholder="PW"><br><br>
<button id=submit>확인</button>
</div>
<script src="http://code.jquery.com/jquery-latest.min.js?ver=<%= System.currentTimeMillis() %>"></script>
	<script type="text/javascript" src="resources/script/catlogin.js?ver=<%= System.currentTimeMillis()%>"></script>
</body>
</html>