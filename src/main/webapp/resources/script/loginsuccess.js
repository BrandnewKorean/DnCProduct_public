$(function(){
	var blue;
	var red;
	var blueTop = parseInt($('.blue').css('top'));
	var redTop = parseInt($('.red').css('top'));
	console.log(redTop);
	
	$('*').css('cursor','url("/ex01/resources/cursor/footprint_default.cur"), auto');
	$('*').mouseup(function(){
		$(this).css('cursor','url("/ex01/resources/cursor/footprint_default.cur"), auto');
	});
	
	$('*').mousedown(function(){
		$(this).css('cursor','url("/ex01/resources/cursor/footprint_click.cur"), auto');
	});
	
	$('.dog').hover(function(){
		$(this).css('cursor','url("/ex01/resources/cursor/footprint_hover.cur"), auto');
		$('.bluepointer').css('display','block');
		
		blue = setInterval(function(){
			$('.blue').css('top',(blueTop-5)+'px');
			$('.blue').animate({
				top: blueTop+'px'
			},500);
		},600);
	},function(){
		$('.bluepointer').css('display','none');
		clearInterval(blue);
		$('.blue').css('top',blueTop+'px');
	});
	
	$('.cat').hover(function(){
		$(this).css('cursor','url("/ex01/resources/cursor/footprint_hover.cur"), auto');
		$('.redpointer').css('display','block');
		
		red = setInterval(function(){
			$('.red').css('top',(redTop-5)+'px');
			$('.red').animate({
				top: redTop+'px'
			},500);
		},600);
	},function(){
		$('.redpointer').css('display','none');
		clearInterval(red);
		$('.red').css('top',redTop+'px');
	});
	
	$('.dog').click(function(){
		$('.message').animate({
			width: 0,
			fontSize: "0"
		}, 500);
		$('#select').animate({
			width: 0,
			fontSize: "0"
		}, 500);
		$('.logo').animate({
			opacity: "1"
		}, 1000);
		$('.logo').animate({
			top: 0,
			left: 0
		},1000);
		$('.logo').animate({
			width: 7+"%",
			height: 14+"%"
		},1000);
		setTimeout(function() {
			$('.container').animate({
				opacity: "0"
			},1000);
			location.href = "dogmain";
		}, 3500);
	});
	
	$('.cat').click(function(){
		$('.message').animate({
			width: 0,
			fontSize: "0"
		}, 500);
		$('#select').animate({
			width: 0,
			fontSize: "0"
		}, 500);
		$('.logo').animate({
			opacity: "1"
		}, 1000);
		$('.logo').animate({
			top: 0,
			left: 0
		},1000);
		$('.logo').animate({
			width: 7+"%",
			height: 14+"%"
		},1000);
		setTimeout(function() {
			$('.container').animate({
				opacity: "0"
			},1000);
			location.href = "catmain";
		}, 3500);
	});
});