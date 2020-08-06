$(function(){
	$('#submit').on('click', function(){
		var vo = {
			id: $('#id').val(),
			password: $('#password').val()
		}
		
		$.ajax({
			url: 'login',
			Type: 'get',
			data: vo,
			success: function(data){
				if(data.code == 0){
					alert('로그인 완료되었습니다');
					location.href = 'catmain';
				}else if(data.code == 1){
					alert('비밀번호가 일치하지 않습니다');
				}else if(data.code == 2){
					alert('아이디가 일치하지 않습니다');
				}else{
					alert('서버 오류로 인해 로그인 할 수 없습니다');
				}
			}
		});
	});
	
	$('#cancel').click(function(){
		location.href = 'catmain';
	});
});