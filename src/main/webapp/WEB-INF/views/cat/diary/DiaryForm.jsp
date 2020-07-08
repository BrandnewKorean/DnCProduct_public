<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="resources/css/cat/diary/DiaryForm.css?ver=<%= System.currentTimeMillis()%>">
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="resources/script/diary.js?ver=<%= System.currentTimeMillis()%>"></script>
<title>Diary</title>
</head>
<body>
	<div class="header">
		<img id="catmainlogo" src="resources/image/logob.png" width=7%>
	</div>
	<div class=intro>
		
	</div>
	<div class=container>
		<div id=diary>
			<div id=calendar>달력</div>
			<textarea id=write placeholder="여기에 내용을 입력해주세요"></textarea>
		</div>
	</div>
</body>
</html>