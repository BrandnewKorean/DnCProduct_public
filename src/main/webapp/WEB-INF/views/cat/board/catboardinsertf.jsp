<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="resources/css/cat/board/catboardinsertf.css?ver=<%=System.currentTimeMillis()%>">
<script src="resources/script/jquery-3.2.1.min.js"></script>
<meta charset="UTF-8">
<title>새 글 등록</title>
<script type="text/javascript">
	function catboardinsert(){
		$.ajax({
			url: 'catboardinsert',
			data:{
				title: $('#title').val(),
				content: $('#content').val()
			},
			success: function(data){
				if(data.bcode == 0){
					alert('글이 등록되었습니다');
					location.href = 'catboard';
				}else if(data.bcode == 1){
					alert('글 등록에 실패했습니다');
				}else{
					alert('로그인 후 사용하세요');
					location.href = 'catmain';
				}
			}
		});
	}//catboardinsert()
</script>
</head>
<body>
	<div class=header>
		<img src="resources/image/logod.png" width=7%>
		<div id=header_menu>
			<input type="submit" onclick="catboardinsert()" value="발행">
		</div>
	</div>
	<div class=container>
		<input id=title placeholder="제목" type="text">
		<hr>
		<textarea id=content placeholder="내용"></textarea>
	</div>
</body>
</html>