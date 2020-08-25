$(function(){
	$.ajax({
		url: 'clientInfo',
		data: {code: "json"},
		success: function(data){
			if(data.cv.profile == ""){
				$('#profile_image').css({
					backgroundImage: 'url("/ex01/resources/profile/default_client.png")',
					backgroundRepeat: "no-repeat",
					backgroundSize: "cover" 
				});
			}else{
				$('#profile_image').css({
					backgroundImage: 'url("/ex01/resources/profile/'+data.cv.profile+'")',
					backgroundRepeat: "no-repeat",
					backgroundSize: "cover" 
				});
			}
		}
	});
	
	$.ajax({
		url: 'clientInfo',
		data: {code: "not"},
		success: function(result){
			$('#client_result').html(result);
		}
	});
	
	$('#profile_image').click(function(){
		$('#client_info').slideToggle('slow');
	});
	
	$('#catmainlogo').click(function(){
		location.href = "catmain";
	});
});