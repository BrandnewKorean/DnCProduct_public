<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<style type="text/css">
	.container{
		margin: 0 auto;
		text-align: center;
		width: 60%;
	}
	#login_form{
		display: inline-block;
	}
	input{
		width: 100px;
	}
	*{
		background-color: LavenderBlush;
	}
</style>
</head>
<body>
<div class=container>
	<img src="/ex01/resources/image/logob.png" width=15%>
	<div id=login_form>
		Login<br>
		<input type="text" id=id placeholder="ID"><br>
		<input type="password" id=password placeholder="PW"><br><br>
		<button id=submit>확인</button>
		<button id=cancel>취소</button>
	</div>
</div>
<script src="http://code.jquery.com/jquery-latest.min.js?ver=<%= System.currentTimeMillis() %>"></script>
<script type="text/javascript" src="resources/script/catlogin.js?ver=<%= System.currentTimeMillis()%>"></script>
</body>
</html>