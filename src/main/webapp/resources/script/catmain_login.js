$(function(){
	
	$('*').mouseup(function(e){
		e.stopImmediatePropagation();
		$('*').css('cursor', 'url("/ex01/resources/cursor/cat_default.cur"), auto');
	})
	
	$('*').mousedown(function(e){
		e.stopImmediatePropagation();
		$('*').css('cursor', 'url("/ex01/resources/cursor/cat_click.cur"), auto');
	});
	
	$.ajax({
		url: 'clientInfo',
		data: {code: "json"},
		success: function(data){
			if(data.cv.profile == ""){
				$('#profile_image').css({
					backgroundImage: 'url("/ex01/resources/profile/default_client.png")',
					backgroundRepeat: "no-repeat",
					backgroundSize: "cover" 
				});
			}else{
				$('#profile_image').css({
					backgroundImage: 'url("/ex01/resources/profile/'+data.cv.profile+'")',
					backgroundRepeat: "no-repeat",
					backgroundSize: "cover" 
				});
			}
		}
	});
	
	$('#profile_image').click(function(){
		$('#client_info').slideToggle('slow',function(){
			$.ajax({
				url: 'clientInfo',
				data: {code: "not"},
				success: function(result){
					$('#client_result').html(result);
				}
			});
		});
	});
	
	$('#catmainlogo').click(function(){
		location.href = "catmain";
	});
	
	$('#logout').click(function(){
		$.ajax({
			url: 'logout',
			success: function(data){
				if(data.result == true){
					alert('로그아웃 되었습니다');
					location.reload();
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
});
