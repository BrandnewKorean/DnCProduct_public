<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script src="resources/script/jquery-3.2.1.min.js"></script>
<meta charset="UTF-8">
<title>글 수정</title>

	<script type="text/javascript">
		function catboardupdate() {
			$.ajax({
				url:'catboardupdate',
				data:{
					seq:$('#seq').val(),
					title:$('#title').val(),
					content:$('#content').val()
				},
				success:function(data){
					if(data.bcode==0){
						alert('수정성공');
						location.href='catboard';
					}else if(data.bcode==1){
						alert('수정실패');
					}else{
						alert('로그인 후 이용해주세요.');
						location.href='catmain';
					}
				} // success
			}); // ajax
		} // function
	</script>

</head>
<body>

	<c:if test="${dnc!=null}">
		<input type="hidden" id=seq value="${dnc.seq}">
		<input id='title' placeholder='제목' type="text" value="${dnc.title}">
		<hr>
		<textarea id=content placeholder="내용">${dnc.content}</textarea>
		<button onclick="catboardupdate()">확인</button>
	</c:if>
	
	<c:if test="${dnc==null}">
		<span>등록된 글이 없습니다.</span>
	</c:if>

</body>
</html>