var icheck = false;
var pcheck = false;
var ncheck = false;
var bcheck = false;
var echeck = false;
var eccheck = false;
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
	
	$('#joinemailconfirm').focusout(function() {
		eccheck=emailConfirm();
	});
	
	$('#userAddr').focusout(function() {
		console.log("this is the local variable that is supposed to be inputted => " + $('#userAddr').val());
		acheck=addressCheck();
	}); // address
	
}); // ready

function allCheck() {
	
	if(icheck==true && pcheck==true && ncheck==true && bcheck==true &&
			echeck==true && eccheck==true && acheck==true){
		$.ajax({
			url:'join',
			type:'post',
			data:{
				id: $('#joinid').val(),
				password: $('#joinpw').val(),
				name: $('#joinname').val(),
				birthday:$('#joinbirthday').val(),
				email:$('#joinemail').val(),
				addrress:$('#joinadrress').val()
			},
			success:function(data){
				if(data.result){
					alert('회원가입에 성공했습니다.');
					location.href = 'catmain';
				}else{
					alert('회원가입에 실패했습니다.');
				}
			} // success
		}); // ajax
		
	}else{
		alert('모든 항목은 필수 입력 항목입니다.');
	}

} // allcheck

function idCheck() {
	var result = false;
	var joinid = $('#joinid').val();
	if(joinid.length < 4 || joinid.length > 20){
		$('#idmessage').html('ID는 4글자 이상 15글자 이하만 가능합니다.');
		return false;
	}else if (joinid.replace(/[a-z.0-9]/gi,'').length>0){
		$('#idmessage').html('ID는 영문자와 숫자로만 입력해주세요.');
		return false;
	}else{
		$.ajax({
			type:'Post',
			url:'selectOne',
			data:{id:'#joinid'},
			async: false,
			success:function(data){
				if(data.result){
					$('#idmessage').html('');
					result = data.result;
				}else{
					$('#idmessage').html('이미 사용 중인 ID입니다.');
					result = data.result;
				}
			}
		}); // ajax
		return result;
	}
	/*else{
		$('idmessage').html('');
		return true;
	}*/
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
	
}; // emailcheck

function emailConfirmButton() {
	$.ajax({
		url: 'mailSending',
		data:{addrress:$('#joinmail').val()},
		async:false,
		success:function (data){
			if(data.result == false){
				alert('이메일 전송에 실패했습니다.');
			}else{
				alert('인증 메일이 발송되었습니다.');
				number = data.result;
			}
		} // if
	}) // ajax
} // emailbutton

function emailConfirm() {
	if(number == null){
		$('#emailmessage').html('이메일 인증 버튼을 눌러주세요.');
		return false;
	}else if(number != $('#joinmailconfirm').val()){
		$('#emailmessage').html('인증번호가 일치하지 않습니다.');
		return false;
	}else{
		$('emailmessage').html('인증 완료 되었습니다.');
		return true;
	}
		
} // emailconfirm

function addressCheck() {
	var joinaddress = $('#userAddr').val();
	if(joinaddress.length == 0){
		$('#addressmessage').html('주소를 정확하게 입력해주세요.');
		return false;
	}else{
		$('#addressmessage').html('');
		return true;
	}
	
}; // address
