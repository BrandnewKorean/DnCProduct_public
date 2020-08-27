<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta name="google-signin-client_id" content="464025460206-5ffi7i9pibd984alsf29h6e565n0s4co.apps.googleusercontent.com">
<title>Login</title>
<script>
var mailCheck = 0;
var clickOne = 1;
var findid = function() {
	if(clickOne == 0){
		return false;
	} else {
	var email = $('#email').val();
	console.log(email);
	var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
	if (email == "") {
		$('#mailCheck3').attr("style", "");
		return false;
	}else if(exptext.test(email) == false){
		console.log("error2");
		$('#mailCheck3').attr("style", "display: none");
		$('#mailCheck1').attr("style","");
		return false;
	}else {
		$('#mailCheck3').attr("style", "display: none");
		$.ajax({
			type: 'Get',
			url : 'FindId',
			data : {
				email : document.getElementById("email").value
			},
			success : function(data){
				if(data.result == true){
					$('#writebox').remove();
					$('#sendsuccess').attr("style","");
					mailCheck = 1;
					clickOne = 0;
					document.getElementById('getemail').innerHTML = data.email;
					return true;
				}else if(data.result == false){
					$('#mailCheck2').attr("style","");
					return false;
				}
			}
		});
	}
	}
}
</script>
</head>
<body>
<h3>계정 찾기</h3>

			<div>
				<ul>
					<li style="display: none" id="mailCheck1">[이메일] : 이메일 형식이
						아닙니다.</li>
					<li style="display: none" id="mailCheck2">[이메일] : 해당 이메일 주소를
						찾을 수 없습니다.</li>
					<li style="display: none" id="mailCheck3">[이메일] : 필수 입력입니다.</li>
				</ul>
			</div>

			<div>
				<div>
					<div id="writebox">
						<p>이메일 주소를 입력해 주세요.</p>
						<p>
							회원 가입시 입력하신 이메일 주소를 입력하시면,<br> 해당 이메일로 아이디를 보내드립니다.
						</p>
						<div>
							<input type="email" name="email" id="email"
								placeholder="이메일 주소" value="" />
						</div>
						<button onclick="findid()">계정 찾기</button>
						<a href="home">취소</a>
					</div>
					<div style="display: none" id="sendsuccess">
						<span id="getemail"></span>
						<p class="lead">로 요청하신 계정정보를 보냈습니다.</p>
						<p>
							해당 이메일을 확인 하시고, 비밀번호 변경이 필요하신 경우 해당 이메일을 통해 변경 가능합니다..<br /> ※
							서비스에 따라 스팸으로 분류 되있을 수도 있습니다. 스팸함도 꼭 확인해 주시기 바랍니다.
						</p>
						<a href="/loginf">로그인</a>
					</div>
				</div>
			</div>
	<br><br><br>
<script src="http://code.jquery.com/jquery-latest.min.js?ver=<%= System.currentTimeMillis() %>"></script>
<script type="text/javascript" src="resources/script/catlogin.js?ver=<%= System.currentTimeMillis()%>"></script>
</body>
</html>