<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="resources/script/jquery-3.2.1.min.js"></script>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<script>
function checkLoginStatus(){
 	var loginBtn = document.querySelector('#loginBtn');
 	var nameTxt = document.querySelector('#name');
 	if(gauth.isSignedIn.get()){
 		  console.log('logined');
 		  loginBtn.value='Logout';
 		  var profile = gauth.currentUser.get().getBasicProfile();
		  nameTxt.innerHTML='Welcome <Strong>'+profile.getName()+'</strong>';
 	}else{
 		  console.log('logouted');
 		  loginBtn.value='Login';
 		  nameTxt.innerHTML='';
 	}
}
function init() {
  console.log('init');
  gapi.load('auth2', function() {
  	console.log('auth2');
  	window.gauth=gapi.auth2.init({
  	  client_id='799500449457-7dtqvt0900n7ocr4sttpofpcmjcuccli.apps.googleusercontent.com';
  	});
	gauth.then(function(){
		console.log('googleAuth success');
		checkLoginStatus();
  	},function(){
  		console.log('googleAuth fail');
  	});
  });
}
</script>
</head>
<body>
<span id="name"></span><input type="button" id="loginBtn" value="checking..."
	   onclick="
	   if(this.value==='Login'){
		   gauth.signIn().then(function(){
			   console.log('gauth.signin()');
			   checkLoginStatus();
		   });
	   }else{
		   gauth.signOut().then(function(){
			   console.log('gauth.signOut()');
			   checkLoginStatus();
		   });
	   }
">
<script src="https://apis.google.com/js/platform.js?onload=init" async defer></script>





</body>
</html>