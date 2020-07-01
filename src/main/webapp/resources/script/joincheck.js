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
	var passwordlength=joinpw.length;
	
	if(joinpw.length < 5|| joinpw.length > 20){
		$('#pwmessage').html('Password는 5글자 이상 입력해야합니다.');
		return false;                
	}else if(joinpw.replace(/[!-*]/gi,'').length>=passwordlength){
		$('#pwmessage').html('Password는 특수문자가 반드시 포함되어야 합니다.');
		return false;
	}else if(joinpw.replace(/[0-9.!-*]/gi,'').length>0){
		$('#pwmessage').html('Password는 숫자와 특수문자로만 입력해주세요.');
		return false;
	}else{
		$('#pwmessage').html('');
		return true;
	}
	
}; // pwCheck()

function nameCheck() {
	var joinname=$('#joinname').val();
	var namelength = joinname.length;
	
	if(joinname.length < 2 || joinname.length > 5){
		$('#namemessage').html('이름은 2글자 이상 5글자 이하로 입력해주세요.');
		return false;
	}else{
		$('#namemessage').html('');
		return true;
	}
	
}; // nameCheck()

function birthdayCheck(){
	var joinbirthday=$('#joinbirthday').val();
	
	if(joinbirthday.length==0){
		$('#birthdaymessage').html('생일을 입력해주세요');
		return false;
	}else{
		$('#birthdaymessage').html('');
		return true;
	}
};

function emailCheck(){
	var joinemail=$('#joinemail').val();
	
	if(joinemail.length==0){
		$('#emailmessage').html('이메일을 입력해주세요');
		return false;
	}else{
		$('#emailmessage').html('');
		return true;
	}
};

function addressCheck(){
	var joinaddress=$('#joinaddress').val();
	
	if(joinaddress.length==0){
		$('#addressmessage').html('주소를 입력해주세요');
		return false;
	}else{
		$('#addressmessage').html('');
		return true;
	}
  };





















