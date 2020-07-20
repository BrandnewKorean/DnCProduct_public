<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="resources/css/cat/board/catboardview.css?ver=<%=System.currentTimeMillis()%>">
</head>

<script type="text/javascript" src="resources/script/jquery-3.2.1.min.js"></script>


	<script type="text/javascript">
		function catboarddelete() {
			$.ajax({
				url:'catboarddelete',
				data:{
					seq:$('#seq').val()
				},
				success:function(data){
					if(data.bcode==0){
						alert('삭제 되었습니다.');
						location.href='catboard';
					}else if(data.bcode==1){
						alert('삭제 실패했습니다. 다시 시도해주세요.');
					}else{
						alert('로그인 후 시도해주세요.');
						location.href='catmain'
					}
				}
			}) // ajax
		} // function	
	
	</script>


<body>

<button onclick="location.href='catboard'">이전으로</button>

	<input type="hidden" id="seq" value="${bv.seq}">
	<span>${bv.title}</span>
	
<hr>

<pre>
${bv.content}
</pre>

	<c:if test="${logID==bv.id}">
		<button onclick="location.href='catboardupdatef?seq=${bv.seq}'">수정</button>
		<button onclick="catboarddelete()">삭제</button>
	</c:if>

</body>
</html>