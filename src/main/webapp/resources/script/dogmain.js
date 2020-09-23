$(function(){
	var image = document.getElementById('text_image');
	
	var engine = new RainyDay('test_canvas','test_image',1510,583,1,3);
	
	console.log('engine');
	engine.gravity = engine.GRAVITY_NON_LINEAR;
	engine.trail = engine.TRAIL_DROPS;
	engine.rain([ engine.preset(3, 3, 0.1), engine.preset(5, 5, 0.9), engine.preset(6, 2, 1) ], 100);

	
	
	$('.mainimg_diary').hover(function(){
		$('.text').text('반려견을 위한 다이어리입니다.');
	});
});

