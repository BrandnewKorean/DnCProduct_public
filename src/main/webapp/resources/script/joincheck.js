function idCheck(){
	var joinid=$('#joinid').val();
	if(joinid.length<4||joinid.length>20){
		$('#idmessage').html('ID는 5글자 이상 20글자 미만으로 하시오');
		$('#joinid').focus();
		return false;
	}else if(joinid.replace(/[a-z.0-9]/gi,'').length>0){
		$('idmessage').html('ID 는 영문자와 숫자로만 입력하시오');
		$('joinid').focus();
		return false;
	}else{
		$('#idmessage').html('');
		return true;
	}
}// idCheck()



