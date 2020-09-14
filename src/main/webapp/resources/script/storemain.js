var banner = 0;
var interval = 0;
$(function(){
	$('.slide_item').css({
		width: (window.innerWidth * 0.9 -1)+'px',
		height: 400+'px'
	});
	
	$('.item').css({
		width: (window.innerWidth * 0.9 -1)+'px',
		height: 400+'px'
	});
	
	$(window).resize(function(){
		$('.slide_item').css({
			width: (window.innerWidth * 0.9 -1)+'px',
			height: 400+'px'
		});
		
		$('.item').css({
			width: (window.innerWidth * 0.9 -1)+'px',
			height: 400+'px'
		});
	});
	
	$('#banner_left').css('visibility','hidden');
	
	interval = setInterval('start()', 3000);
	console.log('interval = ',interval);
	
	$('#banner_left').click(function(){
		banner--;
		if(banner <= 0){
			banner = 0;
			$(this).css('visibility','hidden');
		}else {
			$('#banner_right').css('visibility','visible');
			$(this).css('visibility','visible');
		}
		$('.slide_wrap').css({
			transform: "translate(-"+(window.innerWidth * 0.9)*banner+"px)"
		});
		setTimeout(function(){
			interval = setInterval('start()',3000);
		},1000);
	});
	
	$('#banner_right').click(function(){
		banner++;
		if(banner >= 3){
			banner = 3;
			$(this).css('visibility','hidden');
		}else{
			$('#banner_left').css('visibility','visible');
			$(this).css('visibility','visible');
		}
		$('.slide_wrap').css({
			transform: "translate(-"+(window.innerWidth * 0.9)*banner+"px)"
		});
		setTimeout(function(){
			interval = setInterval('start()',3000);
		},1000);
	});
});

function start(){
	banner++;
	if(banner > 3) banner = 0;
	if(banner <= 0){
		$('#banner_left').css('visibility','hidden');
		$('#banner_right').css('visibility','visible');
	}else if(banner < 3){
		$('#banner_left').css('visibility','visible');
		$('#banner_right').css('visibility','visible');
	}else{
		$('#banner_left').css('visibility','visible');
		$('#banner_right').css('visibility','hidden');
	}
	$('.slide_wrap').css({
		transform: "translate(-"+(window.innerWidth * 0.9)*banner+"px)"
	});
}

function stop(){
	console.log('stop interval = '+interval);
	clearInterval(interval);
}