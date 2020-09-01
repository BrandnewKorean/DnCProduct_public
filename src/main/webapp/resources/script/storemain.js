$(function(){
	var banner = 0;
	
	$('#banner_left').click(function(){
		banner++;
		$('#ad_banner').css({
			transform: "translate("+window.innerWidth*banner+"px)"
		});
	});
	
	$('#banner_right').click(function(){
		banner--;
		$('#ad_banner').css({
			transform: "translate("+window.innerWidth*banner+"px)"
		});
	});
});