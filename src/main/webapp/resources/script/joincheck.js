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

function idCheck() {
	var joinid = $('#joinid').val();
	if(joinid.length < 4 || joinid.length > 20){
		$('#idmessage').html('ID는 4글자 이상 15글자 이하만 가능합니다.');
		return false;
	}else if (joinid.replace(/[a-z.0-9]/gi,'').length>0){
		$('#idmessage').html('ID는 영문자와 숫자로만 입력해주세요.');
		return false;
	}else{
		$('#idmessage').html('');
		return true;
	}
}; // idcheck

function pwCheck() {
	var joinpw=$('#joinpw').val();
	var passwordlength=joinpw.length; // ('#joinpw').length가 아니라 joinpw.legth가 되어야한다.
	
	if(joinpw.length < 5 || joinpw.length > 20){
		$('#pwmessage').html('Password는 5글자 이상 입력해야합니다.');
		return false;
	}else if(joinpw.replace(/[!@#$%^&*\(\)-=_+\'\"?\/\\~`\[\]\{\}]/gi,'').length >= passwordlength){
		$('#pwmessage').html('Password는 특수문자가 반드시 포함되어야 합니다.');
		return false;
	}else if(joinpw.replace(/[ㄱ-힣]/gi,'').length < passwordlength){
		$('#pwmessage').html('Password는 숫자나 영문자 또는 특수문자로만 입력해주세요.');
		return false;
	}else{
		$('#pwmessage').html('');
		return true;
	}
}; // pwCheck()

function nameCheck() {
	var joinname=$('#joinname').val();
	var namelength = joinname.length;
	
	if(namelength < 2 || namelength > 5){
		$('#namemessage').html('이름은 2글자 이상 5글자 이하로 입력해주세요.');
		return false;
	}else if(joinname.replace(/[a-z.가-힇]/gi,'').length > 0){
		$('#namemessage').html('이름은 한글로만 입력해주세요.');
		return false;
	}else{
		$('#namemessage').html('');
		return true;
	}
}; // nameCheck()

function birthdayCheck() {
	var joinbirthday = $('#joinbirthday').val();
	
	if(joinbirthday.length == 0){
		$('#birthdaymessage').html('생년월일을 입력해주세요.');
		return false;
	}else{
		$('#birthdaymessage').html('');
		return true;
	}
	
}; // birthday 

function emailCheck() {
	var joinemail = $('#joinemail').val();
	if(joinemail.length == 0){
		$('#emailmessage').html('E-mail을 정확하게 입력해주세요.');
		return false;
	}else{
		$('#emailmessage').html('');
		return true;
	}
	
}; // email


function addressCheck() {
	var joinaddress = $('#joinaddress').val();
	if(joinaddress.length == 0){
		$('#addressmessage').html('주소를 정확하게 입력해주세요.');
		return false;
	}else{
		$('#addressmessage').html('');
		return true;
	}
	
}; // address
