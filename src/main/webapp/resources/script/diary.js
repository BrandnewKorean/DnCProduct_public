$(function(){
	var image = 2;
	
	$('.intro').css({
		backgroundImage: 'url("/ex01/resources/interval/diary/interval'+1+'.jpg")',
	});
	$('.intro').animate({
		opacity: "0.8"
	},1000);
	setTimeout(function() {
		$('.intro').animate({
			opacity: "0"
		},1000);
	}, 3000);
	
	setInterval(function() {
		$('.intro').css({
			backgroundImage: 'url("/ex01/resources/interval/diary/interval'+image+'.jpg")',
		});
		$('.intro').animate({
			opacity: "0.8"
		},1000);
		setTimeout(function() {
			$('.intro').animate({
				opacity: "0"
			},1000);
		}, 3000);
		image++;
		if(image > 10) image = 1;
	}, 5100);
});