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
	
	$.ajax({
		url: 'top5',
		data:{
			group1:'식료품'
		},
		success:function(data){
			console.log(data.productimageMap);
			
			for(var i=0;i<data.list.length;i++){
				$('#t'+(i+1)).empty();
				$('#t'+(i+1)).append('<div class=top_images id=t'+(i+1)+'_image></div>')
				$('#t'+(i+1)+'_image').append('<img class=top_image src="/resources/productimage/'+data.productimageMap[data.list[i].seq][0].filename+'" width=100% height=100%>')
				$('#t'+(i+1)).append(data.productMap[data.list[i].seq].name+'<br>');
				$('#t'+(i+1)).append(data.priceMap[data.list[i].seq]+'원');
			}
		}
	});
	
	$('#food').addClass('active');
	
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
		$('.top5_buttons').removeClass('active');
		$(this).addClass('active');
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
				console.log(data.productimageMap);
				
				for(var i=0;i<data.list.length;i++){
					$('#t'+(i+1)).empty();
					$('#t'+(i+1)).append('<div class=top_images id=t'+(i+1)+'_image></div>')
					$('#t'+(i+1)+'_image').append('<img class=top_image src="/resources/productimage/'+data.productimageMap[data.list[i].seq][0].filename+'" width=100% height=100%>')
					$('#t'+(i+1)).append(data.productMap[data.list[i].seq].name+'<br>');
					$('#t'+(i+1)).append(data.priceMap[data.list[i].seq]+'원');
				}
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
