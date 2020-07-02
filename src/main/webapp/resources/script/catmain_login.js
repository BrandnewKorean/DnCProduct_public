$(function(){
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
});
