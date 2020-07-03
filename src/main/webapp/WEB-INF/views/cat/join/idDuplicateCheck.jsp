<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>id 중복 검사</title>
<script src="resources/script/joincheck.js"></script>
<script src="resources/script/jquery-3.2.1.min.js"></script>
<script>

$(function() {
	$('#id').focusout(function() {
		idCheck();
	}).click(function() {
		$('#messageBlock').html('');
	}); // id_focusout_click
}); // ready

function idCheckSuccess(){
	opener.document.getElementById('id').value="${ID}";
	opener.document.getElementById('submit').disabled="";
	opener.document.getElementById('idDuplicate').disabled="disabled";
	opener.$('#id').attr("readonly","readonly");
	opener.$('#joinpw').focus();
	self.close();
}//idCheckSuccess()

</script>
</head>
<body>

<div id="idDuplicateCheckdiv">
<form action="idDuplicate" method="post"></form>
I   D :
	<input type="text" id="joinid" name="id" value="">
	<input type="button" value="ID 중복확인" onclick="return idCheck()">
	<span class="messageBlock" ></span>
</form>
<br><br><br>
<div class="messageBlock">
  <c:if test="${idUse=='T' }">
  	${duplicateid} 는 사용가능한 ID입니다
  	<input type="button" value="idCheckSuccess" onclick="idCheckSuccess()">
  </c:if>
  <c:if test="${idUse=='F'}">
    ${duplicate } 는 이미 존재합니다
	사용할 수 없으니 다시 입력하세요  
	
  	<script>
    	${'#id'}.focus();
    	opener.document.getElementById('id').value='';
    </script>
  </c:if>	
</div></div>	








</body>
</html>