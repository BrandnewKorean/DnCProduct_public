<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>D&C Join</title>

<link rel="stylesheet" type="text/css" href="resources/css/cat/join/joinForm.css?ver=<%= System.currentTimeMillis()%>">
<script src="resources/script/jquery-3.2.1.min.js"></script>
<script src="resources/script/joincheck.js"></script>


<script>
	var icheck = false;
	var pcheck = false;
	var ncheck = false;
	var bcheck = false;
	var echeck = false;
	var acheck = false;
	
	$(function(){
		
		$('#joinid').focus();
		$('#joinid').focusout(function() {
			icheck=idCheck();
		}); // idcheck
		
		$('#joinpw').focuout(function() {
			pcheck=pwCheck();
		}); // pwcheck
		
		$('#joinname').focusout(function() {
			ncheck=nameCheck();
		}); // joinname
		
		
	
	}); // ready
	
	
	
	
</script>



</head>
<body>

<img id="joinformimg" src="resources/image/logoe.png">

<h1>D&C 회원가입</h1>
<br><br>

<form action="catmain" method="post">

<div class="jointext">I D : 
	<input id="joinid" type="text">
	<br>
	<span id="idmessage" style="color: red"></span>
</div>

<div class="jointext">P W :
	<input id="joinpw" type="password">
	<span id="pwmessage"></span>
</div>

<div class="jointext">Name :
	<input id="joinname" type="text">
	<span id="namemessage"></span>
</div>

<div class="jointext">Birthd :
	<input type="date">
</div>

<div class="jointext">E-mail : 
	<input type="email">
</div>

<div class="jointext">Address : 
	<input type="text">
</div>

<br><br>

<button type="submit">확인</button>
<button onclick="location.href='catmain'">취소</button>

</form>




</body>
</html>