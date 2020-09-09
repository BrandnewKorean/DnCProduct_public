$(function(){
	$.ajax({
		url: "productimage",
		data: {productcode: $('#productcode').val()},
		success: function(data){
			for(var i=0;i<data.list.length;i++){
				$('#product_image_button').append('<button class=image_buttons id='+data.list[i].filename+'><img src="resources/productimage/'+data.list[i].filename+'" width=98% height=98%></button>');
			}
			
			$('.image_buttons').click(function(){
				$('#image_view').attr('src','resources/productimage/'+$(this).attr('id'));
			});
		}
	});
	
	var number = 1;
	var price = parseInt($('#product_price').text().replace(',',''));
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
	
	$('#shopping_basket').click(function(){
		$.ajax({
			url:'basket',
			data:{
				seq: $('#seq').val()
			},
			success:function(data){
				switch(data.code){
				case 0:
					alert('이미 장바구니에 등록되었습니다');
					break;
				case 1:
					alert('장바구니에 추가되었습니다');
					break;
				case 2:
					alert('장바구니에 추가 실패');
					break;
				case 3:
					alert('로그인 후 이용해주세요');
					break;
				default : 
					alert('error');
					break;
				}
			}
		});
	});
});