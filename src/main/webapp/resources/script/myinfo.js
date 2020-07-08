$(function(){
	var profile = $('#image').val();
	
	console.log(profile);
	if(profile == ''){
		$('#profile').css({
			backgroundImage: 'url("/ex01/resources/profile/default_client.png")',
			backgroundRepeat: "no-repeat",
			backgroundSize: "cover"
		});
	}else{
		$('#profile').css({
			backgroundImage: 'url("/ex01/resources/profile/'+profile+'")',
			backgroundRepeat: "no-repeat",
			backgroundSize: "cover"
		});
	}
});