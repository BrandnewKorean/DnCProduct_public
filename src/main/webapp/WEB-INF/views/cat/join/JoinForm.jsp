<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<<<<<<< HEAD
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
=======
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
		
		$('#joinpw').focusout(function() {
			pcheck=pwCheck();
		}); // pwcheck
		
		$('#joinname').focusout(function() {
			ncheck=nameCheck();
		}); // joinname
		
		$('#joinbirthday').focusout(function() {
			bcheck=birthdayCheck();
		}); // birthday
		
		$('#joinemail').focusout(function() {
			echeck=emailCheck();
		}); // email
		
		$('#joinaddress').focusout(function() {
			acheck=addressCheck();
		}); // address
	
	}); // ready
	
	
	function allCheck() {
		if(icheck==true && pcheck==true && ncheck==true
				&& bcheck==true && echeck==true && acheck==true)
			return true;
		else{
			alert('모든 항목이 필수 입력 항목입니다.');
			return false;
		}
	} // allcheck
	
	
	
</script>



</head>
<body>

<img id="joinformimg" onclick="location.href='catmain'" src="resources/image/logoe.png">

<h1>D&C 회원가입</h1>
<br><br>

<form action="catmain" method="post">

<div class="jointext">아이디<br>
	<input id="joinid" name="id" type="text">
	<br>
	<span id="idmessage" style="color: red"></span>
</div>

<br>

<div class="jointext">비밀번호<br>
	<input id="joinpw" name="password" type="password">
	<br>
	<span style="color: red" id="pwmessage"></span>
</div>

<br>

<div class="jointext">이름<br>
	<input id="joinname" name="name" type="text">
	<br>
	<span style="color: red" id="namemessage"></span>
</div>

<br>

<div class="jointext">생년월일<br>
	<input id="joinbirthday" name="birthday" type="date" ><br>
	<br>
	<span style="color: red" id="birthdaymessage"></span>
</div>

<br>

<div class="jointext" >E-mail<br> 
	<input id="joinemail" name="email" type="email" value="@gmail.com">
	<br>
	<span style="color: red" id="emailmessage"></span>
</div>

<br>

<div class="jointext">주소<br>
	<input id="joinaddress" name="address" type="text">
	<br>
	<span style="color: red" id="addressmessage"></span>
</div>

<br>

<br><br>

<button type="submit" onclick="return allCheck()">확인</button>
<button onclick="location.href='catmain'">취소</button>

<br><br><br>

>>>>>>> refs/remotes/origin/master
</form>




</body>
</html>