<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>D&C Join</title>
<link rel="stylesheet" type="text/css" href="resources/css/cat/join/joinForm.css?ver=<%= System.currentTimeMillis()%>">
<script src="resources/script/jquery-3.2.1.min.js"></script>
<script src="resources/script/joincheck.js?ver=<%= System.currentTimeMillis() %>"></script>
<script>

function goPopup(){
	// 주소검색을 수행할 팝업 페이지를 호출합니다.
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	var pop = window.open("juso","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	
	// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
    //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
}

function jusoCallBack(roadFullAddr){
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.	
		$('#userAddr').val(roadFullAddr);
		acheck=addressCheck();
}
</script>
</head>
<body>
<img id="joinformimg" onclick="location.href='catmain'" src="resources/image/logoe.png">
<h1>D&C 회원가입</h1>

	
	<label for="joinid" class="label">아이디</label>
	<br>
	<div class="jointext">
			<span class="innerinput">
				<input id="joinid" name="id" type="text" placeholder="아이디">	
			</span>	
	<br>
		<span style="color: red" id="idmessage"></span>

	</div>
	
	
	<br>
	<div class="jointext">

	<label for="joinpw">비밀번호 </label>
	<br>
		<input id="joinpw" name="password" type="password" placeholder="비밀번호">
		<br>
		<span style="color: red" id="pwmessage"></span>
	</div>
	<br>
	<div class="jointext">
	<label for="joinname">이름</label>
	<br>
		<input id="joinname" name="name" type="text" placeholder="이름">
		<br>
		<span style="color: red" id="namemessage"></span>
	</div>
	<br>
	<div class="jointext">
	<label for="joinbirthday">생년월일</label>
	<br>
		<input id="joinbirthday" name="birthday" type="date"><br>
		<br>
		<span style="color: red" id="birthdaymessage"></span>
	</div>
	<br>

	<div class="jointext" >	
		<label for="joinemail">E-mail</label><br>
		<input id="joinemail" name="email" type="email" placeholder="E-mail">
		<button onclick="emailConfirmButton()">인증하기</button>

		<br>
		<span style="color: red" id="emailmessage"></span>
	</div>
	<br>
	<div class="jointext" >
	<label for="joinemail">E-mail 인증</label>
	<br> 
		<input id="joinemailconfirm" name="emailconfirm" type="text" placeholder="E-mail 인증번호"  >
		<br>
		<span style="color: red" id="emailmessage"></span>
	</div>
	<br>
	<div class="jointext">
	<label for="userAddr">주소</label><br>
		<input type="text" id="userAddr" name="address" class="form-control" placeholder="주소입력" readonly/>
		<button type="button" class="btn btn-warning" onclick="goPopup()">주소검색</button>
		<br>
		<span style="color: red" id="addressmessage"></span>
	</div>
	<br>
	<br><br>
<button type="submit" onclick="return allCheck()">확인</button>
<!-- <button onclick="location.href='catmain'">취소</button> -->
<input type="button" onclick="location.href='catmain'" readonly value="취소">
<br><br><br>
</body>
</html>