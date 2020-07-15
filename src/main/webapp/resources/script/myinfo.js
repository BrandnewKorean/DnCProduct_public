$(function(){
	var profile = $('#image').val();
	
	console.log(profile);
	if(profile == ''){
		$('#profile').css({
			backgroundImage: 'url("/ex01/resources/profile/default_client.png")',
			backgroundRepeat: "no-repeat",
			backgroundSize: "cover"
		});
	}else{
		$('#profile').css({
			backgroundImage: 'url("/ex01/resources/profile/'+profile+'")',
			backgroundRepeat: "no-repeat",
			backgroundSize: "cover"
		});
	}
	
	$('#update').click(function(){
		$.ajax({
			url: 'update',
			type: 'get',
			data:{
				id: $('#cid').val(),
				password: $('#cpassword').val(),
				name: $('#cname').val(),
				birthday: $('#cbirthday').val(),
				//email: $('#cemail').val(),
				address: $('#caddress').val()
			},
			success: function(data){
				if(data.code == 0){
					alert('수정 성공 했습니다.');
				}else if(data.code == 1){
					alert('수정 실패 했습니다.');
				}
			}
		}) // ajax
	
	}) // update
	
	
	$('#delete').click(function(){
		$.ajax({
			url:'delete',
			type: 'get',
			success: function(data){
				if(data.code == 0){
					alert('탈퇴 성공');
				}else{
					alert('탈퇴 실패');
				}
				location.href='catmain';
			}
		})
		
	}) // delete
}); // ready

