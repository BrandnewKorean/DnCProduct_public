function writeComment(){
	var seq = document.getElementById("seq").value;
	var content = document.getElementById("content").value;
	var bcv={
		"seq":seq,
		"content":content,
	}
	
	$.ajax({
		url :"writecomment",
		type:"get",
		data:bcv,
		async:false,
		success:function(data){
			switch(data.code){
			case 0:
				alert('댓글 작성 성공');
				location.reload();
				break;
			case 1:
				alert('댓글을 DB에 저장하는 중 오류 발생');
				location.reload();
				break;
			case 2:
				alert('게시판 갱신 중 오류');
				location.reload();
				break;
			case 3:
				alert('게시판 갱신 중 오류 & 댓글을 DB에 저장하는 중 오류 발생');
				location.reload();
				break;
			default:
				alert('로그인 후 이용하세요');
				location.href = 'catmain';
				break;
			}
		},
		error:function(){
			alert("error");
		}
	});
}