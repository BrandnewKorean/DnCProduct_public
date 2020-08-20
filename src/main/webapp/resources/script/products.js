$(function(){
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
					location.href = "catmain";
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
					location.reload();
					break;
				case 1:
					alert('탈퇴 처리에 실패했습니다');
					location.reload();
					break;
				}
			}
		});
	});
	
	$.ajax({
		url: "productimage",
		data: {productcode: $('#productcode').val()},
		success: function(data){
			console.log(data.list);
			console.log(data.list[0].ismain);
			for(var i=0;i<data.list.length;i++){
				$('#product_image_button').append('<button class=image_buttons id='+data.list[i].filename+'><img src="resources/productimage/'+data.list[i].filename+'" width=98% height=98%></button>');
			}
			
			$('.image_buttons').click(function(){
				$('#image_view').attr('src','resources/productimage/'+$(this).attr('id'));
			});
		}
	});
	
	var number = 1;
	var price = parseFloat($('#product_price').text().replace(',',''));
	var df = new DecimalFormat("###,###");
	
	$('#number').text(number);
	$('#total_price').text(df.format(price));
	$('#number_input').val(number);
	
	$('#number_minus').click(function(){
		number--;
		if(number <= 0) number = 1;
		$('#number').text(number);
		$('#number_input').val(number);
		$('#total_price').text(df.format(number*price));
	});
	
	$('#number_plus').click(function(){
		number++;
		$('#number').text(number);
		$('#number_input').val(number);
		$('#total_price').text(df.format(number*price));
	});
	
	$('#number_input').focusout(function() {
		if(parseInt($('#number_input').val())){
			if(parseInt($('#number_input').val()) < 1){
				alert('수량은 1이상만 입력 가능합니다');
				$('#number_input').val(number);
			}else{
				number = parseInt($('#number_input').val());
			}
		}else{
			alert('잘못 입력');
			$('#number_input').val(number);
		}
		
		$('#number').text(number);
		$('#total_price').text(df.format(number*price));
	});
});