$(function(){
//	var image_index = 1;
	
//	setInterval(function(){
//		$('.head_image').animate({
//			opacity: "0.5"
//		}, 1000);
//		setTimeout(function() {
//			$('.head_image').animate({
//				opacity: "0"
//			},1000);
//		}, 3000);
//		$('.head_image').css({
//			backgroundImage: "url(\"/ex01/resources/interval/catmain/interval"+(image_index++)+".jpg\")"
//		});
//		if(image_index > 10) image_index = 1;
//	}, 4000);
	
	
	$('#join').click(function() {
		location.href='JoinTerms';
	});
	
	$('.head_message').animate({
		'opacity': 1
	}, 2000);
	
	$('*').mouseup(function(e){
		e.stopImmediatePropagation();
		$('*').css('cursor', 'url("/ex01/resources/cursor/cat_default.cur"), auto');
	})
	
	$('*').mousedown(function(e){
		e.stopImmediatePropagation();
		$('*').css('cursor', 'url("/ex01/resources/cursor/cat_click.cur"), auto');
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
				cursor:'url("/ex01/resources/cursor/cat_hover.cur"), auto'
			});
		},
		function(){
			$(this).css({
				color:'black',
				cursor:'url("/ex01/resources/cursor/cat_default.cur"), auto'
			});
		}
	);
	
	$('#login').on('click', function(e){
		e.stopImmediatePropagation();
		if($('.tmenu_result').html() == ''){
			$('.tmenu_result').animate({
				height: '150px'
			}, 500);
			
			$.ajax({
				url: 'catloginf',
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
			cursor:'url("/ex01/resources/cursor/cat_hover.cur"), auto'
		});
	},function(){
		$(this).css({
			cursor:'url("/ex01/resources/cursor/cat_default.cur"), auto'
		});
	});
	
	$('.cmenu').hover(function(){
		$(this).css({
			border: "3px solid aqua"
		});
	},function(){
		$(this).css({
			border: ""
		});
	});
});