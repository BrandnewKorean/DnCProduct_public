$(function(){
	$('.head_message').animate({
		'opacity': 1
	}, 3000);
	
	$('*').mouseup(function(e){
		e.stopImmediatePropagation();
		$('*').css('cursor', 'url("/ex01/resources/cursor/dog_default.cur"), auto');
	})
	
	$('*').mousedown(function(e){
		e.stopImmediatePropagation();
		$('*').css('cursor', 'url("/ex01/resources/cursor/dog_click.cur"), auto');
	});
	
	$(window).scroll(function(){
		var scrollTop = $(window).scrollTop();
		var scrollBottom = scrollTop + window.innerHeight;
		var newPosition = scrollTop + "px";
		
		$('.header').css('top', newPosition);
	});
	
	$('.text').hover(function(){
		$(this).css({
				color:'red',
				cursor:'url("/ex01/resources/cursor/dog_hover.cur"), auto'
			});
		},
		function(){
			$(this).css({
				color:'black',
				cursor:'url("/ex01/resources/cursor/dog_default.cur"), auto'
			});
		}
	);
	
	$('#login').click(function(e){
		e.stopImmediatePropagation();
		if($('.tmenu_result').html() == ''){
			$('.tmenu_result').animate({
				height: '150px'
			}, 500);
			
			$.ajax({
				url: 'dogloginf',
				success: function(result){
					$('.tmenu_result').html(result);
				}
			});
		}else{
			$('.tmenu_result').animate({
				height: '0px'
			}, 500);
			$('.tmenu_result').html('');
		}
	});
	
	$('#home').click(function(e){
		e.stopImmediatePropagation();
		location.href = "home";
	});
	
	$('img').hover(function(){
		$(this).css({
			cursor:'url("/ex01/resources/cursor/dog_hover.cur"), auto'
		});
	},function(){
		$(this).css({
			cursor:'url("/ex01/resources/cursor/dog_default.cur"), auto'
		});
	});
});