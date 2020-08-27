$(function(){
	var profile = $('#image').val();
	
	if(profile == ''){
		$('#profile').css({
			backgroundImage: 'url("/resources/profile/default_client.png")',
			backgroundRepeat: "no-repeat",
			backgroundSize: "cover"
		});
	}else{
		$('#profile').css({
			backgroundImage: 'url("/resources/profile/'+profile+'")',
			backgroundRepeat: "no-repeat",
			backgroundSize: "cover"
		});
	}
	
	$('#update').click(function(){
		$.ajax({
			url: 'update',
			data:{
				id: $('#cid').val(),
				password: $('#cpassword').val(),
				name: $('#cname').val(),
				birthday: $('#cbirthday').val(),
				//email: $('#cemail').val(),
				address: $('#caddress').val(),
			},
			success: function(data){
				if(data.code == 0){
					alert('수정 성공 했습니다');
				}else if(data.code == 1){
					alert('수정 실패 했습니다');
				}
			}
		});
	});
	
	$('#logout').click(function(){
		$.ajax({
			url: 'logout',
			success: function(data){
				if(data.result == true){
					alert('로그아웃 되었습니다');
					location.href = "home";
				}else{
					alert('로그인 후 사용하세요');
				}
			}
		});
	});
	
	$('#updatef').click(function(){
		$.ajax({
			url: 'updatef',
			success: function(result){
				$('#client_result').html(result);
			}
		});
	});
	
	$('#delete').click(function(){
		$.ajax({
			url: 'delete',
			success: function(data){
				switch(data.code){
				case 0:
					alert('정상적으로 탈퇴 처리 되었습니다');
					location.href = "home";
					break;
				case 1:
					alert('탈퇴 처리에 실패했습니다');
					location.reload();
					break;
				}
			}
		});
	});
});