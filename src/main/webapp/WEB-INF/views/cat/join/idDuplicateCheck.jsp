<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID 중복 검사</title>

<link rel="stylesheet" type="text/css" href="resources/jqLib/myStyle.css">
<script src="resources/script/jquery-3.2.1.min.js"></script>
<script src="resources/script/joincheck.js"></script>

<script>

	$(function() {
		$('#duplicateid').focusout(function() {
			idCheck();
		}).click(function() {
			$('#messageBlock').html('');
		}); // id_focusout_click
	}); // ready


	function idCheckSuccess() {
		
		opener.document.getElementById('duplicateid').value="${ID}";
		// <script> 에서 EL을 문자열 Type 내부에 사용 가능함. 
		// => id 가 확정된 경우 더이상 id는 입력하지 못하도록 하고, 
		//    password 부터 입력 할 수 있도록 해줌.  
		//    joinForm의 submit 은 사용가능으로, ID 중복확인 버튼은 사용불가로.
		
		opener.document.getElementById('submit').disabled="";
		opener.document.getElementById('idDuplicateCheck').disabled="disabled";
		
		opener.$('#joinid').attr("readonly","readonly");
		
		opener.$('#joinpw').focus();
		self.close();
		
		
	}; // idCheckSuccess

</script>

</head>

<body>
	
	<div id = "idDuplicateCheckdiv">
		<img src="resources/image/logoe.png">
		<form action="idDuplicateCheck" method="post">
			I D : 
				<input type="text" id="duplicateid" name="id" value="">
				<input type="submit" value="ID 중복확인" onclick="return idCheck()">
				<span class="messageBlock"></span>
		</form>
		
		<br><br><hr><br>
		
		<div class="messasgeBlock">
			<c:if test="${idUse=='T'}">
				${duplicateid}는 사용 가능한 ID 입니다.
				<input type="button" value="확인" onclick="return idCheckSuccess()">
			</c:if>
			
			<c:if test="${idUse=='F'}">
				${duplicateid}는 이미 존재합니다.<br>
				다른 ID를 입력해주세요.
			
				<script>
					$('#duplicateid').focus();
					opener.document.getElementById('duplicateid').value='';
				</script>
			</c:if>
			
		</div>
		
	</div>

</body>
</html>