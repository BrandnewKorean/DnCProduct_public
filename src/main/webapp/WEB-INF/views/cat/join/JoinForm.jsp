<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join Form</title>
<script src="resources/script/joincheck.js"></script>
<script src="resources/script/jquery-3.2.1.min.js"></script>
</head>
<body>
<script>
var iCheck=false;

$(function(){
	$('#joinid').focus();
	$('#joinid').focusout(function(){
		iCheck=idCheck();
	});
	
var pCheck=false;



	
	
	
	
});


</script>







<h1> D&C 회원가입</h1>

<form action="catmain" method>
	<div>I  D  :
		<input id="joinid" type="text" >
		<br>
		<span id="idmessage" style="color:red"></span>
	</div>
	<div>PassWord  :
		<input type="password">
	</div>
	<div>Name  :
		<input type="text">
	</div>
	<div>BirthDay  :
		<input type="date">
	</div>
	<div>E-mail  :
		<input type="email">
	</div>
	<div>Address  :
		<input type="text"> 
	</div>
	<button type="submit">확인</button>
	<button onclick="location.href='catmain '">취소</button>
</form>




</body>
</html>