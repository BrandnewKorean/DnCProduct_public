<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<style type="text/css">
	*{
		background-color: LavenderBlush;
		margin: 0;
	}
	.container{
		margin: 0 auto;
		text-align: center;
		width: 30%;
	}
	input{
		width: 100%;
		height: 25px;
		margin-bottom: 5px;
		font-size: 15pt;
		text-align: center;
		border: none;
		outline-color: red;
		background-color: rgba(0,0,0,0.2);
	}
	#googlebtn{
		width: 200px;
		height: 50px;
		background-image: url("/ex01/resources/image/btn_google_signin_light_normal_web.png");
		background-repeat: no-repeat;
		background-position: center center;
		background-size: contain;
	}
</style>
<meta name="google-signin-client_id" content="464025460206-5ffi7i9pibd984alsf29h6e565n0s4co.apps.googleusercontent.com">
<script src="https://apis.google.com/js/platform.js" async defer></script>
</head>
<body>
<div class=container>
	<img src="/ex01/resources/image/logob.png" width="100%" onclick="location.href = 'catmain'">
	<h2>Login</h2><br>
	<input type="text" id=id placeholder="ID"><br>
	<input type="password" id=password placeholder="PW"><br><br>
	<button id=submit>확인</button>
	<button id=cancel>취소</button>
	<button id=googlebtn onclick="onSignIn"></button>
	<button onclick="signOut()">sign out</button>
</div>
<script src="http://code.jquery.com/jquery-latest.min.js?ver=<%= System.currentTimeMillis() %>"></script>
<script type="text/javascript" src="resources/script/catlogin.js?ver=<%= System.currentTimeMillis()%>"></script>
<script>
$(function(){
	
});

function onSignIn(googleUser) {
	gapi.load('auth2', () => {
		auth2 = gapi.auth2.getAuthInstance();
		console.log('Api inited');
		
		auth2.signIn().then(function(){
			console.log(auth2.currentUser.get().getId());
		});
	});
	
	var id_token = googleUser.getAuthResponse().id_token;
	
	$.ajax({
		url: 'googleLogin',
		type: 'post',
		data: {idtoken: id_token},
		success: function(data){
			alert('success');
		}
	});
}
function signOut(){
	var auth2 = gapi.auth2.getAuthInstance();
	auth2.signOut().then(function(){
		console.log('signed out');
	});
}
</script>
</body>
</html>