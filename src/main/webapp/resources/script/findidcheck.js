var mailCheck =0;
var clickOne = 1;

function findid(){
	if(clickOne==0){
		return false;
	}else{
		var email=$('#email').val();
		console.log(email);
		var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
		
		if(email==""){
			alert('필수 입력 사항입니다');
			return false;
		}else if(exptext.test(email) == false){
			alert('이메일 형식이 아닙니다');
			return false;
		}else{
			$.ajax({
				type:'Get',
				url:'FindId',
				data:{
					email:document.getElementById("email").value
				},
				success: function(data){
					if(data.result==true){
						$('#writebox').remove();
						$('#sendsuccess').attr("style","");
						mailCheck=1;
						clickOne=0;
						document.getElementById('getemail').innerHTML = data.email;
						return true;
					}else{
						alert('해당 이메일 주소를 찾을 수 없습니다');
						return false;
					}
				}
			});
		}
	}
}
