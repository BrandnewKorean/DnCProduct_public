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
		var formData = new FormData();
		
		formData.append('title', $('#title').val());
		formData.append('content', $('#content').val());
		
		for(var i=0;i<$('#files')[0].files.length;i++){
			formData.append('files', $('#files')[0].files[i]);
	

function catboardinsert(){
	var formData = new FormData();
	formData.append('title', $('#title').val());
	formData.append('content', $('#content').val());
	for(var i=0;i<$('#file')[0].files.length;i++){
		formData.append('files', $('#file')[0].files[i]);
	}
	$.ajax({
		url: 'catboardinsert',
		type:'POST',
		enctype: 'multipart/form-data',
		processData: false,
		contentType: false,
		data: formData,
		success: function(data){
			switch(data.code){
			case 0:
				alert('글이 등록되었습니다');
				if(data.view) location.href = 'catboard?code=image';
				else location.href = 'catboard?code=list';
				break;
			case 1:
				alert('파일을 업로드 하는 도중 문제가 발생했습니다');
				break;
			case 2:
				alert('글 등록에 실패했습니다');
				break;
			case 3:
				alert('로그인 후 사용하세요');
				location.href = "catmain";
				break;
			default:
				alert('내부적인 오류 발생');
				alert(data.code);
				break;
			}
	

function catboardinsert(){
	var formData = new FormData();
	formData.append('title', $('#title').val());
	formData.append('content', $('#content').val());
	for(var i=0;i<$('#file')[0].files.length;i++){
		formData.append('files', $('#file')[0].files[i]);
	}
	$.ajax({
		url: 'catboardinsert',
		type:'POST',
		enctype: 'multipart/form-data',
		processData: false,
		contentType: false,
		data: formData,
		success: function(data){
			switch(data.code){
			case 0:
				alert('글이 등록되었습니다');
				if(data.view) location.href = 'catboard?code=image';
				else location.href = 'catboard?code=list';
				break;
			case 1:
				alert('파일을 업로드 하는 도중 문제가 발생했습니다');
				break;
			case 2:
				alert('글 등록에 실패했습니다');
				break;
			case 3:
				alert('로그인 후 사용하세요');
				location.href = "catmain";
				break;
			default:
				alert('내부적인 오류 발생');
				alert(data.code);
				break;
			}
	

function catboardinsert(){
	var formData = new FormData();
	formData.append('title', $('#title').val());
	formData.append('content', $('#content').val());
	for(var i=0;i<$('#file')[0].files.length;i++){
		formData.append('files', $('#file')[0].files[i]);
	}
	$.ajax({
		url: 'catboardinsert',
		type:'POST',
		enctype: 'multipart/form-data',
		processData: false,
		contentType: false,
		data: formData,
		success: function(data){
			switch(data.code){
			case 0:
				alert('글이 등록되었습니다');
				if(data.view) location.href = 'catboard?code=image';
				else location.href = 'catboard?code=list';
				break;
			case 1:
				alert('파일을 업로드 하는 도중 문제가 발생했습니다');
				break;
			case 2:
				alert('글 등록에 실패했습니다');
				break;
			case 3:
				alert('로그인 후 사용하세요');
				location.href = "catmain";
				break;
			default:
				alert('내부적인 오류 발생');
				alert(data.code);
				break;
			}
	

function catboardinsert(){
	var formData = new FormData();
	formData.append('title', $('#title').val());
	formData.append('content', $('#content').val());
	for(var i=0;i<$('#file')[0].files.length;i++){
		formData.append('files', $('#file')[0].files[i]);
	}
	$.ajax({
		url: 'catboardinsert',
		type:'POST',
		enctype: 'multipart/form-data',
		processData: false,
		contentType: false,
		data: formData,
		success: function(data){
			switch(data.code){
			case 0:
				alert('글이 등록되었습니다');
				if(data.view) location.href = 'catboard?code=image';
				else location.href = 'catboard?code=list';
				break;
			case 1:
				alert('파일을 업로드 하는 도중 문제가 발생했습니다');
				break;
			case 2:
				alert('글 등록에 실패했습니다');
				break;
			case 3:
				alert('로그인 후 사용하세요');
				location.href = "catmain";
				break;
			default:
				alert('내부적인 오류 발생');
				alert(data.code);
				break;
			}
	

function catboardinsert(){
	var formData = new FormData();
	formData.append('title', $('#title').val());
	formData.append('content', $('#content').val());
	for(var i=0;i<$('#file')[0].files.length;i++){
		formData.append('files', $('#file')[0].files[i]);
	}
	$.ajax({
		url: 'catboardinsert',
		type:'POST',
		enctype: 'multipart/form-data',
		processData: false,
		contentType: false,
		data: formData,
		success: function(data){
			switch(data.code){
			case 0:
				alert('글이 등록되었습니다');
				if(data.view) location.href = 'catboard?code=image';
				else location.href = 'catboard?code=list';
				break;
			case 1:
				alert('파일을 업로드 하는 도중 문제가 발생했습니다');
				break;
			case 2:
				alert('글 등록에 실패했습니다');
				break;
			case 3:
				alert('로그인 후 사용하세요');
				location.href = "catmain";
				break;
			default:
				alert('내부적인 오류 발생');
				alert(data.code);
				break;
			}
	

function catboardinsert(){
	var formData = new FormData();
	formData.append('title', $('#title').val());
	formData.append('content', $('#content').val());
	for(var i=0;i<$('#file')[0].files.length;i++){
		formData.append('files', $('#file')[0].files[i]);
	}
	$.ajax({
		url: 'catboardinsert',
		type:'POST',
		enctype: 'multipart/form-data',
		processData: false,
		contentType: false,
		data: formData,
		success: function(data){
			switch(data.code){
			case 0:
				alert('글이 등록되었습니다');
				if(data.view) location.href = 'catboard?code=image';
				else location.href = 'catboard?code=list';
				break;
			case 1:
				alert('파일을 업로드 하는 도중 문제가 발생했습니다');
				break;
			case 2:
				alert('글 등록에 실패했습니다');
				break;
			case 3:
				alert('로그인 후 사용하세요');
				location.href = "catmain";
				break;
			default:
				alert('내부적인 오류 발생');
				alert(data.code);
				break;
			}
		}
		
		console.log($('#files')[0].files);
		
		$.ajax({
			url: 'catboardinsert',
			type:'POST',
			enctype: 'multipart/form-data',
			processData: false,
			contentType: false,
			data: formData,
			success: function(data){
				if(data.bcode == 0){
					alert('글이 등록되었습니다');
					if(data.view) location.href = 'catboard?code=image';
 					else location.href = 'catboard?code=list';
				}else if(data.bcode == 1){
					alert('파일 업로드 실패');
				}else{
					alert('로그인 후 사용하세요');
					alert(data.bcode);
 					location.href = 'catmain';
				}
			}
		});
	}//catboardinsert()

$(function(){
	$('#title').on("input", function(){
		console.log('input');
		if($('#content').val() != '' && $('#title').val() != '') $('#insertdisabled').attr('disabled', false).css('color', 'black');
		else $('#insertdisabled').attr('disabled', true).css('color','white');
	});
	
	$('#content').on("input", function(){
		console.log('input');
		if($('#content').val() != '' && $('#title').val() != '') $('#insertdisabled').attr('disabled', false).css('color', 'black');
		else $('#insertdisabled').attr('disabled', true).css('color','white');
	});
});

</script>
</head>
<body>
	<div class=header>
		<img src="resources/image/logod.png" width=7% onclick="location.href='catboard'">
		<div id=header_menu>	
			<input type="submit" onclick="catboardinsert()" value="발행" id="insertdisabled" disabled="disabled">
		</div>
	</div>
	<div class=container>
		<input id=title placeholder="제목" type="text">
		<hr>
		<label id=files_label for=files><input id=files type="file"></label>
		<textarea id=content placeholder="내용"></textarea>
	</div>
</body>
</html>