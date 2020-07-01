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
var pCheck=false;
var nCheck=false;
var bCheck=false;
var eCheck=false;
var aCheck=false;

$(function(){
	$('#joinid').focus();
	$('#joinid').focusout(function(){
		iCheck=idCheck();
	});
});

$(function(){
	$('#joinpw').focusout(function(){
		pCheck=pwCheck();
	});
	
});

$(function(){
	$('#joinname').focusout(function(){
		nCheck=nameCheck();
		});
	});

$(function(){
	$('#joinbirthday').focusout(function(){
		bCheck = birthdayCheck();
	});
});

$(function(){
	$('#joinemail').focusout(function(){
		eCheck=emailCheck();		
	});
});

$(function(){
	$('#joinaddress').focusout(function(){
		aCheck=addressCheck();
	});
});

function allCheck(){
	if(iCheck==true&&pCheck==true&&nCheck==true&&
			bCheck==true&&eCheck==true&&aCheck==true)
		return true;
	else{
		alert('가입 시, 모든 항목을 입력해주세요');
		return false;
	}
};



</script>

<h1> D&C 회원가입</h1>

<form action="catmain" method="post">
	<div>I  D  :
		<input id="joinid" name="id" type="text" >
		<br>
		<span id="idmessage" "style="color:red"></span>
	</div>
	<div>PassWord  :
		<input id="joinpw" name="password" type="password">
		<span id="pwmessage" style="color:red"></span>
	</div>
	<div>Name  :
		<input id="joinname" name="name" type="text">
		<span id="namemessage" style="color:red"></span>
	</div>
	<div>BirthDay  :
		<input id="joinbirthday" name="birthday" type="date">
		<span id="birthdaymessage" style="color:red"></span>
	</div>
	<div>E-mail  :
		<input id="joinemail" name="email" type="email">
		<span id="emailmessage" style="color:red"></span>
	</div>
	<div>Address  :
		<input id="joinaddress" name="address" type="text"> 
		<span id="addressmessage" style="color:red"></span>
	</div>
	<button type="submit" onclick="return allCheck()">확인</button>
	<button onclick="location.href='catmain '">취소</button>
</form>




</body>
</html>