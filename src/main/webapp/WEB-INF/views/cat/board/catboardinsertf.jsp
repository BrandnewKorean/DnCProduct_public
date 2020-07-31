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
				content: $('#content').val(),
			},
			success: function(data){
				if(data.bcode == 0){
					alert('글이 등록되었습니다');
					if(data.view) location.href = 'catboard?code=image';
					else location.href = 'catboard?code=list';
				}else if(data.bcode == 1){
					alert('글 등록에 실패했습니다');
				}else{
					alert('로그인 후 사용하세요');
					location.href = 'catmain';
				}
			}
		});
	}//catboardinsert()
	
	$(function() {
		$('#title').on("input",function(){
			if($('#title').val() != '' && $('#content').val() != ''){
				if($('#title').val().trim().length == 0 || $('#content').val().trim().length == 0){
					$('#submit').attr('disabled',true).css('color','Plum');
				}else{
					$('#submit').attr('disabled',false).css('color','white');
				}
			}else{
				$('#submit').attr('disabled',true).css('color','Plum');
			}
		}); // on
	
		$('#content').on("input",function(){
			if($('#title').val() != '' && $('#content').val() != ''){
				if($('#title').val().trim().length == 0 || $('#content').val().trim().length == 0){
					$('#submit').attr('disabled',true).css('color','Plum');
				}else{
					$('#submit').attr('disabled',false).css('color','white');
				}
			}else{
				$('#submit').attr('disabled',true).css('color','Plum');
			}
		}); // on
	}); // function
	
	
</script>
</head>
<body>
	<div class=header>
		<img src="resources/image/logod.png" width=7% onclick="location.href='catboard'">
		<div id=header_menu>
			<button id=submit type="submit" onclick="catboardinsert()" onchange="insertdisabled()" disabled="disabled">발행</button>
		</div>
	</div>
	<div class=container>
		<input id=title placeholder="제목" type="text">
		<hr>
		<textarea id=content placeholder="내용"></textarea>
	</div>
</body>
</html>