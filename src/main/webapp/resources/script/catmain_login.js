$(function(){
	
	$('*').mouseup(function(e){
		e.stopImmediatePropagation();
		$('*').css('cursor', 'url("/ex01/resources/cursor/cat_default.cur"), auto');
	})
	
	$('*').mousedown(function(e){
		e.stopImmediatePropagation();
		$('*').css('cursor', 'url("/ex01/resources/cursor/cat_click.cur"), auto');
	}); 
		
	$('#diaryf').click(function(){
		location.href="diaryf";
	});
	
	$('#catboard').click(function(){
		location.href="catboard";
	});
	
	$('#storemain').click(function(){
		location.href = "storemain";
	});
});
