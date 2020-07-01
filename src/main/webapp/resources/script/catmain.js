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
		console.log(scrollBottom);
		
		if(scrollBottom <= $('#diary').offset().top + 240){
			console.log('cat foootprint init');
			$('#cat_footprint').stop();
			$('#cat_footprint').css({
				width: '100%',
				height: '100%',
				display: 'none'
			});
		}
		
		if(scrollBottom >= $('#diary').offset().top + 300){
			console.log('cat foootprint animate');
			$('#cat_footprint').css('display','block');
			$('#cat_footprint').animate({
				width: '25%',
				height: '40%'
			}, 500);
		}
		
		if(scrollBottom <= $('#truck').offset().top){
			console.log('truck init');
			$('#truck').stop();
			$('#truck').css('right','0');
		}
		
		if(scrollBottom >= $('#store').offset().top + 300){
			console.log('cat foootprint init');
			$('#truck').animate({
				right: '110%'
			}, 1000);
		}
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
});