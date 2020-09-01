	var oneclick = 1;
	var inputemail = 0;
	var inputid = 0;
	
	var sendPassword = function(){
		if(oneclick == 0){
			return false;
		}else{
			console.log("2222");
			var userid = $('#userId').val();
			var email = $('#email').val();
			console.log("3333"+userid);
			console.log("3333"+email);
			if(userid.length < 4){
				$('#idCheck1').attr("style", "");
				console.log(userid);
				return false;
			}else if (email == ""){
				$('#emailCheck1').attr("style", "");
				console.log(pfemail);
				return false;
			}else{
				$('#emailCheck1').attr("style", "display: none");
				console.log("4444"+email);
			$.ajax({
				type : 'Get',
				url : 'FindPw',
				data : {
					email : document.getElementById("email").value,
					id : document.getElementById("userId").value
				},
				success : function(data){
					if(data.result == true){
						console.log("성공");
						$('#writebox').remove();
						$('#sendsuccess').attr("style","");
						oneclick = 0;
						inputemail = 1;
						inputid = 1;
						document.getElementById('getemail').innerHTML = data.email;
						return true;
					}else if (data.result == false) {
						console.log("실패");
						return false;
					}
				}
			});
			}
		}
	};