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
	});
	
	$('.top5_buttons').click(function(){
		var id = $(this).attr('id');
		var group1;
		if(id == 'food'){
			group1 = "식료품";
		}else if(id == 'medic'){
			group1 = "배변/위생용품";
		}else if(id == 'beauty'){
			group1 = "미용용품";
		}else{
			group1 = "생활용품";
		}
		$.ajax({
			url: 'top5',
			data:{
				group1:group1
			},
			success:function(data){
				console.log(data.list);
			}
		});
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
