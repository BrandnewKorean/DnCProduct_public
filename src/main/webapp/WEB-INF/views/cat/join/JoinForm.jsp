<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>D&C Join</title>

<link rel="stylesheet" type="text/css" href="resources/css/cat/join/joinForm.css?ver=<%= System.currentTimeMillis()%>">
<script src="resources/script/jquery-3.2.1.min.js"></script>
<<<<<<< HEAD
<script src="resources/script/joincheck.js?ver=<%= System.currentTimeMillis() %>"></script>
=======
<script src="resources/script/joincheck.js"></script>


<script>
	var icheck = false;
	var pcheck = false;
	var ncheck = false;
	var bcheck = false;
	var echeck = false;
	//var acheck = false;
	
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
		
		/* $('#joinaddress').focusout(function() {
			acheck=addressCheck();
		}); // address */
	
	}); // ready
	
	
	function allCheck() {
		if(/* icheck==true && pcheck==true && ncheck==true
				&& bcheck==true && echeck==true && acheck==true)
			return true; */
			icheck==true && pcheck==true && ncheck==true
			&& bcheck==true && echeck==true)
		return true;
		else{
			alert('주소를 제외한 모든 항목이 필수 입력 항목입니다.');
			return false;
		}
	} // allcheck
	
	function idDuplicateCheck() {
		
		if(iCheck==false){iCheck=idCheck();}
		else{
			var url = "idDuplicateCheck?id"+$('#joinid').val();
			window.open(url,"_blank",
			"toolbar=no,menubar=yes,scrollbars=yes,resizable=yes,width=500,height=400");
		}
	} //idDuplicateCheck()
	
	
	
</script>



>>>>>>> refs/remotes/origin/BrooklynKimBranch
</head>
<body>

<img id="joinformimg" onclick="location.href='catmain'" src="resources/image/logoe.png">

<h1>D&C 회원가입</h1>

<form action="join" method="post">
<<<<<<< HEAD
	<div class="jointext">아이디<br>
		<input id="joinid" name="id" type="text">
		<br>
		<span id="idmessage" style="color: red"></span>
	</div>
=======

<div class="jointext">아이디<br>
	<input id="joinid" name="id" type="text">
	<input type="button" value="중복확인" id="idDuplicate" onclick="idDuplicateCheck()"> <!-- id와 onclick(선언형 함수)명이 동일해서 충돌되는 현상 -->
>>>>>>> refs/remotes/origin/BrooklynKimBranch
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
		<input id="joinemail" name="email" type="email">
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
<<<<<<< HEAD
<button onclick="location.href='catmain'">취소</button>
=======
<!-- <button onclick="location.href='catmain'">취소</button> -->
<input type="button" onclick="location.href='catmain'" readonly value="취소">

>>>>>>> refs/remotes/origin/BrooklynKimBranch
<br><br><br>
</form>
<<<<<<< HEAD
=======


<br><br><br>

>>>>>>> refs/remotes/origin/BrooklynKimBranch
</body>
</html>