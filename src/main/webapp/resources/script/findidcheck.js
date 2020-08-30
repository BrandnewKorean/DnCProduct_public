var mailCheck = 0;
var clickOne = 1;


var findid = function() {
	if(clickOne == 0){
		return false;
	} else {
	var email = $('#email').val();
	console.log(email);
	var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
	if (email == "") {
		$('#mailCheck3').attr("style", "");
		return false;
	}else if(exptext.test(email) == false){
		console.log("error2");
		$('#mailCheck3').attr("style", "display: none");
		$('#mailCheck1').attr("style","");
		return false;
	}else {
		$('#mailCheck3').attr("style", "display: none");
		$.ajax({
			type: 'Get',
			url : 'FindId',
			data : {
				email : document.getElementById("email").value
			},
			success : function(data){
				if(data.result == true){
					$('#writebox').remove();
					$('#sendsuccess').attr("style","");
					mailCheck = 1;
					clickOne = 0;
					document.getElementById('getemail').innerHTML = data.email;
					return true;
				}else if(data.result == false){
					$('#mailCheck2').attr("style","");
					return false;
				}
			}
		});
	}
	}
}
