<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join Terms</title>
<script src="resources/script/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="resources/script/jointerms.js?ver=<%= System.currentTimeMillis()%>"></script>
<link rel="stylesheet" type="text/css" href="resources/css/cat/join/joinTerms.css?ver=<%= System.currentTimeMillis()%>">
</head>
<body>
<div class=container>
	<div class=title>
		<img id="termsimg" src="resources/image/logoe.png" onclick="location.href='catmain'">
		<h1>D&C 회원가입 동의 약관</h1>
	</div>
	<form name="term" action="JoinForm" method="post" id='termsform'>
		<label for=agree1><input type="checkbox" name="agree1" class=agree id="agree1"> 이용약관 동의(필수)</label>
			<div class=terms id=terms0></div>
		<label for=agree2><input type="checkbox" name="agree2" class=agree id="agree2"> 개인정보 동의(필수)</label>
			<div class=terms id=terms1></div>
		<label for=agree3><input type="checkbox" class=agree id=agree3> 위치 서비스 동의(선택)</label>
			<div class=terms id=terms2></div>
		<label for=agree4><input type="checkbox" class=agree id=agree4> 광고 수신 여부 동의(선택)</label><br><br>
		<div class=buttons>
			<button id="termssubmit">확인</button> 
			<button onclick="location.href='catmain'">취소</button>
		</div>
<<<<<<< HEAD
		<br><br><br>
	<input type="radio"> 광고 수신 여부 동의(선택)<br><br><br><br>
<<<<<<< HEAD
	<button type="submit">확인</button> 
=======
	<button id="termssubmit">확인</button> 
>>>>>>> refs/remotes/origin/master
	<button onclick="location.href='catmain'">취소</button>
	
	<br><br><br><br>
	
</form>



=======
	</form>
</div>
>>>>>>> refs/remotes/origin/master
</body>
</html>