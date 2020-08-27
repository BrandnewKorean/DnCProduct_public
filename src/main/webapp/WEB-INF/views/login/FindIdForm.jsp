<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.security.SecureRandom"%>
<%@ page import="java.math.BigInteger"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta name="google-signin-client_id"
	content="464025460206-5ffi7i9pibd984alsf29h6e565n0s4co.apps.googleusercontent.com">
<title>ID찾기</title>
<style type="text/css">
* {
	background-color: LavenderBlush;
	margin: 0;
}

.container {
	margin: 8em auto;
	text-align: center;
	width: 30%;
}

.container-text {
	font-size: 8pt;
	border: 2em;
	color: red;
}

.lead {
	font-size: 14pt;
	border: 5em;
	color: navy;
}

input {
	width: 80%;
	height: 25px;
	margin-bottom: 5px;
	font-size: 10pt;
	text-align: center;
	border: none;
	outline-color: navy;
	background-color: white;
}

button {
	
}
</style>

</head>
<body>
	<div class="container">
		<img src="resources/image/logob.png" width="50%"
			onclick="location.href = 'home'"> <br>
		<br>
		<h3>계정 찾기</h3>
		<br>
		<br>
		<div>
			<div>
				<div class="container-text" id="writebox">
					<p class="lead">이메일 주소를 입력해 주세요.</p>
					<p>
						*회원 가입시 입력하신 이메일 주소를 입력하시면,<br> 해당 이메일로 아이디를 보내드립니다.
					</p>
					<br>
					<div>
						<input type="email" name="email" id="email" placeholder="이메일 주소"
							value="" />
					</div>
					<div style="font-size: 10pt;">
						<ul style="list-style: none; padding: 0;">
							<li style="display: none;" id="mailCheck1">[이메일] : 이메일 형식이
								아닙니다.</li>
							<li style="display: none;" id="mailCheck2">[이메일] : 해당 이메일
								주소를 찾을 수 없습니다.</li>
							<li style="display: none;" id="mailCheck3">[이메일] : 필수 입력입니다.</li>
						</ul>
					</div>
					<br>
					<button class="btn btn-primary btn-block" onclick="findid()">계정찾기</button>
					<a href="loginf"><button>취소</button></a>
				</div>

				<div style="display: none" id="sendsuccess">
					<span id="getemail"></span><p class="lead">로 요청하신 계정정보를 보냈습니다.</p>
					<p style="font-size: 15px;">
						해당 이메일을 확인 하시고, 비밀번호 변경이 필요하신 경우 해당 이메일을 통해 변경 가능합니다.<br> 
					<p style="color: red; font-size: 10px;">※서비스에 따라 스팸으로 분류 되있을 수도 있습니다. 스팸함도 꼭 확인해 주시기 바랍니다.
					</p><br><br>
					<a href="loginf"><button>로그인</button></a>
				</div>
			</div>
		</div>
		<br> <br> <br>
	</div>
	<%-- <div>
		<h2>소셜 로그인</h2>
		<a href="${google_url}"><img src="resources/image/구글.png" width=20%></a>
		<a href="${naver_url}"><img src="resources/image/네이버.png" width=20%></a>
		<a href="${kakao_url}"><img src="resources/image/카카오.png" width=20%></a>
	</div> --%>
	<script
		src="http://code.jquery.com/jquery-latest.min.js?ver=<%=System.currentTimeMillis()%>"></script>
	<script
		src="/resources/script/findidcheck.js?ver=<%=System.currentTimeMillis()%>"></script>
</body>
</html>