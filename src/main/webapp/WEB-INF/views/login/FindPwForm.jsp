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
<title>비밀번호찾기</title>
<script>
	var oneclick = 1;
	
	var inputemail = 0;
	var inputid = 0;
	
	var sendPassword = function(){
		if(oneclick == 0){
			return false;
		}else{
			console.log("2222");
			var email = $('#email').val();
			var userid = $('#userId').val();
			console.log("3333"+userid);
			if(userid.length < 4){
				console.log(userid);
				return false;
			}else if (email == null){
				console.log(pfemail);
				return false;
			}else{
				console.log("4444"+email);
			$.ajax({
				type : 'Get',
				url : 'FindPw',
				data : {
					email : document.getElementById("email").value,
					userid : document.getElementById("userId").value
				},
				success : function(data){
					if(data.message == '200'){
						console.log("성공");
						$('#pfwritebox').remove();
						$('#pfsendsuccess').attr("style","");
						oneclick = 0;
						inputemail = 1;
						inputid = 1;
						document.getElementById('getemail').innerHTML = data.email;
						return true;
					}else if (data.message == 'fail') {
						console.log("실패");
						return false;
					}
				}
			});
			}
		}
	};
</script>
</head>
<body>
	<div id="create-user" class="content" role="main">
		<h3 class="content-header">비밀번호 찾기</h3>
			<ul>
				<li style="display: none" id="mailCheck1">[이메일] : 이메일 형식이 아닙니다.</li>
				<li style="display: none" id="mailCheck2">[이메일] : 해당 이메일 주소를 찾을
					수 없습니다.</li>
				<li style="display: none" id="mailCheck3">[이메일] : 필수 입력입니다.</li>
			</ul>
		</div>
		<div class="col-sm-8 col-sm-offset-2">
			<div class="panel panel-default panel-margin-10">
				<div class="panel-body panel-body-content text-center"
					id="pfwritebox">
					<p class="lead">이메일 주소와 아이디를 입력해 주세요.</p>
					<p>
						회원 가입시 입력하신 이메일 주소와 아이디를 입력하시면,<br /> 해당 이메일로 임시 비밀번호를 보내드립니다.
					</p>
					<div class="form-group">
						<input type="text" name="userId" id="userId"
							class="form-control form-control-inline text-center"
							placeholder="아이디" value="" />
					</div>
					<div class="form-group">
						<input type="text" name="email" id="email"
							class="form-control form-control-inline text-center"
							placeholder="이메일 주소" value="" />
					</div>
					<button class="btn btn-primary btn-block" onclick="sendPassword()">비밀번호발급</button>
					<button class="btn btn-default">
						<a href="loginf">취소</a>
					</button>
				</div>

				<div class="panel-body panel-body-content text-center"
					style="display: none" id="pfsendsuccess">
					<span id="getemail"></span>
					<p class="lead">로 요청하신 임시 비밀번호를 보냈습니다.</p>
					<p>
						해당 이메일을 확인 하시고, 로그인 후에 비밀번호를 꼭 변경 하세요.<br /> ※ 서비스에 따라 스팸으로 분류
						되있을 수도 있습니다. 스팸함도 꼭 확인해 주시기 바랍니다.
					</p>
					<a href="loginf" class="btn btn-primary btn-block">로그인</a>
				</div>
			</div>
		</div>
</body>
</html>