$(function() {
	var url = ['termsuse', 'termsprivacy', 'termslocation'];
	
	for(var i=0;i<3;i++){
		$.ajax({
			url: url[i],
			async: false,
			success: function(result){
				$('#terms'+i).html(result);
			}
		});
	}
	
	$('#termssubmit').click(function(e) {
		e.preventDefault();
		
		var agreecheck1 = document.term.agree1.checked;
		var agreecheck2 = document.term.agree2.checked;
		
		if( agreecheck1 && agreecheck2 ) {
			$('#termsform').submit();
		} else if (!agreecheck1){
			alert('이용약관 동의는 필수 선택 항목입니다.');
		} else {
			alert('개인정보 동의는 필수 선택 항목입니다.');
		}
	});
});